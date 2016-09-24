package com.example.creep.daily.API;

import com.example.creep.daily.data.StartImage;
import com.example.creep.daily.data.ZhiHuArticle;
import com.example.creep.daily.data.ZhiHuData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by creep on 2016/9/13.
 */

public interface ZhiHuApi {
    @GET("api/4/news/latest")
    Observable<ZhiHuData> getLastData();
    @GET("api/4/news/before/{date}")
    Observable<ZhiHuData> getBeforData(@Path("date") String date);
    @GET("api/4/news/{id}")
    Observable<ZhiHuArticle> getZhihuStory(@Path("id") String id);
    @GET("api/4/start-image/{width}*{height}")
    Observable<StartImage> getStartImage(@Path("width") int width, @Path("height") int height);
}
