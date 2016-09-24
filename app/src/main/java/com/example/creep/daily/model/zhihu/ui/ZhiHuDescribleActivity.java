package com.example.creep.daily.model.zhihu.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.example.creep.daily.BaseWebActivity;
import com.example.creep.daily.data.ZhiHuArticle;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.model.juejin.ui.JuejinDescribleActivity;
import com.example.creep.daily.util.DayNightHelper;
import com.example.creep.daily.util.WebUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/18.
 */

public class ZhiHuDescribleActivity extends BaseWebActivity {
    public static final String CONTENT_ID_URL_CODE ="id";
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        id =getIntent().getStringExtra(CONTENT_ID_URL_CODE);
        super.onCreate(savedInstanceState);
        setTitle("知乎日报");



    }

    @Override
    protected void loadWebData() {
        super.loadWebData();
        Subscription s =ApiManager.getInstance().getZhiHuApi()
                .getZhihuStory(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(zhiHuArticle -> {
                    showZhiHuArticle(zhiHuArticle);
                }, throwable -> {
                    Log.e("ZHIHU",Log.getStackTraceString(throwable));
                    showError();
                });
        getCompositeSubscription().add(s);
    }


    private void showZhiHuArticle(ZhiHuArticle zhiHuArticle) {
        Glide.with(this).load(zhiHuArticle.getImage()).centerCrop().into(imageView);
        String url = zhiHuArticle.getShare_url();
        boolean isEmpty = TextUtils.isEmpty(zhiHuArticle.getBody());
        String mBody = zhiHuArticle.getBody();
        List<String> css = zhiHuArticle.getCss();
        if (isEmpty) {
            webView.loadUrl(url);
        } else {
            String data = WebUtil.buildHtmlWithCss(mBody, css.toArray(new String[css.size()]), !DayNightHelper.isDay());
            webView.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }



    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
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
            super.onPageFinished(view, url);
        }
    }

}
