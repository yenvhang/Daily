package com.example.creep.daily.http;

import com.example.creep.daily.API.HearthStoneApi;
import com.example.creep.daily.util.ConfigUtil;
import com.example.creep.daily.util.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by creep on 2016/9/7.
 */

public class HSRestrofit {
    public static final String ARTICLE_LIST ="article_list";
    private  Retrofit retrofit;
    private HearthStoneApi mhearthstoneService;
    private int maxsize =1024*1024*80;
    public HSRestrofit(){

        File cacheDir = new File(ConfigUtil.getCacheDir(),ARTICLE_LIST);
        Cache cache =new Cache(cacheDir,maxsize);
        Interceptor interceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request =chain.request();
                if(!NetWorkUtil.isNetWorkAvailable()) {
                    request =request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build() ;
                }
                return chain.proceed(request);
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
        retrofit =new Retrofit.Builder()
                .baseUrl(HSFactory.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mhearthstoneService =retrofit.create(HearthStoneApi.class);


    }



    public HearthStoneApi getHearthStoneService(){
        return mhearthstoneService;
    }
}
