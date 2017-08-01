package com.css.demo2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.Uri;
import android.os.Handler;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.io.DataOutputStream;
import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.List;

/**
 * @author chenshaosheng E-mail:ex-chenshaosheng001@pingan.com.cn
 * @version 2.0.4
 */

public class Utils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private enum Page {
        map, service, eventChannel, discover, personalCenter,
        freeData, freeFlowTactic, browser, video, home, messageCenter,
        accountSetting, zeroflow, jfDetail, signHaveGift, flowQuery, doTask,
        walletOpenAccount, walletRecharge, walletWithdraw, cardDetail, fasttransmit;

        public static Page getPage(String page) {
            return valueOf(page);
        }
    }

    public static void testCompare() {
        //        String str = "Hello Worldq";
        Page md5_one = Page.getPage("walletWithdraw");
        //        String md5_two = getMD5Code(str);

    }

    public static void thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
            }
        }).start();
    }

    public static void bohao(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 1111111111));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void postd() {
        int count = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("jkcss", "1");
            }
        }, 1000);
    }

    public static void url() {
        Uri url = Uri.parse("custom:123sdfsdf");
        String a = url.getScheme();
        //        String a = url.getQueryParameter("page");
        Log.d("jkcss ", a + "");
        Log.d("jkcss ", url + "");
    }


    public static void syncCookie(Context context) {
        //        UserData userData = AuthMgr.getInstance().getUserData();
        //        if (userData != null) {
        //            Lg.d("WebView USER DATA --> " + AuthMgr.getInstance().getUserData());
        //        }

        //        String jsessionid = userData == null ? "" : userData.jsessionid;
        //        Lg.d("synchronize cookie: " + jsessionid);
        if (true) {
            //            String[] domains = context.getResources().getStringArray(R.array.web_domains);
            CookieSyncManager cs = CookieSyncManager.createInstance(context);
            CookieManager cm = CookieManager.getInstance();
            CookieManager.setAcceptFileSchemeCookies(true);
            cm.setAcceptCookie(true);
            //            String v;
            //            for (String domain : domains) {
            //                v = "wifi_jsessionid=" + jsessionid;
            //                cm.setCookie(domain, v);
            //                Lg.d("domain: " + domain);
            //            }
            cm.setCookie(".pingan.com", "wifi_jsessionid=c09b0f507f124b7cb99a142aa8c18c22");
            cm.setCookie(".pingan.com.cn", "wifi_jsessionid=c09b0f507f124b7cb99a142aa8c18c22");
            cm.setCookie(".baidu.com", "wifi_jsessionid=c09b0f507f124b7cb99a142aa8c18c22");

            cs.sync();

        }
    }

    public static void imsi(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager sm = SubscriptionManager.from(context);
            List<SubscriptionInfo> mActiveSubscriptionInfoList = sm.getActiveSubscriptionInfoList();
            for (SubscriptionInfo info : mActiveSubscriptionInfoList) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imsi = getSubscriberId(context, info.getSubscriptionId());
                Log.d("jkcss ", imsi);
            }
        }
    }


    //获取imsi
    private static String getSubscriberId(Context context, int subId) {
        String subscriberId = null;

        try {
            Method method = TelephonyManager.class.getDeclaredMethod("getSubscriberId", int.class);

            subscriberId = (String) method.invoke(context.getSystemService(Context.TELEPHONY_SERVICE), subId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return subscriberId;
    }


    public static void stringArrayChannge(String... arr) {
        //        String [] arr = aa;
        for (int s = 0; s < arr.length; s++) {
            if (arr[s] == null) {
                arr[s] = "@";
            }
        }

        for (String a : arr) {
            Log.d("jkcss", a + "");
        }
    }


    public static void sharedutil(Context context) {
        boolean ss = true;
        int bb = 1;
        while (ss) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("jkcss", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("jkcss", bb + "");
            editor.apply();

            SharedPreferences.Editor editor2 = sharedPreferences.edit();
            editor2.clear();
            editor2.apply();

            String aa = sharedPreferences.getString("jkcss", "###");

            Log.d("jkcss", bb + "@@@" + aa);
            if ((bb + "").equals(aa)) {
                ss = false;
            }

            bb++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static String getPackageSign(Context mContext) {
        String md5 = null;
        try {
            PackageManager pm = mContext.getPackageManager();
            PackageInfo packeInfo = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);
            Signature signature = packeInfo.signatures[0];
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(signature.toByteArray());
            byte[] digest = md.digest();
            md5 = toHexString(digest);
            Log.d("jkcss", "md5:" + md5);
        } catch (Exception e) {
        }

        return md5;
    }

    private static String toHexString(byte[] block) {
        StringBuffer buf = new StringBuffer();
        int len = block.length;
        for (int i = 0; i < len; i++) {
            byte2hex(block[i], buf);
            if (i < len - 1) {
                buf.append(":");
            }
        }
        return buf.toString();
    }

    private static void byte2hex(byte b, StringBuffer buf) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int high = ((b & 0xf0) >> 4);
        int low = (b & 0x0f);
        buf.append(hexChars[high]);
        buf.append(hexChars[low]);
    }

    public static int getLength(float x1, float y1, float x2, float y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static Point getBorderPoint(Point a, Point b, int cutRadius) {
        float radian = getRadian(a, b);
        return new Point(a.x + (int) (cutRadius * Math.cos(radian)), a.x + (int) (cutRadius * Math.sin(radian)));
    }

    public static float getRadian (Point a, Point b) {
        float lenA = b.x-a.x;
        float lenB = b.y-a.y;
        float lenC = (float)Math.sqrt(lenA*lenA+lenB*lenB);
        float ang = (float)Math.acos(lenA/lenC);
        ang = ang * (b.y < a.y ? -1 : 1);
        return ang;
        }

    /**获取虚拟功能键高度 */
    public static int getVirtualBarHeigh(Context context) {
        int vh = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - windowManager.getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }

    public static void getimsi(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static boolean isRoot() {

        boolean root = false;

        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }

        } catch (Exception e) {
        }

        return root;
    }

    public static boolean RootCommand(String command) {

        Process process = null;
        DataOutputStream os = null;

        try {

            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();

        } catch (Exception e) {
            Log.d("*** DEBUG ***", "ROOT REE" + e.getMessage());
            return false;

        } finally {

            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }

        Log.d("*** DEBUG ***", "Root SUC ");
        return true;

    }

}
