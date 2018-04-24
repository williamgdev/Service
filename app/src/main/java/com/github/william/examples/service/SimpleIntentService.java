package com.github.william.examples.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
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
        int timeToWait = intent.getIntExtra(ServiceConstants.TIME_TO_WAIT, 5000);
        Log.d(TAG, "onHandleIntent: ");
        sendStatus(ServiceConstants.STATUS.STARTED);
        try {
            Thread.sleep(timeToWait);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Log.d(TAG, "onHandleIntent: Finish Task");
        sendStatus(ServiceConstants.STATUS.FINISHED);

    }

    private void sendStatus(ServiceConstants.STATUS status) {
        Intent intent = new Intent(ServiceConstants.BROADCAST_SERVICE_STATUS);
        intent.putExtra(ServiceConstants.BROADCAST_DATA_STATUS, status);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public static Intent buildIntent(Context context, int timeToWait) {
        Intent intent = new Intent(context, SimpleIntentService.class);
        intent.putExtra(ServiceConstants.TIME_TO_WAIT, timeToWait);
        return intent;

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
