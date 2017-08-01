package com.css.demo2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;

@SuppressLint("ViewConstructor")
public class AppSingleRocker extends SurfaceView implements Callback{
    private SurfaceHolder mHolder;
    private Paint mPaint;
    public Point mRockerPosition; // 摇杆位置
    private Point mCtrlPoint;// 摇杆起始位置
    private int mRudderRadius = 25;// 摇杆半径
    private int mWheelRadius = 80;// 摇杆活动范围半径
    private int batmapHW = 100;
    private int batmap2HW = 40;
    int isHide = 0;
    Bitmap bitmap,bitmap2;
    float scale;
    private SingleRudderListener listener = null; //事件回调接口
    public static final int ACTION_RUDDER = 1, ACTION_ATTACK_DEVICEMOVE = 2, ACTION_STOP = 3,  ACTION_ATTACK_CAMERAMOVE = 4;
    public AppSingleRocker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setKeepScreenOn(true);
        scale = context.getResources().getDisplayMetrics().density;
        mRudderRadius = dip2px(15);// 摇杆半径
        mWheelRadius = dip2px(45);// 摇杆活动范围半径
        mCtrlPoint = new Point((mRudderRadius + mWheelRadius), (mRudderRadius + mWheelRadius));// 摇杆起始位置
        batmapHW = (mWheelRadius+mRudderRadius) * 2;
        batmap2HW = mRudderRadius * 2;
        mHolder = getHolder();
        mHolder.addCallback(this);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        mRockerPosition = new Point(mCtrlPoint);
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSPARENT);//设置背景透明
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vpn_switch_off);
        bitmap = Bitmap.createScaledBitmap(bitmap, batmapHW, batmapHW, false);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.white_btn);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2,batmap2HW,batmap2HW, false);
    }

    //获取屏幕的宽度，高度和密度以及dp / px
    public void getDisplayMetrics() {

    }
    public int dip2px(float dpValue) {
        return (int)(dpValue * scale + 0.5f);
    }
    //回调接口
    public interface SingleRudderListener {
        void onSteeringWheelChanged(int action,int angle);
    }

    //设置回调接口
    public void setSingleRudderListener(SingleRudderListener rockerListener) {
        listener = rockerListener;
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
                        if(listener != null) {
                            float radian = Utils.getRadian(mCtrlPoint, new Point((int)event.getX(), (int)event.getY()));
                            listener.onSteeringWheelChanged(ACTION_RUDDER, getAngleCouvert(radian));
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mRockerPosition = new Point(mCtrlPoint);
                        if (listener != null) {
                            listener.onSteeringWheelChanged(ACTION_STOP, 0);
                        }
                        break;
                }
                Canvas_OK();
                Thread.sleep(60);
            }else {
                Thread.sleep(200);
            }
        } catch (Exception e) {

        }
        return true;
    }

    public void singleRockerUp(){
        mRockerPosition = new Point(mCtrlPoint);
        listener.onSteeringWheelChanged(ACTION_STOP, 0);
    }
    //获取摇杆偏移角度 0-360°
    private int getAngleCouvert(float radian) {
        int tmp = (int)Math.round(radian/Math.PI * 180);
        if(tmp < 0) {
            return -tmp;
        }else{
            return 180 + (180 - tmp);
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Canvas_OK();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    // 设置是否隐藏
    public void Hided(int opt)
    {
        isHide = opt;
        Canvas_OK();
    }

    public void setHided(int opt){
        isHide = opt;
    }
    /**
     * 返回圆盘是否隐藏
     * @return
     */
    public int getIsHided(){
        return isHide;
    }
    //绘制图像
    public void Canvas_OK(){
        Canvas canvas = null;
        try {
            if (isHide == 0) {
                canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);//清除屏幕
                canvas.drawBitmap(bitmap, mCtrlPoint.x - mWheelRadius - mRudderRadius, mCtrlPoint.y - mWheelRadius - mRudderRadius, mPaint);
                canvas.drawBitmap(bitmap2, mRockerPosition.x - mRudderRadius, mRockerPosition.y - mRudderRadius, mPaint);
            }else {
                canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);//清除屏幕
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(canvas != null) {
                mHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}

