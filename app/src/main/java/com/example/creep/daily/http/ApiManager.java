package com.example.creep.daily.http;

import com.example.creep.daily.API.HearthStoneApi;
import com.example.creep.daily.API.JueJinApi;
import com.example.creep.daily.API.ZhiHuApi;
import com.example.creep.daily.util.ConfigUtil;
import com.example.creep.daily.util.NetWorkUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by creep on 2016/9/13.
 */

public class ApiManager {
    private static ApiManager apiManager;


    private static ZhiHuApi zhiHuApi;
    private static NewsApi newsApi;
    private static HearthStoneApi hearthStoneApi;
    private static JueJinApi jueJinApi;
    private static final String FILE_HEARTHSTONE ="file_hearthstone";
    private static final String FILE_ZHIHU ="file_zhihu";

    private static final String FILE_JUEJIN ="file_juejin";

    private static final int DEFAULT_MAXSIZE =1024*1024*50;
    public static final Interceptor CACHE_CONTROL_INTERCEPTOR = chain -> {
        Request request =chain.request();
        if(!NetWorkUtil.isNetWorkAvailable()){
            request=request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        return chain.proceed(request);
    };
    public static ApiManager getInstance(){
        if(apiManager==null){
            synchronized (ApiManager.class){
                if(apiManager==null){
                    apiManager =new ApiManager();
                }
            }
        }
        return apiManager;
    }


    public ZhiHuApi getZhiHuApi(){
        if(zhiHuApi==null){
            synchronized (ApiManager.class){
                File cacheDir = new File(ConfigUtil.getCacheDir(),FILE_ZHIHU);
                Cache cache =new Cache(cacheDir,DEFAULT_MAXSIZE);
                if(zhiHuApi==null){
                    zhiHuApi = createBaseRetrofitWithCacheClient(
                            createBaseOKHttpClientWithCache(cache),
                            ZHFactory.BASE_URL)
                            .create(ZhiHuApi.class);
                }
            }
        }
        return zhiHuApi;
    }
    public HearthStoneApi getHearthStoneApi(){
        if(hearthStoneApi==null){
            synchronized (ApiManager.class){
                File cacheDir = new File(ConfigUtil.getCacheDir(),FILE_HEARTHSTONE);
                Cache cache =new Cache(cacheDir,DEFAULT_MAXSIZE);
                if(hearthStoneApi==null){
                    hearthStoneApi= createBaseRetrofitWithCacheClient(
                            createBaseOKHttpClientWithCache(cache),
                            HSFactory.BASE_URL)
                            .create(HearthStoneApi.class);
                }
            }
        }
        return hearthStoneApi;
    }
    public JueJinApi getJueJinApi(){
        if(jueJinApi==null){
            synchronized (ApiManager.class){
                File cacheDir = new File(ConfigUtil.getCacheDir(),FILE_JUEJIN);
                Cache cache =new Cache(cacheDir,DEFAULT_MAXSIZE);
                if(jueJinApi==null){
                    jueJinApi = createBaseRetrofitWithCacheClient(
                            createBaseOKHttpClientWithCache(cache),
                            JJFactory.BASE_URL)
                            .create(JueJinApi.class);
                }
            }
        }
        return jueJinApi;
    }

    public static  OkHttpClient createBaseOKHttpClientWithCache(Cache cache){
        return  new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(CACHE_CONTROL_INTERCEPTOR)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
    }
    public static Retrofit createBaseRetrofitWithCacheClient(OkHttpClient okHttpClient,String url){
        return  new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
