package com.github.william.examples.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button
            bStartSimpleServie,
            bStartBoundService;
    boolean ssstarted, bsStarted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStartSimpleServie = (Button) findViewById(R.id.main_bSimpleService);
        bStartBoundService = (Button) findViewById(R.id.main_bBoundService);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String status = String.valueOf(intent.getExtras().get(ServiceConstants.BROADCAST_DATA_STATUS));
                showMessage(status);
            }
        }, new IntentFilter(ServiceConstants.BROADCAST_SERVICE_STATUS));
    }

    public void onStartIntentService(View view) {
        Intent intent = SimpleIntentService.buildIntent(this, 5000);
        startService(intent);
    }

    public void onStartSimpleService(View view) {
        if (!ssstarted){
            bStartSimpleServie.setText(R.string.stop_service);
            ssstarted = true;
            Intent intent = new Intent(this, SimpleService.class);
            startService(intent);
        }
        else {
            bStartSimpleServie.setText(R.string.start_service);
            ssstarted = false;
            Intent intent = new Intent(this, SimpleService.class);
            stopService(intent);
        }
    }


    public void onStartBoundService(View view) {
        switchButton();
    }

    private void switchButton() {
        if (!bsStarted) {
            bStartBoundService.setText(R.string.stop_boundservice);
            Intent intent = new Intent(this, BoundService.class);
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        } else {
            bStartBoundService.setText(R.string.start_boundservice);
            bsStarted = false;
            unbindService(mConnection);
        }
    }


    private BoundService mBoundService;
    private String Tag = "ServiceConnection";
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mBoundService = binder.getService();
            bsStarted = true;
            Log.d(Tag, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            switchButton();
            Log.d(Tag, "onServiceDisconnected: ");
        }
    };

    public void doMagic(View view) {
        if (bsStarted) {
            showMessage(mBoundService.doMagic());
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
