package com.mori.mybatis.sqlsession.defaults;

import com.mori.mybatis.cfg.Configuration;
import com.mori.mybatis.sqlsession.SqlSession;
import com.mori.mybatis.sqlsession.SqlSessionFactory;

/**
 * SQLSessionFactory 接口的实现类
 */
public class DefaultSQLSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSQLSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSQLSession(cfg);
    }
}
