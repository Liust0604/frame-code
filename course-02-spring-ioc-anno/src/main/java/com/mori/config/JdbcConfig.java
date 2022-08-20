package com.mori.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * 配置类
 */
public class JdbcConfig {

    @Value("${jdbc.driver}") //el表达式
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 用于创建一个Bean对象
     * 并通过@Bean注解，将方法返回的Bean对象存入spring的ioc容器中
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "runner")
    @Scope("prototype") //默认单例，设为多例
    //加了 @Bean 的参数，参数默认Autowired类型注入，类型是参数类型，若存在相同类型，则参数名作为bean对象id再进行查找。若最终容器没有符合的bean对象的话会报红。
    //为了解决多个Bean对象同类型Autowired无法正确注入的问题，可以使用@Qualifier注解，指定Bean对象的id
    public QueryRunner createQueryRunner(@Qualifier("dataSource") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
