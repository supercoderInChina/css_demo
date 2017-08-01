package com.css.myview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author chenshaosheng E-mail:ex-chenshaosheng001@pingan.com.cn
 * @version 2.0.4
 */

public class Utils {

    public static int getWidth(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 高
     * @return
     */
    public static int getHeight(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

}
