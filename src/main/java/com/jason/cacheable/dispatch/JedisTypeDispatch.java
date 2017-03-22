package com.jason.cacheable.dispatch;

import com.jason.cacheable.utils.ObjectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/21
 * Time:下午7:59
 */
public class JedisTypeDispatch {

    public static Object getValue(Jedis jedis, String key, Method method) {
        Class returnClazz = method.getReturnType();
        if (returnClazz.isAssignableFrom(List.class)) {
            List<String> list = jedis.lrange(key, 0, -1);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            } else {
                Type type = method.getGenericReturnType();
                return ObjectUtils.deserialList(list, type);
            }
        } else if (returnClazz.isAssignableFrom(Set.class)) {
            Set<String> set = jedis.smembers(key);
            if (CollectionUtils.isEmpty(set)) {
                return null;
            } else {
                Type type = method.getGenericReturnType();
                return ObjectUtils.deserialSet(set, type);
            }
        } else if (returnClazz.isAssignableFrom(Map.class)) {
            Map<String, String> map = jedis.hgetAll(key);
            if (MapUtils.isEmpty(map)) {
                return null;
            } else {
                Type type = method.getGenericReturnType();
                return ObjectUtils.deserialMap(map, type);
            }
        } else {
            String name = returnClazz.getSimpleName();
            if (name.equalsIgnoreCase("double")) {
                String value = jedis.get(key);
                return Double.valueOf(value);
            } else if (name.equalsIgnoreCase("float")) {
                String value = jedis.get(key);
                return Float.valueOf(value);
            } else if (name.equals("int") || name.equals("Integer")) {
                String value = jedis.get(key);
                return Integer.valueOf(value);
            } else {
                byte[] value = jedis.get(key.getBytes());
                return ObjectUtils.deserialObject(value, returnClazz);
            }
        }
    }

}
