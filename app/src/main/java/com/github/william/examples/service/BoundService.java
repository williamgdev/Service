package com.github.william.examples.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by wgutierrez on 1/10/17.
 */

public class BoundService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private String TAG = "BoundService";

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder{
        BoundService getService(){
            Log.d(TAG, "getService: ");
            return BoundService.this;
        }
    }

    public String doMagic(){
        return "Hello from the BoundService";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }
}


