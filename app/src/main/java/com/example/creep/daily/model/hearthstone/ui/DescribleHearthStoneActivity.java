package com.example.creep.daily.model.hearthstone.ui;


import android.os.Bundle;

import android.support.v4.view.ViewCompat;
import android.view.View;


import android.webkit.WebView;


import com.example.creep.daily.BaseWebActivity;

import com.example.creep.daily.app.app;
import com.example.creep.daily.http.UrlCache;
import com.example.creep.daily.model.hearthstone.BaseWebViewClient;

/**
 * Created by creep on 2016/9/8.
 */

public class DescribleHearthStoneActivity extends BaseWebActivity{
    private UrlCache urlCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("炉石传说");
        ViewCompat.setTransitionName(imageView,"iv_head");

    }

    @Override
    protected android.webkit.WebViewClient getWebViewClient() {
        return new BaseWebViewClient(app.getActivity());
    }

    @Override
    protected android.webkit.WebChromeClient getChromeClient() {
        return new WebChromeClient();
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            view.loadUrl("javascript: document.getElementsByClassName(\"lazy\")[0].remove();" );
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() ==View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }
//    public class BaseWebViewClient extends android.webkit.BaseWebViewClient{
//        @Override
//        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//            String url =request.getUrl().toString();
//            if(url.contains("time=")){
//                url =url.substring(0,url.indexOf("="));
//                urlCache.register(url, Base64.encodeToString(url.getBytes(),Base64.DEFAULT).replace("/","="),"","",UrlCache.ONE_DAY);
//                return urlCache.load(url);
//            }
//
//            if(url.startsWith("http://static.iyingdi.com/common")){
//
//            }
//            return super.shouldInterceptRequest(view, request);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//
//            super.onPageFinished(view, url);
//        }
//    }






}
