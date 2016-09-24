package com.example.creep.daily.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.webkit.WebView;

import com.example.creep.daily.R;
import com.example.creep.daily.app.app;
import com.example.creep.daily.data.JueJinData;
import com.example.creep.daily.data.ZhiHuArticle;
import com.example.creep.daily.data.ZhiHuData;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.http.HSFactory;
import com.example.creep.daily.model.hearthstone.BaseWebViewClient;
import com.example.creep.daily.model.hearthstone.data.ArticleData;
import com.example.creep.daily.model.home.ui.HomeActivity;
import com.example.creep.daily.util.DayNightHelper;
import com.example.creep.daily.util.WebUtil;
import com.example.creep.daily.util.WebViewHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/21.
 */

public class OffLineDownLoadService extends Service {
    private List<String> urls =new ArrayList<>();
    private List<ZhiHuArticle> zhihuArticles =new ArrayList<>();
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private WebView webview;
    Map<String,Integer> maps;
    private BaseWebViewClient webviewClient;
    NotificationCompat.Builder builder;
    private NotificationManager manager;
    private HashMap<String,String> juejins;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        params= new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.width = 1;
        params.height = 1;
        webviewClient =new BaseWebViewClient(app.getActivity());
        initWebView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        windowManager.addView(webview,params);
        initNotif();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
        initParams();
        ApiManager.getInstance().getHearthStoneApi().getArticleData(maps)
                .map(articleData -> articleData.getArticles())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lists -> {
                            for (int i = 0; i < lists.size(); i++) {
                                String url = HSFactory.getDesURL(Integer.parseInt(lists.get(i).get(0)));
                                urls.add(url);
                            }
                        }, throwable -> {
                        }, () ->
                    ApiManager.getInstance().getJueJinApi()
                            .getAndroidData(juejins)
                            .map(JueJinData::getResults)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(resultsBeen -> {
                                        for (int i = 0; i < resultsBeen.size(); i++) {
                                            urls.add(resultsBeen.get(i).getUrl());
                                        }
                                    }, throwable -> {
                                    }, () -> ApiManager.getInstance().getZhiHuApi()
                                            .getLastData()
                                            .map(zhiHuData -> zhiHuData.getStories())
                                            .flatMap(storiesBeen -> Observable.from(storiesBeen))
                                            .flatMap(new Func1<ZhiHuData.StoriesBean, Observable<ZhiHuArticle>>() {
                                                @Override
                                                public Observable<ZhiHuArticle> call(ZhiHuData.StoriesBean storiesBean) {
                                                    return ApiManager.getInstance().getZhiHuApi().getZhihuStory(String.valueOf(storiesBean.getId()));
                                                }

                                            })
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribeOn(Schedulers.io())
                                            .subscribe(zhiHuArticle -> urls.add(zhiHuArticle.getShare_url()),
                                                    throwable -> {
                                                    }, () -> loadWebPage())
                    )
                );







        return super.onStartCommand(intent, flags, startId);

    }

    private void initParams() {
        maps = new HashMap();
        maps.put(HSFactory.TIMESTAMP_CODE,0);
        maps.put(HSFactory.TOKEN_CODE,null);
        maps.put(HSFactory.SIZE_CODE,HSFactory.DEFAULT_SIZE);
        maps.put(HSFactory.MODULE_CODE,HSFactory.DEFAULT_MODULE);
        maps.put(HSFactory.VERSION_CODE,HSFactory.DEFAULT_VERSION);

        juejins =new HashMap<>();
        juejins.put("where","{\"tags\":{\"__type\":\"Pointer\",\"className\":\"Tag\",\"objectId\":\"5597838ee4b08a686ce2319d\"}}");
        juejins.put("include","user");
        juejins.put("order","-rankIndex");
    }

    private void initNotif() {
        builder =new NotificationCompat.Builder(this)
                .setContentTitle("开始下载")
                .setContentTitle("离线下载")
                .setContentText("正在下载")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setProgress(100,0,false);
    }


    private void loadWebPage() {


        if(urls!=null&&urls.get(0)!=null) {
            Log.e("OFFLINE","beginLoad");
            webview.loadUrl(urls.get(0));
        }
    }

    private void initWebView() {
        webview  =new WebView(this);
        WebViewHelper.setViewConfig(webview);
        webviewClient.setLoadFinshCallback(new BaseWebViewClient.loadFinshCallback() {
            int i=1;
            @Override
            public void webPageLoadFinsh() {
                if(i<urls.size()&&urls.get(i)!=null){
                    manager.notify(0,  builder.setProgress(100,i*100/urls.size()-1,false).build());

                    webview.loadUrl(urls.get(i));
                }
                if(i==urls.size()-1){
                    builder.setContentText("下载完成");

                    Intent intent =new Intent(OffLineDownLoadService.this, HomeActivity.class);
                    PendingIntent pendingIntent =PendingIntent
                            .getActivity(OffLineDownLoadService.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    builder.setProgress(0,0,false);
                    manager.notify(0,builder.build());
                }
                i++;
            }
        });
        webview.setWebViewClient(webviewClient);
    }
}
