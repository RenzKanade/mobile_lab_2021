package com.example.week10c_31720;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class SimpleIntentService extends IntentService {

    public SimpleIntentService(){
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("INTENTSERVICE","onHandleIntent: IntentService started!");
        int n = (int) (Math.random()*50)+10;
        try{
            for(int i = 0;i<n ;i++){
                Thread.sleep(200);
                Log.i("INTENTSERVICE","onHandleIntent: processing"+((int) ((100*i)/(float)n))+"%");
            }
            Log.i("INTENTSERVICE","onHandleIntent: Clear");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
