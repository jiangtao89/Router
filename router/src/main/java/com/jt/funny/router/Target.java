package com.jt.funny.router;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Target {
    private Class mClass;
    private IRouteHandler mRouteListener;

    public Target(Class aClass) {
        mClass = aClass;
    }

    public Target(IRouteHandler routeListener) {
        mRouteListener = routeListener;
    }

    public Class getClss() {
        return mClass;
    }

    public IRouteHandler getRouteListener() {
        return mRouteListener;
    }
}
