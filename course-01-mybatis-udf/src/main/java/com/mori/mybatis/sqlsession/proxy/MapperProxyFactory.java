package com.mori.mybatis.sqlsession.proxy;

import com.mori.mybatis.cfg.Mapper;
import com.mori.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * 代理
 */
public class MapperProxyFactory implements InvocationHandler {

    //<工作空间全类名.方法名,映射信息>
    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxyFactory(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 方法增强
     * 增强的内容是给定义的方法，去执行映射的sql
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //获取key
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String key = className + "." + methodName;
        //获取value,映射信息
        Mapper mapper = mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("传入的参数有误");
        }
        //调用工具类执行查询所有
        return new Executor().selectList(mapper, conn);
    }
}
