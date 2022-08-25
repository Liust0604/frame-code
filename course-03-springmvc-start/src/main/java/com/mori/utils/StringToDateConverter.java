package com.mori.utils;


import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义类型转换类
 * 字符串转日期
 */
public class StringToDateConverter implements Converter<String, Date> {

    /**
     * @param source 传入值
     * @return 返回值
     */
    @Override
    public Date convert(String source) {
        try {
            if (source == null) {
                throw new RuntimeException("请传入数据.");
            }
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (Exception e) {
            throw new RuntimeException("传入日期格式有误.");
        }
    }
}
