package com.example.user.broadcast;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by user on 2016/4/13.
 */
public class BaseActivity extends Activity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
