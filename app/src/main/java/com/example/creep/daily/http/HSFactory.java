package com.example.creep.daily.http;

import com.example.creep.daily.API.HearthStoneApi;

/**
 * Created by creep on 2016/9/7.
 */

public class HSFactory {
    public static final String BASE_URL = "http://www.iyingdi.com/";
    public static final String TIMESTAMP_CODE = "timestamp";
    public static final String TOKEN_CODE = "token";
    public static final String VERSION_CODE = "version";
    public static final String SIZE_CODE = "size";
    public static final String MODULE_CODE = "module";

    public static final int DEFAULT_TIMESTAMP = 0;
    public static final int DEFAULT_VERSION = 310;
    public static final int DEFAULT_SIZE = 20;
    public static final int DEFAULT_MODULE = 12;

    private static HearthStoneApi mhearthstoneService;

    public static HearthStoneApi getHearthstoneService() {
        if (mhearthstoneService == null) {
            synchronized (HSFactory.class) {
                if (mhearthstoneService == null) {
                    mhearthstoneService = new HSRestrofit().getHearthStoneService();
                }
            }
        }
        return mhearthstoneService;
    }

    public static String getDesURL(int id, long timestamp) {
        return new StringBuffer(BASE_URL)
                .append("article")
                .append("/" + id)
//                .append("/content")
                .append("?time=" + timestamp)
                .toString();


    }

    public static String getDesURL(int id) {
        return new StringBuffer(BASE_URL)
                .append("article")
                .append("/" + id)
                .append("/content")

                .toString();
    }
}
