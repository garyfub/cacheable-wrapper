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
 * Time:下午2:12
 */
public interface ICacheClient {

    boolean set(String key, String value) throws CacheKvException;

    boolean set(String key, String value, int expire) throws CacheKvException;

    boolean setBytes(String key, byte[] value, int expire) throws CacheKvException;

    boolean setex(String key, String value, int expire) throws CacheKvException;

    boolean setCount(String key, int value, int expire) throws CacheKvException;

    boolean setCount(String key, int value) throws CacheKvException;

    String get(String key) throws CacheKvException;

    byte[] getBytes(String key) throws CacheKvException;

    Integer getCount(String key) throws CacheKvException;

    boolean expire(String key, int expireSeconds) throws CacheKvException;

    boolean del(String key) throws CacheKvException;

    boolean batchDel(List<String> keys) throws CacheKvException;

    Integer incrBy(String key, int count) throws CacheKvException;

    Integer incr(String key) throws CacheKvException;

    Integer incr(String key, int expireSeconds) throws CacheKvException;

    Integer incrBy(String key, int count, int expireSeconds) throws CacheKvException;

    Integer decrBy(String key, int count) throws CacheKvException;

    Integer decr(String key) throws CacheKvException;

    Integer decr(String key, int expireSeconds) throws CacheKvException;

    Integer decrBy(String key, int count, int expireSeconds) throws CacheKvException;

    boolean addItems(String key, List<?> items) throws CacheKvException;

    boolean addItems(String key, List<?> items, int expireTime) throws CacheKvException;

    boolean addItems(String key, int maxLength, List<?> items) throws CacheKvException;

    boolean addItems(String key, List<?> items, int maxCount, int expireTime)
            throws CacheKvException;

    boolean removeItems(String key, int offset, int count) throws CacheKvException;

    boolean rpush(String key, List<?> items) throws CacheKvException;

    Object rpop(String key) throws CacheKvException;

    Object lpop(String key) throws CacheKvException;

    @SuppressWarnings("unchecked")
            List<Object> getItems(String key, int offset, int count) throws CacheKvException;

    List<? extends Object> getItemsByClazz(String key, Class<? extends Object> clazz, int offset,
            int count) throws CacheKvException;

    List<?> getItemsAll(String key) throws CacheKvException;

    List<?> getItemsAll(String key, Class<?> clazz) throws CacheKvException;

    Integer getItemsCount(String key) throws CacheKvException;

    Map<String, String> mgetString(List<?> keys) throws CacheKvException;

    Map<String, byte[]> mgetRawBytes(List<String> keys) throws CacheKvException;

    byte[] getRawBytes(String key) throws CacheKvException;

    @SuppressWarnings("unchecked")
            Map<String, Integer> mgetCount(List<String> keys) throws CacheKvException;

    Map<String, Integer> mgetCount(List<String> keys, long timeout) throws CacheKvException;

    Map<String, String> mgetStringCheckingPartFailed(List<?> keys) throws CacheKvException;

    Map<String, Integer> mgetCountCheckPartFailed(List<?> keys) throws CacheKvException;

    boolean msetString(Map<String, String> valueMap) throws CacheKvException;

    boolean msetString(Map<String, String> valueMap, int expireSeconds) throws CacheKvException;

    boolean msetString(Map<String, String> valueMap, int expireSeconds, boolean partOkAsFailed)
            throws CacheKvException;

    boolean hset(String key, String field, String value) throws CacheKvException;

    boolean hset(String key, String field, byte[] value, int expireSeconds) throws CacheKvException;

    boolean hset(String key, String field, String value, int expireSeconds) throws CacheKvException;

    boolean hmset(String key, Map<String, String> hash) throws CacheKvException;

    boolean hmset(String key, Map<String, String> hash, int expireSeconds) throws CacheKvException;

    String hget(String key, String field) throws CacheKvException;

    byte[] hgetBytes(String key, String field) throws CacheKvException;

    Map<String, String> hmget(String key, String... fields) throws CacheKvException;

    Map<String, String> hgetAll(String key) throws CacheKvException;

    Map<String, byte[]> hgetAllBytes(String key) throws CacheKvException;

    Map<String, byte[]> hmgetBytes(String key, List<String> fields) throws CacheKvException;

    boolean hdel(String key, String... fields) throws CacheKvException;

    public Set<String> hkeys(String key) throws CacheKvException;

    boolean exists(String key) throws CacheKvException;

    Long ttl(String key) throws CacheKvException;
}
