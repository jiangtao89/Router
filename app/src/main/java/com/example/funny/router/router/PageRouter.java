package com.example.funny.router.router;

import com.jt.funny.router.DefaultPageRouter;
import com.jt.funny.router.annotation.IRouter;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@IRouter(schema = {PageRouter.FUNNY})
public class PageRouter extends DefaultPageRouter {

    public static final String FUNNY = "funny";

}
