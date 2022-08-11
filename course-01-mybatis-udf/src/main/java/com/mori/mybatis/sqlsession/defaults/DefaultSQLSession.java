package com.mori.mybatis.sqlsession.defaults;

import com.mori.mybatis.cfg.Configuration;
import com.mori.mybatis.sqlsession.SqlSession;
import com.mori.mybatis.sqlsession.proxy.MapperProxyFactory;
import com.mori.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * SQLSession 的实现类
 */
public class DefaultSQLSession implements SqlSession {

    private Configuration cfg;
    private Connection conn;

    public DefaultSQLSession(Configuration cfg) {
        this.cfg = cfg;
        this.conn = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 创建代理对象
     *
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        // 增强dao接口的功能，根据映射关系，操作数据库
        // 被代理类的类加载器
        // 被代理类的接口数组：因为传入的dao接口本身就是接口，所以直接放到数组里即可
        // 如何代理：自定义一个代理类，必须实现处理器InvocationHandler接口
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass}, new MapperProxyFactory(this.cfg.getMappers(), this.conn));

    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        if (this.conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
