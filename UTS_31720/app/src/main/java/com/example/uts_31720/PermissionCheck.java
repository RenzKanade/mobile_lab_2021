package com.example.uts_31720;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class PermissionCheck {
    public static boolean CheckPermission(Context context, String... permissions){
        for (String permission : permissions){
            if(!CheckPermission(context,permission)){
                return false;
            }
        }
        return true;
    }

    public static boolean CheckPermission(Context context, String permission){
        return  ActivityCompat.checkSelfPermission(context,permission)== PackageManager.PERMISSION_GRANTED;
    }

    public static void RequestPermission(Activity activity,int requestCode,String... permissions){
        ActivityCompat.requestPermissions(activity,permissions,requestCode);
    }
}
