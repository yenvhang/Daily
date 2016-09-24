package com.example.creep.daily.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * Created by creep on 2016/9/12.
 */

public class NetWorkUtil {
    private static Context context;
    public static void init(Context context){
        NetWorkUtil.context =context;
    }
    public static boolean isNetWorkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
       if(networkInfo==null){
           return false;
       }
        return networkInfo.isAvailable();
    }

}
