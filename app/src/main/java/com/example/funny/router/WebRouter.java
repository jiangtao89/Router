package com.example.funny.router;

import com.jt.funny.router.DefaultWebRouter;
import com.jt.funny.router.annotation.Router;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */

@Router(schema = {"http", "https"}, clss = WebRouter.class)
public class WebRouter extends DefaultWebRouter {
}
