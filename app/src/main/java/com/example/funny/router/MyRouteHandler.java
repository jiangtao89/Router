package com.example.funny.router;

import com.jt.funny.router.IRouteHandler;
import com.jt.funny.router.Route;

/**
 * Created by jiangtao on 16/4/21.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
//@IRoute(schema = "funny", path = "my")
public class MyRouteHandler implements IRouteHandler {
    @Override
    public boolean open(Route route) {
        return false;
    }
}
