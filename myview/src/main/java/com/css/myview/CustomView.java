package com.css.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author chenshaosheng E-mail:ex-chenshaosheng001@pingan.com.cn
 * @version 2.0.4
 */

public class CustomView extends View {

    private Context mContext;
    private Canvas canvas;
    private Paint mPaint;
    private float radius = 100;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initPaint();
        initCanvas();
    }

    private void initCanvas() {
        canvas = new Canvas();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(Utils.getWidth(mContext) / 2, Utils.getHeight(mContext) / 2, radius, mPaint);
        radius = radius + 10;
        if(radius == 310f){
            radius = 100;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }
}
