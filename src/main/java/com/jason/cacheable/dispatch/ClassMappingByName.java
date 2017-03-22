package com.jason.cacheable.dispatch;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/22
 * Time:上午11:22
 */
public class ClassMappingByName {

    private static Map<String, Class> classMap = new HashMap();

    public static Class getCollectionObjectClass(Type type) {
        String typeName = type.toString();
        int start = typeName.indexOf("<");
        int end = typeName.lastIndexOf(">");
        final String className = typeName.substring(start + 1, end).trim();
        return getClassByName(className);
    }

    public static Pair getMapObjectClass(Type type) {
        String typeName = type.toString();
        int start = typeName.indexOf("<");
        int end = typeName.lastIndexOf(">");
        final String className = typeName.substring(start + 1, end).trim();
        String[] clazzs=className.split(",");
        String keyClazzName=clazzs[0].trim();
        String valueClazzName=clazzs[1].trim();
        Class keyClazz=getClassByName(keyClazzName);
        Class valueClazz=getClassByName(valueClazzName);
        return Pair.of(keyClazz,valueClazz);
    }

    private static Class getClassByName(String className){
        Class clazz = classMap.get(className);
        if (clazz == null) {
            try {
                clazz = Class.forName(className);
            } catch (Exception e) {
                e.printStackTrace();
            }
            classMap.put(className, clazz);
        }
        return clazz;
    }
}
