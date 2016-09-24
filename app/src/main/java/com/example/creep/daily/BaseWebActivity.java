package com.example.creep.daily;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.example.creep.daily.http.UrlCache;
import com.example.creep.daily.util.WebViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by creep on 2016/9/8.
 */

public class BaseWebActivity extends ToolbarActivity{

    public static final String HEAD_URL_CODE="url1";
    public static final String CONTENT_URL_CODE="url2";
    @Bind(R.id.webview)
    protected WebView webView;
    @Bind(R.id.progressbar)
    protected ProgressBar progressBar;
    @Bind(R.id.iv_head)
    protected ImageView imageView;
    @Bind(R.id.nestedscroll)
    protected NestedScrollView nestedScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        String uImg = getIntent().getStringExtra(HEAD_URL_CODE);
        String uContent = getIntent().getStringExtra(CONTENT_URL_CODE);
        initWebView();

        if(uImg !=null) {
            Glide.with(this).load(uImg).centerCrop().into(imageView);
        }
        if(uContent!=null) {
            webView.loadUrl(uContent);
        }
        loadWebData();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (oldScrollY<168){
                    imageView.setTranslationY(- oldScrollY);

                }
            }
        });
    }

    protected  void loadWebData(){};


    @Override
    protected int getContentViewId() {
        return R.layout.activity_baseweb_describle;
    }


    private void initWebView() {
        webView.setWebViewClient(getWebViewClient());
        webView.setWebChromeClient(getChromeClient());
        WebViewHelper.setViewConfig(webView);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

    }

    protected android.webkit.WebChromeClient getChromeClient() {
        return new DefaultWebChromeClient();
    }

    protected android.webkit.WebViewClient getWebViewClient() {
        return new DefaultWebViewClient();
    }

    public class DefaultWebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
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
    public class DefaultWebViewClient extends android.webkit.WebViewClient{
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            showError();
            super.onReceivedError(view, request, error);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack();
        }
        nestedScrollView.setVisibility(View.GONE);
        return super.onKeyDown(keyCode, event);
    }



    public void showError() {
        Snackbar.make(webView,"网络错误", Snackbar.LENGTH_INDEFINITE).setAction("重试", v -> webView.reload()).show();
    }

    @Override
    protected boolean canBack() {
        return true;
    }
    @Override
    protected void performNavigationClick() {
        this.finish();
    }


    @Override
    protected void onStop() {
        if(webView!=null) {
            webView.pauseTimers();
        }
        super.onStop();

    }


    @Override
    protected void onResume() {
        if(webView!=null){
            webView.resumeTimers();
        }
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        if(webView!=null){
            ((ViewGroup)webView.getParent()).removeView(webView);
            webView.destroy();
            webView=null;
        }
        super.onDestroy();

    }
}
