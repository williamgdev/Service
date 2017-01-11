package com.github.william.examples.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button
            bStartSimpleServie,
            bStartBoundService;
    boolean ssstarted, bsstarted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStartSimpleServie = (Button) findViewById(R.id.main_bSimpleService);
        bStartBoundService = (Button) findViewById(R.id.main_bBoundService);
    }

    public void onStartIntentService(View view) {
        Intent intent = new Intent(this, SimpleIntentService.class);
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
        if (!bsstarted){
            bStartBoundService.setText(R.string.stop_boundservice);
            Intent intent = new Intent(this, BoundService.class);
            bindService(intent, mConnection, BIND_AUTO_CREATE);
        }
        else {
            bStartBoundService.setText(R.string.start_service);
            bsstarted = false;
            unbindService(mConnection);
        }
    }


    private BoundService mBoundService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mBoundService = binder.getService();
            bsstarted = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bsstarted = false;
        }
    };

}
