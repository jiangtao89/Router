package com.jt.funny.router;

import android.app.Activity;

import java.util.HashMap;

/**
 * Created by jiangtao on 16/4/19.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
class RouteManager {

    private boolean isDebug = false;

    private HashMap<String, Target> mRoutes = new HashMap<String, Target>();

    /**
     * debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(String uri, Class<? extends Activity> clss) {
        if (isEmptySchema(uri)) {
            return;
        }
        Target target = new Target(clss);
        mRoutes.put(RouterUtils.decodeWithoutQuery(uri), target);
    }

    /**
     * @param uri           uri
     * @param routeListener routeListener
     */
    public void registerRoute(String uri, IRouteHandler routeListener) {
        if (isEmptySchema(uri)) {
            return;
        }
        Target target = new Target(routeListener);
        mRoutes.put(RouterUtils.decodeWithoutQuery(uri), target);
    }

    /**
     * getTarget
     *
     * @param uri uri
     * @return Target
     */
    public Target getTarget(String uri) {
        return mRoutes.get(uri);
    }

    private boolean isEmptySchema(String uri) {
        if (RouterUtils.isEmptySchema(uri)) {
            if (isDebug) {
                throw new IllegalArgumentException("uri format error!");
            }
            return true;
        }
        return false;
    }
}
