package com.mori.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring 主配置类
 */
@Configuration
@ComponentScan(basePackages = "com.mori")
@Import({JdbcConfig.class, TransactionConfig.class})
@PropertySource({"classpath:jdbcConfig.properties"})
@EnableTransactionManagement
public class SpringConfig {
}
