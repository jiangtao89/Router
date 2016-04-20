package com.jt.funny.router;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by jiangtao on 16/3/27.
 *
 * @author jiangtao
 * @version 1.0.0
 */
public abstract class Router implements IRouterHandler {

    /**
     * launch activity
     *
     * @param activity activity
     * @param route    route
     * @param intent   intent
     */
    public void launchActivity(Activity activity, Route route, Intent intent) {
        if (intent == null) {
            return;
        }
        intent.setData(route.getUri());
        intent.addFlags(route.getFlags());

        int requestCode = route.getRequestCode();
        if (requestCode < 0) {
            activity.startActivity(intent);
        } else {
            activity.startActivityForResult(intent, requestCode);
        }

        // activity animation
        int enterAnim = route.getEnterAnim();
        int exitAnim = route.getExitAnim();
        if (enterAnim != 0 || exitAnim != 0) {
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

}
