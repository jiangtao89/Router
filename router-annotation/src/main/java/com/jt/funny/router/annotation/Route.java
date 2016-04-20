package com.jt.funny.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jiangtao on 16/4/20.
 * schema://authority/path?id=12
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Route {

    /**
     * uri schema
     *
     * @return
     */
    String schema();

    /**
     * uri authority
     *
     * @return
     */
    String authority() default "page";

    /**
     * uri path
     *
     * @return
     */
    String[] path();

    /**
     * activity clss
     *
     * @return activity class
     */
    Class clss();
}
