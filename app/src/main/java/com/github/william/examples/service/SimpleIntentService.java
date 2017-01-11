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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }


}
