package com.css.css;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by changyu on 16/9/2.
 */
public class DualSIMGetProvider {
    public static String getProviderName(Context context) {
        String providerName = "未知";
        //                tv_sim1.setText("sim1 = "+getIMSI(MainActivity.this));
        String net = getNetWork(context);
        List<String> infos = getNetWorkList(context);
        if (net == null || net.equals("WIFI")) {
            if (infos.size() > 1) {
                infos.remove("WIFI");
                net = infos.get(0);
                if (net.equals("3gwap") || net.equals("uniwap")
                        || net.equals("3gnet") || net.equals("uninet")) {
                    providerName = "联通";
                } else if (net.equals("cmnet") || net.equals("cmwap")) {
                    providerName = "移动";
                } else if (net.equals("ctnet") || net.equals("ctwap")) {
                    providerName = "电信";
                }
            }
        } else {
            if (net.equals("3gwap") || net.equals("uniwap")
                    || net.equals("3gnet") || net.equals("uninet")) {
                providerName = "联通";
            } else if (net.equals("cmnet") || net.equals("cmwap")) {
                providerName = "移动";
            } else if (net.equals("ctnet") || net.equals("ctwap")) {
                providerName = "电信";
            }
        }
        return providerName;
    }

    /**
     * 获取当前网络名称；
     *
     * @param context
     * @return
     */
    public static List<String> getNetWorkList(Context context) {


        List<String> list = new ArrayList<String>();
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo[] networkInfos = manager.getAllNetworkInfo();

            if (networkInfos != null) {
                for (int i = 0; i < networkInfos.length; i++) {
                    NetworkInfo info = networkInfos[i];
                    String infoName = null;
                    if (info.getTypeName().equals("WIFI")) {
                        infoName = info.getTypeName();
                    } else {
                        infoName = info.getExtraInfo();
                    }
                    if (infoName != null && list.contains(infoName) == false) {
                        list.add(infoName);
                        // System.out.println(name);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getNetWork(Context context) {
        String NOWNET = null;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            if (info.getTypeName().equals("WIFI")) {
                NOWNET = info.getTypeName();
            } else {
                NOWNET = info.getExtraInfo();// cmwap/cmnet/wifi/uniwap/uninet
            }
        }
        return NOWNET;


    }
}
