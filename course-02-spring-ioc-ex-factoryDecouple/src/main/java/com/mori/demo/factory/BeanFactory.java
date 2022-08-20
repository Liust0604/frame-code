package com.mori.demo.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建Bean对象的工厂
 * 创建service和dao对象
 */
public class BeanFactory {

    private static Properties props;

    //定义一个容器，存放我们要创建的对象。保证单例对象
    private static Map<String, Object> beans;

    //静态代码块
    static {
        try {
            //加载配置文件
            props = new Properties();
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(is);
            //实例化容器
            beans = new HashMap<>();
            //遍历配置文件key和value,通过全类名创建对象,并存入容器
            Enumeration<Object> keys = props.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                String beanPath = props.getProperty(key);
                //每次调用默认构造函数创建对象。反射机制加载类，并实例化对象
                Object bean = Class.forName(beanPath).newInstance();
                beans.put(key, bean);
            }
        } catch (Exception e) {
            //抛出初始化错误
            throw new ExceptionInInitializerError("初始化bean.properties失败");
        }
    }

    /**
     * 根据Bean名称，获取Bean对象
     *
     * @param BeanName
     * @return
     */
    public static Object getBean(String BeanName) {
        return beans.get(BeanName);
    }

}
