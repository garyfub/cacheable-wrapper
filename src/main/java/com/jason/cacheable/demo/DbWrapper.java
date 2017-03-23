package com.jason.cacheable.demo;

import com.google.common.collect.ImmutableMap;
import com.jason.cacheable.annotation.CacheParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/19
 * Time:下午8:21
 */
public class DbWrapper {

    @CacheParam(key = "nbi")
    public String getNameById(int id) {
        return "name" + id;
    }

    @CacheParam(key = "nbi", read = false)
    public void setNameById(int id, String name) {
    }

    @CacheParam(key = "test")
    public double getRate(){
        return 1.0d;
    }

    @CacheParam(key = "gnlist")
    public List<String> getNameList(){
        return Arrays.asList("1","2");
    }

    @CacheParam(key = "gnmap")
    public Map<Integer,Double> getNameMap(){
        Map map = ImmutableMap.builder().put(1, 2d).put(2, 3d).build();
        return map;
    }
}
