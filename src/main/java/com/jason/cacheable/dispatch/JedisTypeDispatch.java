package com.jason.cacheable.dispatch;

import com.google.common.base.Strings;
import com.jason.cacheable.utils.ObjectUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/21
 * Time:下午7:59
 */
public class JedisTypeDispatch {

    private static final int EXPIRE_SECONDS = 60 * 60 * 24;

    public static Object getValue(Jedis jedis, String key, Method method) {
        Class returnClazz = method.getReturnType();
        if (returnClazz.isAssignableFrom(List.class)) {
            List<byte[]> list = jedis.lrange(key.getBytes(), 0, -1);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            } else {
                Type type = method.getGenericReturnType();
                return ObjectUtils.deserialList(list, type);
            }
        } else if (returnClazz.isAssignableFrom(Set.class)) {
            Set<byte[]> set = jedis.smembers(key.getBytes());
            if (CollectionUtils.isEmpty(set)) {
                return null;
            } else {
                Type type = method.getGenericReturnType();
                return ObjectUtils.deserialSet(set, type);
            }
        } else if (returnClazz.isAssignableFrom(Map.class)) {
            Map<byte[], byte[]> map = jedis.hgetAll(key.getBytes());
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
                if (Strings.isNullOrEmpty(value)) {
                    return null;
                }
                return Double.valueOf(value);
            } else if (name.equalsIgnoreCase("float")) {
                String value = jedis.get(key);
                if (Strings.isNullOrEmpty(value)) {
                    return null;
                }
                return Float.valueOf(value);
            } else if (name.equals("int") || name.equals("Integer")) {
                String value = jedis.get(key);
                if (Strings.isNullOrEmpty(value)) {
                    return null;
                }
                return Integer.valueOf(value);
            } else if (name.equals("String")) {
                return jedis.get(key);
            } else {
                byte[] value = jedis.get(key.getBytes());
                if (value == null) {
                    return null;
                } else {
                    return ObjectUtils.deserialObject(value, returnClazz);
                }
            }
        }
    }

    //// TODO: 17/3/22 list,set,map需加锁 
    public static void setValue(Jedis jedis, String key, Object value) {
        if (value instanceof List) {
            List list = (List) value;
            if (CollectionUtils.isNotEmpty(list)) {
                byte[][] bytes = new byte[list.size()][];
                for (int i = 0; i < list.size(); i++) {
                    Object o = list.get(i);
                    byte[] v = ObjectUtils.serialObject(o, o.getClass());
                    bytes[i] = v;
                }
                jedis.lpush(key.getBytes(), bytes);
                jedis.expire(key.getBytes(), EXPIRE_SECONDS);
            }
        } else if (value instanceof Set) {
            Set sValue = (Set) value;
            if (CollectionUtils.isNotEmpty(sValue)) {
                byte[][] bytes = new byte[sValue.size()][];
                int i = 0;
                for (Object o : sValue) {
                    byte[] v = ObjectUtils.serialObject(o, o.getClass());
                    bytes[i++] = v;
                }
                jedis.sadd(key.getBytes(), bytes);
                jedis.expire(key.getBytes(), EXPIRE_SECONDS);
            }
        } else if (value instanceof Map) {
            Map<Object, Object> map = (Map) value;
            if (MapUtils.isEmpty((Map) value)) {
                Map<byte[], byte[]> byteMap = new HashMap();
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    Object k = entry.getKey();
                    Object v = entry.getValue();
                    byte[] byteK = ObjectUtils.serialObject(k, k.getClass());
                    byte[] byteV = ObjectUtils.serialObject(v, v.getClass());
                    byteMap.put(byteK, byteV);
                }
                jedis.hmset(key.getBytes(), byteMap);
                jedis.expire(key.getBytes(), EXPIRE_SECONDS);
            }
        } else if (value instanceof Integer || value instanceof Double || value instanceof Float) {
            jedis.set(key, String.valueOf(value));
            jedis.expire(key, EXPIRE_SECONDS);
        } else if (value instanceof String) {
            jedis.set(key, (String) value);
            jedis.expire(key, EXPIRE_SECONDS);
        } else {
            byte[] byteV = ObjectUtils.serialObject(value, value.getClass());
            jedis.set(key.getBytes(), byteV);
            jedis.expire(key.getBytes(), EXPIRE_SECONDS);
        }
    }

    public static void delValue(Jedis jedis, String key, Object value){
        if (value instanceof Integer || value instanceof Double || value instanceof Float
                || value instanceof String) {
            jedis.del(key);
        } else {
            jedis.del(key.getBytes());
        }
    }
}
