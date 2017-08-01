package com.css.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A sample activity showing some of the functions of the progress bar
 */
public class MainActivity extends Activity {

    Jockey_Left jockey_left;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = (WebView) findViewById(R.id.webview);
        //WebView加载web资源
        webView.loadUrl("http://wifi.pingan.com.cn/pawf-uc/rest/ucSession/v2/heartLogin.do");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("jkcss checknet :", checkNet()+"");

            }
        }).start();


    }


    public static boolean checkNet() {
        boolean checkNetSuccess = false;
        String checkNetUrl = "http://www.baidu.com";
        int checkNetType = -1;
        if (!checkNetUrl.contains("baidu")) {
            // 非m.baidu.com 则需要添加时间参数
            // http://202.69.21.198:80/pawf-core/rest/v5/checkNet?t=1489025019780
            checkNetType = 1;
            checkNetUrl += System.currentTimeMillis();
        }
        Log.i("jkcss","checkNet :: " + checkNetUrl);
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(checkNetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(4 * 1000);
            connection.setReadTimeout(3 * 1000);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();
            int responseCode = connection.getResponseCode();

            if (checkNetType == -1 && 200 == responseCode) {
                Log.i("jkcss","checkNet baidu success");
                checkNetSuccess = true;
            } else if (checkNetType == 1 && 204 == responseCode){
                Log.i("jkcss","checkNet pingan success");
            } else {
                Log.i("jkcss", "checkNet fail");
            }
//            downLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception ignore) {
                }
            }
        }
        return checkNetSuccess;
    }

}
