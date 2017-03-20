package com.jason.cacheable.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/17
 * Time:下午6:43
 */
@Aspect
public class RedisAop {

    private AtomicInteger success = new AtomicInteger(0);
    private AtomicInteger fail = new AtomicInteger(0);
    private AtomicInteger timeout = new AtomicInteger(0);

    @Pointcut("execution (* redis.clients.jedis.Jedis.*(..))")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        Object object = null;
        try {
            object = pjp.proceed();
        } catch (TimeoutException e) {
            timeout.getAndIncrement();
            throw e;
        } catch (Exception e) {
            fail.getAndIncrement();
            throw e;
        }
        success.getAndIncrement();
        return object;
    }
}
