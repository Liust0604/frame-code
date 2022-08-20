package com.mori.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 配置类，它的作用和bean.xml是一样的
 */
//@Configuration
@ComponentScan(basePackages = "com.mori")
@Import({JdbcConfig.class})
@PropertySource({"classpath:jdbcConfig.properties"})
public class SpringConfiguration {

}

