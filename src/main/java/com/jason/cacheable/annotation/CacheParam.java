package com.jason.cacheable.annotation;

import java.lang.annotation.*;

/**
 * Desc:
 * ------------------------------------
 * Author:liujg@meituan.com
 * Date:17/3/19
 * Time:下午7:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface CacheParam {

    //是否读请求
    boolean read() default true;

    String key() default "";

}
