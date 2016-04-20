package com.example.funny.router;

import android.app.Activity;
import android.os.Bundle;
import com.jt.funny.router.annotation.IRoute;

/**
 * Created by jiangtao on 16/4/20.
 *
 * @author jiang.tao
 * @version 1.0.0
 */
@IRoute(
        schema = "funny",
        path = {"second", "third"}
)
public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
    }
}
