package com.example.creep.daily.http;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Path;
import android.util.Log;
import android.webkit.WebResourceResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Url;

/**
 * Created by creep on 2016/9/10.
 */

public class UrlCache {
    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = 60L * ONE_SECOND;
    public static final long ONE_HOUR   = 60L * ONE_MINUTE;
    public static final long ONE_DAY    = 24 * ONE_HOUR;
    private Map<String, Entity> cacheEntries = new HashMap<String, Entity>();
    private Application activity ;
    private File rootDir;


    private static class Entity{
        public String url;
        public String fileName;
        public String mimeType;
        public String encoding;
        public long   maxAgeMillis;



        private Entity(String url, String fileName,
                           String mimeType, String encoding, long maxAgeMillis) {

            this.url = url;
            this.fileName = fileName;
            this.mimeType = mimeType;
            this.encoding = encoding;
            this.maxAgeMillis = maxAgeMillis;

        }
    }

    public UrlCache(Application activity){
        this.activity =activity;
        this.rootDir =activity.getFilesDir();
    }
    public UrlCache(Application activity,File rootDir){
        this.activity =activity;
        this.rootDir =rootDir;
    }
    public void register(String url, String cacheFileName,
                         String mimeType, String encoding,
                         long maxAgeMillis) {
        if(!cacheEntries.containsKey(url)){
            Log.e("urlCache","!!!!!!");
            Entity entry = new Entity(url, cacheFileName, mimeType, encoding, maxAgeMillis);
            this.cacheEntries.put(url, entry);
        }


    }
    public WebResourceResponse load(String url) {
        Entity entity = cacheEntries.get(url);

        Log.e("urlCache",entity.encoding+":"+entity.mimeType);
        Log.e("UrlCache",entity.fileName);
        if (entity == null) {
            Log.e("UrlCache", "empty");
            return null;
        }
        File cacheDir = new File(rootDir.getPath() + File.separator + entity.fileName);
        if (!cacheDir.exists()) {
            Log.e("urlCache","begindownload");
            downloadAndStore(cacheDir, entity);

        }
        if (cacheDir.exists()) {
            if (System.currentTimeMillis() - cacheDir.lastModified() >= entity.maxAgeMillis) {
            }
            try {
                Log.e("UrlCache", "Visit cache:" + entity.url);
                FileInputStream ins = new FileInputStream(cacheDir);
                return new WebResourceResponse(entity.mimeType, entity.encoding, ins);
            } catch (FileNotFoundException e) {
                Log.e("UrlCache", Log.getStackTraceString(e));
            }

            Log.e("urlCache","not exist1!");
            return null;
        }
        Log.e("urlCache","not exist2!");
        return null;

    }



    private void downloadAndStore(File cacheDir,Entity entity) {
        try {
            URL url =new URL(entity.url);
            URLConnection connection =url.openConnection();
            entity.encoding =connection.getContentEncoding();
            entity.mimeType =connection.getContentType();

            Log.e("urlCache",entity.encoding+":2"+entity.mimeType);

            InputStream ins =connection.getInputStream();
            FileOutputStream fos =new FileOutputStream(cacheDir);

            int data;
            while ((data=ins.read())!=-1){
                fos.write(data);
            }
            Log.e("UrlCache","download:"+entity.url);
            ins.close();
            fos.close();


        } catch (MalformedURLException e) {
            Log.e("UrlCache",Log.getStackTraceString(e));
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("UrlCache",Log.getStackTraceString(e));
            e.printStackTrace();

        }
    }

}
