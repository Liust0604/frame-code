package com.mori.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 解决实体管理器工厂的浪费资源和耗时问题
 * <p>
 * 通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂
 * <p>
 * 1、第一次访问getEntityManager()方法，通过静态代码块创建factory对象
 * 2、第二层访问getEntityManager()方法，使用已经创建好的factory对象
 */
public class JpaUtils {

    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
