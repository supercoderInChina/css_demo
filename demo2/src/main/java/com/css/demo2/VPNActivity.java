package com.css.demo2;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.util.Log;

public class VPNActivity extends Activity {
    private static final int REQUEST_CONNECT = 20012;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("jkcss", "VPNActivity start");
        Intent intent = VpnService.prepare(VPNActivity.this.getApplicationContext());
        /**
         * if intent is null that vpnService has already prepare <br/>
         * or the user has previously consented to the VPN application
         */
        if (intent != null) {
            Log.d("jkcss", "prepareStartService intent != null");
                startActivityForResult(intent, REQUEST_CONNECT);

        } else {
            Log.d("jkcss", "prepareStartService intent == null");
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        Log.d("jkcss","kk====onCreate 2====");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("jkcss","kk====onActivityResult====");

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONNECT) {
            Log.d("jkcss", "VPNActivity start resultCode = " + resultCode);
            finish();
        }
    }
}
