package com.jt.funny.router;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jiangtao on 16/3/26.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class Routers {

    private boolean mDebug = true;

    private final RouteManager mRouteManager;
    private final RouterManager mRouterManager;

    private static volatile Routers sSingleton = null;

    /**
     * Routers instances
     *
     * @return routers
     */
    public static Routers getInstances() {
        if (sSingleton == null) {
            synchronized (Routers.class) {
                if (sSingleton == null) {
                    sSingleton = new Routers();
                }
            }
        }
        return sSingleton;
    }

    private Routers() {
        mRouteManager = new RouteManager();
        mRouterManager = new RouterManager();
    }

    /**
     * debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        mDebug = debug;
        mRouteManager.setDebug(debug);
        mRouterManager.setDebug(debug);
    }

    /**
     * @return
     */
    public boolean isDebug() {
        return mDebug;
    }

    public void inject() {
        try {
            Class clss = Class.forName("com.jt.funny.router.RoutersInject");
            Method method = clss.getMethod("inject");
            method.invoke(clss);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * registerRouter
     *
     * @param scheme scheme
     * @param router router class
     */
    public void registerRouter(String scheme, Class<? extends Router> router) {
        mRouterManager.registerRouter(scheme, router);
    }

    /**
     * registerRoute
     *
     * @param uri  uri
     * @param clss clss
     */
    public void registerRoute(String uri, Class<? extends Activity> clss) {
        mRouteManager.registerRoute(uri, clss);
    }

    /**
     * registerRoute
     *
     * @param uri          uri
     * @param routeHandler routeHandler
     */
    public void registerRoute(String uri, IRouteHandler routeHandler) {
        mRouteManager.registerRoute(uri, routeHandler);
    }

    /**
     * open route
     *
     * @param route route
     * @return open success or failed
     */
    boolean open(Route route) {
        Uri URI = route.getUri();
        if (RouterUtils.isEmptySchema(URI)) {
            return false;
        }

        Router router = mRouterManager.getRouter(URI.getScheme());
        if (router == null) {
            return false;
        }

        return router.open(route, mRouteManager);
    }
}
