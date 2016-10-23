package com.css.demo_one;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.wenming.library.BackgroundUtil;
import com.wenming.library.MyApplication;

public class MainActivity extends Activity {

    private static final String PACKAGE_NAME = "com.css.demo_one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //使用方法一
                    Boolean isForeground1 = BackgroundUtil.getRunningTask(MainActivity.this, PACKAGE_NAME);
                    Log.d("jkcss", "isForeground1 " + isForeground1);
                    //使用方法二
                    Boolean isForeground2 = BackgroundUtil.getRunningAppProcesses(MainActivity.this, PACKAGE_NAME);
                    Log.d("jkcss", "isForeground2 " + isForeground2);

                    //使用方法三
                    Boolean isForeground3 = BackgroundUtil.getApplicationValue((MyApplication) getApplication());
                    Log.d("jkcss", "isForeground3 " + isForeground3);

                    //使用方法四
                    Boolean isForeground4 = BackgroundUtil.queryUsageStats(MainActivity.this, PACKAGE_NAME);
                    Log.d("jkcss", "isForeground4 " + isForeground4);

                    //使用方法五
                    Boolean isForeground5 = BackgroundUtil.getFromAccessibilityService(MainActivity.this, PACKAGE_NAME);
                    Log.d("jkcss", "isForeground5 " + isForeground5);

                    //使用方法六
                    Boolean isForeground6 = BackgroundUtil.getLinuxCoreInfo(MainActivity.this, PACKAGE_NAME);
                    Log.d("jkcss", "isForeground6 " + isForeground6);

                }
            }
        }).start();
    }
}
