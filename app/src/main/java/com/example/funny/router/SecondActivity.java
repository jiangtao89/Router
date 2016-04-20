package com.example.funny.router;

import android.app.Activity;
import android.os.Bundle;
import com.jt.funny.router.Path;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@Path(
        schema = "funny",
        authority = "page",
        path = "second",
        clss = SecondActivity.class
)
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
