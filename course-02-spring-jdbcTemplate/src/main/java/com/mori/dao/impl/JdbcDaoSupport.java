package com.mori.dao.impl;

/**
 * 此类用于抽取Dao中的重复代码
 * （Spring也提供这类似样的类 import org.springframework.jdbc.core.support.JdbcDaoSupport;）
 */
/*public class JdbcDaoSupport {

    private JdbcTemplate jdbcTemplate;

    //set方式注入JdbcTemplate
    //方式1：在AccountDaoImpl中注入jdbcTemplate，由直接此获取jdbcTemplate
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    //方式2：在AccountDaoImpl中注入dataSource，由此间接获取jdbcTemplate
    public void setDataSource(DataSource dataSource) {
        if (jdbcTemplate == null) {
            jdbcTemplate = this.createJdbcTemplate(dataSource);
        }
    }

    private JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}*/
