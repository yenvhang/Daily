package com.example.creep.daily.util;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import static com.example.creep.daily.R.id.webview;

/**
 * Created by creep on 2016/9/11.
 */

public class WebViewHelper {
    private static final String TAG ="WebViewHelper";
    public static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private static final int MAX_SIZE = 30 * 1024 * 1024;//最大缓存30M
    public static void  setViewConfig(WebView webView){
        setWebViewCachePath(webView);
    }

    private static void setWebViewCachePath(WebView webview) {
        if(webview==null){
            return;
        }
        WebSettings settings =webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        String cacheDirPath = ConfigUtil.getCacheDir()
                + APP_CACHE_DIRNAME;
        Log.i(TAG, "cachePath=" + cacheDirPath);
        settings.setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        settings.setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);
        settings.setAppCacheMaxSize(MAX_SIZE);
    }
}
