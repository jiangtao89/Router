package com.jt.funny.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jiangtao on 16/4/20.
 * schema://host/path?id=12
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface IRoute {

    /**
     * uri schema
     *
     * @return
     */
    String schema();

    /**
     * uri host
     *
     * @return
     */
    String host() default "page";

    /**
     * uri path
     *
     * @return
     */
    String[] path();
}
