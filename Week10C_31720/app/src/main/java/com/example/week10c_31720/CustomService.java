package com.example.week10c_31720;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class CustomService extends Service {
    public CustomService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.i("CUSTOMSERVICE","onCreate : CustomService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Log.i("CUSTOMSERVICE", "onStartCommand : "+startId);
        //int n = (int) (Math.random()*50)+10;
        //try{
        //    for ( int i=0;i<n;i++){
        //        Thread.sleep(200);
        //        Log.i("CUSTOMSERVICE","onStartCommand : "+startId+" processing "+ ((int)((100*i)/(float)n))+"%");
        //    }
        //    Log.i("CUSTOMSERVICE","onStartCommand "+startId +" Clear");
        //}catch (InterruptedException e){
        //    e.printStackTrace();
        //}

        AsyncTask customServiceTask = new CustomServiceTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,startId);
        return  Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("CUSTOMSERVICE","onBind :  Service bind");
        return null;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("CUSTOMSERVICE","onDestroy :  Service Destroyed");
    }
}