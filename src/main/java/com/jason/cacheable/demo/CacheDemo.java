package com.jason.cacheable.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/19
 * Time:下午8:03
 */
public class CacheDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("cache-aop.xml");
        DbWrapper dbWrapper = (DbWrapper) ctx.getBean("dbWrapper");
        System.out.println(dbWrapper.getNameById(2));
        dbWrapper.setNameById(2, "n");
    }
}
