package com.css.demo1;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDir("download", Context.MODE_PRIVATE);
        Log.d("jkcss", getDir("download", Context.MODE_PRIVATE).getPath());
        Log.d("jkcss", Environment.getExternalStorageDirectory().getPath());
        tvTest = (TextView) findViewById(R.id.test);
        tvTest.setMovementMethod(ScrollingMovementMethod.getInstance());





    }

}
