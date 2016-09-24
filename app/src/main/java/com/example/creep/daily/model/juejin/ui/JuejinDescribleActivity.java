package com.example.creep.daily.model.juejin.ui;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.creep.daily.BaseWebActivity;
import com.example.creep.daily.R;
import com.example.creep.daily.ToolbarActivity;
import com.example.creep.daily.app.app;
import com.example.creep.daily.http.UrlCache;
import com.example.creep.daily.model.hearthstone.BaseWebViewClient;
import com.example.creep.daily.util.WebViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by creep on 2016/9/8.
 */

public class JuejinDescribleActivity extends BaseWebActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("掘金");
    }

    @Override
    protected android.webkit.WebChromeClient getChromeClient() {
        return new WebChromeClient();
    }

    @Override
    protected android.webkit.WebViewClient getWebViewClient() {
        return new BaseWebViewClient(app.getActivity());
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            view.loadUrl("javascript: document.getElementsByClassName(\"post-hero\")[0].remove();");
            view.loadUrl("javascript: document.getElementsByClassName(\"entry-hero\")[0].remove();");
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript: document.getElementsByClassName(\"post-hero\")[0].remove();");
            view.loadUrl("javascript: document.getElementsByClassName(\"entry-hero\")[0].remove();");
            super.onPageFinished(view, url);
        }
    }
}