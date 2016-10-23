package com.css.aidldemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("jkcss", Process.myPid()+"");


//        ServiceConnection serviceConnection = new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
////                try {
////                    IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
////                    int b = iMyAidlInterface.plus(1, 5);
////                    String a = iMyAidlInterface.toUpperCase("llllll");
////                    Log.d("jkcss", a + b);
////
////                } catch (RemoteException e) {
////                    e.printStackTrace();
////                }
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//
//            }
//        };

//        Intent in = new Intent(this, MyService.class);
//        bindService(in, serviceConnection, BIND_AUTO_CREATE);
    }
}
