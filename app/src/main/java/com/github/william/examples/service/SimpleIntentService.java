package com.github.william.examples.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wgutierrez on 1/10/17.
 */

public class SimpleIntentService extends IntentService {

    private String TAG = "SimpleIntentService";

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Log.d(TAG, "onHandleIntent: Finish Task");

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

}
