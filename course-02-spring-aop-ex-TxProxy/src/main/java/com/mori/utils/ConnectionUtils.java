package com.mori.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接工具类，用于从数据源中获取一个连接，并实现和线程的绑定
 */
public class ConnectionUtils {

    //ThreadLocal：除了加锁的另外一种同步方式，解决线程不安全问题
    //创建一个ThreadLocal变量，每个线程访问的实际是这个变量的副本。
    //即多线程操作的时候，每个线程操作的是自己的 线程本地变量 ，副本之间互不影响，保证线程安全。
    //此处就是，每个线程值只操作自己唯一一个连接对象。
    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    private DataSource dataSource;

    //set方式注入DataSource
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     *
     * @return
     */
    public Connection getThreadConnection() {
        try {
            //从ThreadLocal获取连接对象
            Connection conn = tl.get();
            //判断当前线程上是否有连接
            if (conn == null) {
                // 没有则从数据源获取连接
                conn = dataSource.getConnection();
                //连接和线程绑定（存入ThreadLocal）
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把线程和连接解绑
     * 也就是当前线程移除了连接对象
     */
    public void removeThreadConnection() {
        tl.remove();
    }
}
