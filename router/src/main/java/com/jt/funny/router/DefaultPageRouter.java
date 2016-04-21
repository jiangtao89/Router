package com.jt.funny.router;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class DefaultPageRouter extends Router {

    @Override
    public boolean open(Route route, RouteManager routeManager) {
        String uri = RouterUtils.decodeWithoutQuery(route.getUri());
        if (RouterUtils.isEmpty(uri)) {
            return false;
        }

        Target target = routeManager.getTarget(uri);
        if (target == null) {
            return false;
        }

        final IRouteHandler routeHandler = target.getRouteListener();
        if (routeHandler != null) {
            return routeHandler.open(route);
        }

        final Class cls = target.getClss();
        if (cls == null) {
            return false;
        }

        Activity activity = route.getActivity();
        if (activity == null) {
            return false;
        }

        try {
            Intent intent = new Intent(activity, cls);
            int requestCode = route.getRequestCode();
            /**
             * if requestCode >= 0 with Intent.FLAG_ACTIVITY_NEW_TASK will
             * call {@link Activity#onActivityResult(int, int, Intent) first}
             */
            if (requestCode < 0) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            launchActivity(activity, route, intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
