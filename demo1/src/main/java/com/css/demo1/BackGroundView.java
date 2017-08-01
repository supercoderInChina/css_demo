package com.css.demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author chenshaosheng E-mail:ex-chenshaosheng001@pingan.com.cn
 * @version 2.0.4
 */

public class BackGroundView extends View {
    private Paint paint;
    private Path path;
    public BackGroundView(Context context) {
        this(context,null);
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BackGroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    private void initPaint(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#FED423"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        path = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int x = getWidth();
        int y = getHeight();
        path.moveTo(0f, 0f);
        path.lineTo(0, 2*y/3);
        path.quadTo(x/2, 1.1f*y,x, 2*y/3);
        path.lineTo(x, 0);
        path.close();
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawPath(path, paint);
    }
}
