package com.mori.demo.mapper;

import com.mori.demo.domain.QueryVo;
import com.mori.demo.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 * (使用Mybatis框架，不用写实现类。通过SQLSessionFactory得到的SQLSession对象，创建接口的代理对象)
 */
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from eesy_mybatis.user")
    List<User> findAll();

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改用户
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     *
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     *
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据姓名模糊查询用户
     *
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     *
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的查询条件，查询用户
     *
     * @return
     */
    List<User> findUserByVo(QueryVo vo);


    /**
     * 根据传入参数条件查询
     *
     * @param user
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据 id 集合查询用户信息
     *
     * @param vo
     * @return
     */
    List<User> findUserByIds(QueryVo vo);

    List<User> findAllWithAccount();

    List<User> findAllWithRole();

    List<User> findAllWithAccountLazy();
}
