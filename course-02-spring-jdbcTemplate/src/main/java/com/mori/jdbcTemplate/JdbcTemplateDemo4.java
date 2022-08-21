package com.mori.jdbcTemplate;

import com.mori.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC 的 crud 操作
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ac.getBean("jdbcTemplate");

        //保存
        //jdbcTemplate.update("insert into db2.account(name ,balance) values (?,?)", "eee", 333f);

        //更新
        //jdbcTemplate.update("update db2.account set name=?,balance=? where id = 13", "ae", 3333f);

        //删除
        //jdbcTemplate.update("delete from db2.account where id=?", 13);

        //查询所有
        //List<Account> accounts = jdbcTemplate.query("select * from db2.account where balance>?", new AccountRowMapper(), 1000f);
        //Spring方式
        /*List<Account> accounts = jdbcTemplate.query("select * from db2.account where balance>?",
                new BeanPropertyRowMapper<>(Account.class), 1000f);
        System.out.println(accounts);*/

        //查询一个
        /*List<Account> accounts = jdbcTemplate.query("select * from db2.account where id=?",
                new BeanPropertyRowMapper<>(Account.class), 12);
        System.out.println(accounts.isEmpty() ? "无" : accounts.get(0));*/

        //查询返回一行一列（聚合函数，但不加 group by 字句）
        Integer count = jdbcTemplate.queryForObject("select count(*) from db2.account where balance>?", Integer.class, 1000f);
        System.out.println(count);
    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {

    /**
     * 结果集中的数据，封装到Account中；然后spring把所有Account加入集合中返回
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setBalance(rs.getFloat("balance"));
        return account;
    }
}
