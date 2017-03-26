package com.jason.cacheable.aop;

import com.jason.cacheable.annotation.CacheParam;
import com.jason.cacheable.dispatch.JedisTypeDispatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/19
 * Time:下午7:37
 */
@Aspect
public class CacheWrapperAop {

    @Autowired
    private Jedis jedis;//// TODO: 17/3/20 jedis换成ICacheClient,缓存方案可扩展

    /**
     * 读:先读缓存,存在直接返回,不存在load db,回写缓存返回
     * 写:直接写db,然后删cache
     * todo 写:更新cache,再写db
     */
    @Around(value = "@annotation(com.jason.cacheable.annotation.CacheParam)")
    public Object around(ProceedingJoinPoint pjp) {
        boolean read = false;
        Method method = null;
        try {
            method = getMethod(pjp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }

        CacheParam annotation = method.getAnnotation(CacheParam.class);
        read = annotation.read();
        String cacheKey = getKey(annotation.key(), pjp.getArgs());
        if (read) {
            Object value = JedisTypeDispatch.getValue(jedis, cacheKey, method);
            if (value != null) {
                return value;
            }
        }
        Object result = null;
        try {
            result = pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

        if (read) {
            JedisTypeDispatch.setValue(jedis, cacheKey, result);
        } else {
            JedisTypeDispatch.delValue(jedis, cacheKey, result);
        }
        return result;
    }

    private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        MethodSignature msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    }

    private String getKey(String key, Object[] argNames) {
        StringBuilder sb = new StringBuilder(key);
        for (int i = 0; i < argNames.length; i++) {
            sb.append(String.valueOf(argNames[i]));
            if (i < argNames.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }
}
