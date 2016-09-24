package com.example.creep.daily.model.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.creep.daily.R;
import com.example.creep.daily.data.StartImage;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.model.home.ui.HomeActivity;
import com.example.creep.daily.util.NetWorkUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/19.
 */

public class SplashActivity extends AppCompatActivity {
    private ImageView imageview;
    private AlphaAnimation alpha;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageview = (ImageView) findViewById(R.id.iv_image);
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        int width =metrics.widthPixels;
        int height =metrics.heightPixels;
        Log.e("SPLASH",width+":"+height);
        if(NetWorkUtil.isNetWorkAvailable()){
            ApiManager.getInstance().getZhiHuApi().getStartImage(width,height)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(startImage -> {
                        Glide.with(SplashActivity.this)
                                .load(startImage.getImg()).centerCrop()
                                .animate(alpha)
                                .into(imageview);
                    }, throwable -> {
                        startHomeActivity();
                        Log.e("SPLASH","ERROR");
                    });
        }
        else{
            Glide.with(this).load(R.drawable.default_statr_image).into(imageview);
            startHomeActivity();
        }

        initAnimation();
//        imageview.startAnimation(alpha);

    }



    private void initAnimation() {
        alpha =new AlphaAnimation(0.0f,1.0f);
        alpha.setDuration(2500);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startHomeActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void startHomeActivity() {
        Intent intent =new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
