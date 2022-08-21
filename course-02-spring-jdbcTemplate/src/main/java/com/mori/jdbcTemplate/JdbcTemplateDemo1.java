package com.mori.jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author LiuShitian
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //数据源可以使用连接池C3P0、Druid… 这里使用spring内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/db2?useUnicode=true;characterEncoding=utf8;serverTimezone=GMT%2B8;useSSL=false");
        ds.setUsername("root");
        ds.setPassword("root");

        //创建JdbcTemplate对象，并设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(ds);
        jdbcTemplate.execute("insert into db2.account(name , balance) values ('ccc',600)");
    }
}
