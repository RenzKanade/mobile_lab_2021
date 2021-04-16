package com.example.week10c_31720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CustomBoundService customBoundService;
    boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomBoundService.CustomLocalBinder binder = (CustomBoundService.CustomLocalBinder) service;
            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serIntent = new Intent(this,SimpleIntentService.class);
        startService(serIntent);

        Button btnStartServ = findViewById(R.id.btnStartService);
        btnStartServ.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomService.class);
                startService(intent);
            }
        });

        Intent intent2 = new Intent(this,CustomBoundService.class);
        bindService(intent2,serviceConnection, Context.BIND_AUTO_CREATE);

        Button showTime = findViewById(R.id.btnTimeService);
        showTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curTime = customBoundService.getCurrentTime();
                Toast.makeText(getApplicationContext(),curTime,Toast.LENGTH_LONG).show();
            }
        });
    }
}