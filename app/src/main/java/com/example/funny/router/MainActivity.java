package com.example.funny.router;

import android.app.Activity;
import android.content.Intent;
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

    public void page(View view) {
        new Route.Builder()
                .with(this)
                .scheme("funny")
                .host("page")
                .path("third")
                .appendQueryParameter("id", 5)
                .requestCode(100)
                .build()
                .open();
    }

    public void http(View view) {
        new Route.Builder()
                .with(this)
                .withUrl("http://wwww.baidu.com")
                .build()
                .open();
    }

    public void local(View view) {
        new Route.Builder()
                .with(this)
                .withUrl("funny://page/second")
                .build()
                .open();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {

        }
    }
}
