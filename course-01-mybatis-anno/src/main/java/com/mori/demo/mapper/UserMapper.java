package com.mori.demo.mapper;

import com.mori.demo.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 用户的持久层接口
 * (使用Mybatis框架，不用写实现类。通过SQLSessionFactory得到的SQLSession对象，创建接口的代理对象)
 */
@CacheNamespace(blocking = true)
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from eesy_mybatis.user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "sex", property = "sex")
    })
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user
     */
    @Insert("insert into eesy_mybatis.user(username,address,sex,birthday) values (#{username},#{address},#{sex},#{birthday}) ")
    void saveUser(User user);

    /**
     * 修改用户
     *
     * @param user
     */
    @Update("update eesy_mybatis.user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    @Delete("delete from eesy_mybatis.user where id=#{userId}")
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     *
     * @return
     */
    @Select("select * from eesy_mybatis.user where id=#{userId}")
    @ResultMap("userMap")
    User findById(Integer userId);

    /**
     * 根据姓名模糊查询用户
     *
     * @return
     */
    @Select("select * from eesy_mybatis.user where username like #{username}")
    List<User> findByName(String username);

    /**
     * 查询总用户数
     *
     * @return
     */
    @Select("select count(*) from eesy_mybatis.user")
    int findTotal();

    //查多，通常是延迟加载，FetchType.LAZY 懒加载
    @Select("select * from eesy_mybatis.user")
    @Results(
            @Result(column = "id", property = "accounts",
                    many = @Many(select = "com.mori.demo.mapper.AccountMapper.findByUid", fetchType = FetchType.LAZY))
    )
    List<User> findAllWithAccount();

    List<User> findAllWithAccountLazy();
}
