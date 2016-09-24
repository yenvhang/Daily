package com.example.creep.daily.API;

import com.example.creep.daily.model.hearthstone.data.ArticleData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by creep on 2016/9/7.
 */
//timestamp=0&token=&size=20&module=12&version=310
public interface HearthStoneApi {
    @GET("article/list")
    Observable<ArticleData> getArticleData(@QueryMap Map<String,Integer> maps);


}
