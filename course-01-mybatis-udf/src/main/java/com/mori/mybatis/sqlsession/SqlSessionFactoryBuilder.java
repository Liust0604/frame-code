package com.mori.mybatis.sqlsession;

import com.mori.mybatis.cfg.Configuration;
import com.mori.mybatis.sqlsession.defaults.DefaultSQLSessionFactory;
import com.mori.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * SqlSessionFactoryBuilder构建者，用于构建SQLSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的字节输入流构建一个SQLSessionFactory工厂
     *
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSQLSessionFactory(cfg);
    }
}
