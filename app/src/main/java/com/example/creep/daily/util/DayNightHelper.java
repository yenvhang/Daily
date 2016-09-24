package com.example.creep.daily.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.creep.daily.app.app;

/**
 * Created by creep on 2016/9/20.
 */

public class DayNightHelper {
    //mode 0 :day mode 1:night
    public static SharedPreferences mSharedPreferences= app.getContext()
            .getSharedPreferences(app.getContext().getPackageName(), Context.MODE_PRIVATE) ;
    public static boolean isDay(){
        return mSharedPreferences.getInt("mode",0)==0;
    }
    public static void setMode(int mode){
        SharedPreferences.Editor editor =mSharedPreferences.edit();
        editor.putInt("mode",mode);
        editor.apply();
    }

}
