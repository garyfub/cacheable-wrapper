package com.jason.cacheable.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/17
 * Time:下午3:17
 */
public class RedisDemo {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("cache-aop.xml");
        Jedis jedis = (Jedis) ctx.getBean("jedis");
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

}
