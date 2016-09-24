package com.example.creep.daily.API;

import com.example.creep.daily.data.JueJinData;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by creep on 2016/9/14.
 */

public interface JueJinApi {
    @Headers({"X-LC-Id:mhke0kuv33myn4t4ghuid4oq2hjj12li374hvcif202y5bm6" ,
            "X-LC-Sign:f0621bf3bd1e968055677dd2b8f2204f,1473836050781" ,
            "X-LC-UA:AV/js1.4.0"})
    @GET("1.1/classes/Entry?limit=20")
            Observable<JueJinData> getAndroidData(@QueryMap Map<String,String> maps);


}
