package com.example.creep.daily.model.hearthstone;

import android.app.Application;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.example.creep.daily.app.app;
import com.example.creep.daily.http.UrlCache;

public class BaseWebViewClient extends android.webkit.WebViewClient {
    loadFinshCallback loadFinshCallback;
    public interface loadFinshCallback{
        void webPageLoadFinsh();
    }

    public void setLoadFinshCallback(BaseWebViewClient.loadFinshCallback loadFinshCallback) {
        this.loadFinshCallback = loadFinshCallback;
    }

    public BaseWebViewClient.loadFinshCallback getLoadFinshCallback() {
        return loadFinshCallback;
    }

    private final UrlCache urlCache;

    public BaseWebViewClient(Application activity){
        urlCache = app.getUrlCache();
    }
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        WebResourceResponse response;
        String url = request.getUrl().toString();
//        Log.e("webviewclient", url);
//        if(url.contains("content")){
//            return null;
//        }
//        if(url.contains("png")){
//            return null;
//        }
//        if(url.contains("jpeg")){
//            return null;
//        }

        if (url.contains("time=")) {
            url = url.substring(0, url.indexOf("="));


            urlCache.register(url, Base64.encodeToString(url.getBytes(), Base64.DEFAULT).replace("/", "="),
                    "text/html", "gzip", UrlCache.ONE_DAY);
            return urlCache.load(url);
        }
        return super.shouldInterceptRequest(view,request);

    }


    @Override
    public void onPageFinished(WebView view, String url) {
        Log.e("webViewClient","load finish"+view.getUrl());
        super.onPageFinished(view, url);
        if(loadFinshCallback!=null) {
            loadFinshCallback.webPageLoadFinsh();
        }
    }
}