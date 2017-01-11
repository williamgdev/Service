package com.github.william.examples.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bStartSimpleServie;
    boolean ssstarted;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bStartSimpleServie = (Button) findViewById(R.id.main_bSimpleService);
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
}
