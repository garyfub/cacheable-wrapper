package com.jason.cacheable.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jason.cacheable.dispatch.ClassMappingByName;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/20
 * Time:下午2:22
 */
public class ObjectUtils {

    public static DefaultIdStrategy strategy = new DefaultIdStrategy(
            IdStrategy.DEFAULT_FLAGS | IdStrategy.COLLECTION_SCHEMA_ON_REPEATED_FIELDS, null, 0);

    public static byte[] serialObject(Object object, Class clazz) {
        Schema<Object> schema = RuntimeSchema.getSchema(clazz, strategy);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        return ProtostuffIOUtil.toByteArray(object, schema, buffer);
    }

    public static Object deserialObject(byte[] bytes, Class clazz) {
        Schema schema = RuntimeSchema.getSchema(clazz, strategy);
        Object object = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, object, schema);
        return object;
    }

    public static List deserialList(List<byte[]> list, Type type) {
        Class clazz = ClassMappingByName.getCollectionObjectClass(type);
        List results = Lists.newArrayList();
        for (byte[] s : list) {
            results.add(deserialObject(s, clazz));
        }
        return results;
    }

    public static Set deserialSet(Set<byte[]> set, Type type) {
        Class clazz = ClassMappingByName.getCollectionObjectClass(type);
        Set results = Sets.newHashSet();
        for (byte[] s : set) {
            results.add(deserialObject(s, clazz));
        }
        return results;
    }

    public static Map deserialMap(Map<byte[], byte[]> map, Type type) {
        Pair pair = ClassMappingByName.getMapObjectClass(type);
        Object key = pair.getKey();
        Object value = pair.getValue();
        boolean keyString = String.class.equals(key.getClass());
        boolean valueString = String.class.equals(value.getClass());

        Map results = Maps.newHashMap();
        for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
            byte[] sKey = entry.getKey();
            Object k = null;
            if (keyString) {
                k = sKey;
            } else {
                k = deserialObject(sKey, key.getClass());
            }

            byte[] sValue = entry.getValue();
            Object v = null;
            if (valueString) {
                v = sValue;
            } else {
                v = deserialObject(sValue, value.getClass());
            }
            results.put(k, v);
        }
        return results;
    }
}