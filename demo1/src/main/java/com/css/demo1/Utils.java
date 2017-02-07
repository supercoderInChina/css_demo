package com.css.demo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author chenshaosheng E-mail:ex-chenshaosheng001@pingan.com.cn
 * @version 2.0.4
 */

public class Utils {

    private enum Page {
        map, service, eventChannel, discover, personalCenter,
        freeData, freeFlowTactic, browser, video, home, messageCenter,
        accountSetting, zeroflow, jfDetail, signHaveGift, flowQuery, doTask,
        walletOpenAccount, walletRecharge, walletWithdraw, cardDetail, fasttransmit;
        public static Page getPage(String page){
            return valueOf(page);
        }
    }
    public static void testCompare() {
        //        String str = "Hello Worldq";
        Page md5_one = Page.getPage("walletWithdraw");
        //        String md5_two = getMD5Code(str);

    }

    public static void thread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
            }
        }).start();
    }

    public static void bohao(Context context){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + 1111111111));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void postd(){
        int count = 0;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("jkcss", "1");
            }
        }, 1000);
    }

    public static void url(){
        Uri url = Uri.parse("custom:123sdfsdf");
        String a = url.getScheme();
//        String a = url.getQueryParameter("page");
        Log.d("jkcss " , a+"");
        Log.d("jkcss " , url+"");
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

    public static void imsi(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager sm = SubscriptionManager.from(context);
            List<SubscriptionInfo> mActiveSubscriptionInfoList = sm.getActiveSubscriptionInfoList();
            for(SubscriptionInfo info: mActiveSubscriptionInfoList){
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String imsi = getSubscriberId(context, info.getSubscriptionId());
                Log.d("jkcss " , imsi);
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


    public static void stringArrayChannge(String ...arr){
//        String [] arr = aa;
        for(int s = 0; s < arr.length; s++){
            if(arr[s] == null){
                arr[s] = "@";
            }
        }

        for(String a: arr){
            Log.d("jkcss", a+"");
        }
    }


    public static void sharedutil(Context context){
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

            Log.d("jkcss", bb+"@@@"+aa);
            if ((bb+"").equals(aa)) {
              ss = false;
            }

            bb ++;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rxjava(){

//        Observable.just("one", "two")
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s + "~~~";
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Log.d("jkcss", s);
//                    }
//                });


        ArrayList a = new ArrayList();

        ArrayList b = new ArrayList();
        ArrayList c = new ArrayList();
        a.add(b);
        a.add(c);
        b.add("b1");
        c.add("c1");


        Observable.from(a)
                .flatMap(new Func1<ArrayList, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(ArrayList s) {
                        return Observable.just(s.get(0));
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object s) {
                Log.d("jkcss", s+"");
            }
        });
    }
}
