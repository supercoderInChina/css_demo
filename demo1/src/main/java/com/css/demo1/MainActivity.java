package com.css.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import groovy.lang.GroovyShell;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mMin;
    private SurfaceView mBig;
    private FrameLayout mMfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GroovyShell.main(new String[]{"1", "2"});

        ///2

    }

}
