package com.example.funny.router;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.jt.funny.router.Route;
import com.jt.funny.router.annotation.IRoute;

@IRoute(
        schema = "funny",
        path = "main"
)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inject(View view) {
        new Route.Builder()
                .with(this)
                .scheme("funny")
                .host("page")
                .path("third")
                .build()
                .open();
    }
}
