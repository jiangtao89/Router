package com.jt.funny.router;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public class DefaultWebRouter extends Router {

    @Override
    public boolean open(Route route, RouteManager routeManager) {
        String uri = RouterUtils.decodeWithoutQuery(route.getUri());
        if (RouterUtils.isEmpty(uri)) {
            return false;
        }

        Target target = routeManager.getTarget(uri);
        if (target != null) {
            final IRouteHandler routeHandler = target.getRouteListener();
            if (routeHandler != null) {
                return routeHandler.open(route);
            }
        }

        Activity activity = route.getActivity();
        if (activity == null) {
            return false;
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            launchActivity(activity, route, intent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
