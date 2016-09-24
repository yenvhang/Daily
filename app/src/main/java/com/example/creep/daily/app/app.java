package com.example.creep.daily.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.renderscript.RenderScript;

import com.example.creep.daily.CrashHandler;
import com.example.creep.daily.http.UrlCache;
import com.example.creep.daily.util.ConfigUtil;
import com.example.creep.daily.util.NetWorkUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by creep on 2016/9/7.
 */

public class app extends Application {
    public static final String  img_head_empty="empty";
    private static Context mCOntext;
    private static Application activity;
    private static  UrlCache urlCache;
    @Override
    public void onCreate() {
        super.onCreate();
        mCOntext =this;
        activity=this;
        ConfigUtil.init(this,getCacheDir());
        NetWorkUtil.init(this);
        urlCache =new UrlCache(this);
        CrashHandler.getInstance().init(this);

    }

    public static UrlCache getUrlCache() {
        return urlCache;
    }

    public static Context getContext(){
        return mCOntext;
    };

    public static Application getActivity(){
        return activity;
    };
}
