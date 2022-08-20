package com.mori.utils;

/**
 * 事务管理 工具类
 * <p>
 * 开启事务、提交事务、回滚事务、释放连接
 * 效果：开启事务时，会在当前线程中创建一个jdbc连接对象，并且这个连接不会自动提交事务
 * 接着进行一系列事务操作：提交事务、回滚事务
 * 最后释放连接，会关闭并移除当前线程的jdbc连接对象
 * 当下一次开启事务时，使用就是线程中新创建的jdbc连接，保证事务操作和线程安全
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    //set方式注入ConnectionUtils
    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void begin() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close(); //把jdbc连接还回连接池中
            connectionUtils.removeThreadConnection(); //解绑。因为前一步把jdbc连接释放了，让线程中的conn无效，所以需要移除线程中无效的jdbc连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
