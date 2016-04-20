package com.jt.funny.router;


import java.util.HashMap;

/**
 * Created by jiangtao on 16/4/19.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
class RouterManager {

    private HashMap<String, Class<? extends Router>> mRoutersClss = new HashMap<>();
    private HashMap<String, Router> mRouters = new HashMap<>();

    private boolean isDebug = false;

    /**
     * set debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    /**
     * registerRouter
     *
     * @param scheme scheme
     * @param router router class
     */
    public void registerRouter(String scheme, Class<? extends Router> router) {
        if (RouterUtils.isEmpty(scheme)) {
            if (isDebug) {
                throw new IllegalArgumentException("schema is empty!");
            }
            return;
        }
        mRoutersClss.put(scheme, router);
    }

    /**
     * getRouter
     *
     * @param scheme scheme
     * @return IRouter
     */
    public Router getRouter(String scheme) {
        Router router = mRouters.get(scheme);
        if (router != null) {
            return router;
        }
        Class<? extends Router> clss = mRoutersClss.get(scheme);
        if (clss != null) {
            try {
                router = clss.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (router != null) {
                mRouters.put(scheme, router);
            }
        }
        return router;
    }

}
