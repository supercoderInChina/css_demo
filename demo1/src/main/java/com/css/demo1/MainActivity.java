package com.css.demo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mMin;
    private SurfaceView mBig;
    private FrameLayout mMfl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tv);
        String a = (String) tv.getText();
        Spanny spanny = new Spanny(a);

        spanny.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "11111", Toast.LENGTH_LONG).show();
            }
        }, 8, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        spanny.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_retry_flow))
                , 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tv.setText(spanny);
    }

}
