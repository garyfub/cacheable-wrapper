package com.jason.cacheable.cache;

import com.jason.cacheable.exception.CacheKvException;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/20
 * Time:下午2:15
 */
public class JedisCacheClient implements ICacheClient {

    @Override
    public boolean set(String key, String value) throws CacheKvException {
        return false;
    }

    @Override
    public boolean set(String key, String value, int expire) throws CacheKvException {
        return false;
    }

    @Override
    public boolean setBytes(String key, byte[] value, int expire) throws CacheKvException {
        return false;
    }

    @Override
    public boolean setex(String key, String value, int expire) throws CacheKvException {
        return false;
    }

    @Override
    public boolean setCount(String key, int value, int expire) throws CacheKvException {
        return false;
    }

    @Override
    public boolean setCount(String key, int value) throws CacheKvException {
        return false;
    }

    @Override
    public String get(String key) throws CacheKvException {
        return null;
    }

    @Override
    public byte[] getBytes(String key) throws CacheKvException {
        return new byte[0];
    }

    @Override
    public Integer getCount(String key) throws CacheKvException {
        return null;
    }

    @Override
    public boolean expire(String key, int expireSeconds) throws CacheKvException {
        return false;
    }

    @Override
    public boolean del(String key) throws CacheKvException {
        return false;
    }

    @Override
    public boolean batchDel(List<String> keys) throws CacheKvException {
        return false;
    }

    @Override
    public Integer incrBy(String key, int count) throws CacheKvException {
        return null;
    }

    @Override
    public Integer incr(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Integer incr(String key, int expireSeconds) throws CacheKvException {
        return null;
    }

    @Override
    public Integer incrBy(String key, int count, int expireSeconds) throws CacheKvException {
        return null;
    }

    @Override
    public Integer decrBy(String key, int count) throws CacheKvException {
        return null;
    }

    @Override
    public Integer decr(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Integer decr(String key, int expireSeconds) throws CacheKvException {
        return null;
    }

    @Override
    public Integer decrBy(String key, int count, int expireSeconds) throws CacheKvException {
        return null;
    }

    @Override
    public boolean addItems(String key, List<?> items) throws CacheKvException {
        return false;
    }

    @Override
    public boolean addItems(String key, List<?> items, int expireTime) throws CacheKvException {
        return false;
    }

    @Override
    public boolean addItems(String key, int maxLength, List<?> items) throws CacheKvException {
        return false;
    }

    @Override
    public boolean addItems(String key, List<?> items, int maxCount, int expireTime)
            throws CacheKvException {
        return false;
    }

    @Override
    public boolean removeItems(String key, int offset, int count) throws CacheKvException {
        return false;
    }

    @Override
    public boolean rpush(String key, List<?> items) throws CacheKvException {
        return false;
    }

    @Override
    public Object rpop(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Object lpop(String key) throws CacheKvException {
        return null;
    }

    @Override
    public List<Object> getItems(String key, int offset, int count) throws CacheKvException {
        return null;
    }

    @Override
    public List<? extends Object> getItemsByClazz(String key, Class<? extends Object> clazz,
            int offset, int count) throws CacheKvException {
        return null;
    }

    @Override
    public List<?> getItemsAll(String key) throws CacheKvException {
        return null;
    }

    @Override
    public List<?> getItemsAll(String key, Class<?> clazz) throws CacheKvException {
        return null;
    }

    @Override
    public Integer getItemsCount(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, String> mgetString(List<?> keys) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, byte[]> mgetRawBytes(List<String> keys) throws CacheKvException {
        return null;
    }

    @Override
    public byte[] getRawBytes(String key) throws CacheKvException {
        return new byte[0];
    }

    @Override
    public Map<String, Integer> mgetCount(List<String> keys) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, Integer> mgetCount(List<String> keys, long timeout) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, String> mgetStringCheckingPartFailed(List<?> keys) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, Integer> mgetCountCheckPartFailed(List<?> keys) throws CacheKvException {
        return null;
    }

    @Override
    public boolean msetString(Map<String, String> valueMap) throws CacheKvException {
        return false;
    }

    @Override
    public boolean msetString(Map<String, String> valueMap, int expireSeconds)
            throws CacheKvException {
        return false;
    }

    @Override
    public boolean msetString(Map<String, String> valueMap, int expireSeconds,
            boolean partOkAsFailed) throws CacheKvException {
        return false;
    }

    @Override
    public boolean hset(String key, String field, String value) throws CacheKvException {
        return false;
    }

    @Override
    public boolean hset(String key, String field, byte[] value, int expireSeconds)
            throws CacheKvException {
        return false;
    }

    @Override
    public boolean hset(String key, String field, String value, int expireSeconds)
            throws CacheKvException {
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, String> hash) throws CacheKvException {
        return false;
    }

    @Override
    public boolean hmset(String key, Map<String, String> hash, int expireSeconds)
            throws CacheKvException {
        return false;
    }

    @Override
    public String hget(String key, String field) throws CacheKvException {
        return null;
    }

    @Override
    public byte[] hgetBytes(String key, String field) throws CacheKvException {
        return new byte[0];
    }

    @Override
    public Map<String, String> hmget(String key, String... fields) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, String> hgetAll(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, byte[]> hgetAllBytes(String key) throws CacheKvException {
        return null;
    }

    @Override
    public Map<String, byte[]> hmgetBytes(String key, List<String> fields) throws CacheKvException {
        return null;
    }

    @Override
    public boolean hdel(String key, String... fields) throws CacheKvException {
        return false;
    }

    @Override
    public Set<String> hkeys(String key) throws CacheKvException {
        return null;
    }

    @Override
    public boolean exists(String key) throws CacheKvException {
        return false;
    }

    @Override
    public Long ttl(String key) throws CacheKvException {
        return null;
    }
}
