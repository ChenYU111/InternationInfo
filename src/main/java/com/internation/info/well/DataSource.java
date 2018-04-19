package com.internation.info.well;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解，用于标识使用哪一个数据源
 * 
 * 	参考 http://www.cnblogs.com/surge/p/3582248.html
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    DataSourceEnum value();
}