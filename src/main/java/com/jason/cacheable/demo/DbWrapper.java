package com.jason.cacheable.demo;

import com.jason.cacheable.annotation.CacheParam;

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
        System.out.println("input:" + id);
        return "name" + id;
    }

    @CacheParam(key = "nbi", read = false)
    public void setNameById(int id, String name) {
    }
}
