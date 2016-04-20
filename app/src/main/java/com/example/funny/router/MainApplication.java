package com.example.funny.router;

import android.app.Application;
import com.jt.funny.router.Routers;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Routers.getInstances().setContext(this);
    }
}
