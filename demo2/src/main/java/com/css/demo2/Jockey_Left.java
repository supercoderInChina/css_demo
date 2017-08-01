package com.css.demo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class Jockey_Left extends View{

    private Bitmap bitmap;
    public Point mRockerPosition;
    public Point mCtrlPoint;
    private int mRudderRadius = 25;
    public int mWheelRadius = 80;
    private float scale;
    public int isHide = 0;
    private Paint mPaint;
    public Jockey_Left(Context context) {
        super(context);
    }

    public Jockey_Left(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(float scale){
        this.scale = scale;
        mRudderRadius = dip2px(15);
        mWheelRadius = dip2px(45);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vpn_switch_off);
        bitmap = Bitmap.createScaledBitmap(bitmap, mRudderRadius*2, mRudderRadius*2, false);
        mCtrlPoint = new Point((mRudderRadius + mWheelRadius), (mRudderRadius + mWheelRadius));
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRockerPosition = new Point(mCtrlPoint);
    }
    public int dip2px(float dpValue) {
        return (int)(dpValue * scale + 0.5f);
    }
    public Canvas canvas;
    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            //canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);//�����Ļ
            this.canvas = canvas;
            canvas.drawBitmap(bitmap, mRockerPosition.x - mRudderRadius, mRockerPosition.y - mRudderRadius, mPaint);
        }
        super.onDraw(canvas);
    }

    int len;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (isHide == 0) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        len = Utils.getLength(mCtrlPoint.x, mCtrlPoint.y, event.getX(), event.getY());
                        //如果屏幕接触点不在摇杆挥动范围内,则不处理
                        if(len > mWheelRadius) {
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        len = Utils.getLength(mCtrlPoint.x, mCtrlPoint.y, event.getX(), event.getY());
                        if(len <= mWheelRadius) {
                            //如果手指在摇杆活动范围内，则摇杆处于手指触摸位置
                            mRockerPosition.set((int)event.getX(), (int)event.getY());
                        }else{
                            //设置摇杆位置，使其处于手指触摸方向的 摇杆活动范围边缘
                            mRockerPosition = Utils.getBorderPoint(mCtrlPoint, new Point((int)event.getX(), (int)event.getY()), mWheelRadius);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mRockerPosition = new Point(mCtrlPoint);
                        break;
                }
                Thread.sleep(40);
            }else {
                Thread.sleep(200);
            }
        } catch (Exception e) {

        }
        invalidate();//更新布局
        return true;
    }



}

