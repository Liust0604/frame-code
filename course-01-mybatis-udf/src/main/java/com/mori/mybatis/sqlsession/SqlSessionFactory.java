package com.mori.mybatis.sqlsession;

/**
 * SQLSessionFactory工厂，用于生产SqlSession对象
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
