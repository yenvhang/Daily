package com.example.creep.daily;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.creep.daily.util.DayNightHelper;

import butterknife.ButterKnife;

/**
 * Created by creep on 2016/9/7.
 */

public abstract class ToolbarActivity extends BaseActivity {
    protected AppBarLayout appbarlayout;
    protected Toolbar mToolbar;
    private boolean mIsHidden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initTheme();
        setContentView(getContentViewId());
        appbarlayout = (AppBarLayout) findViewById(R.id.appbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if(canBack()){
            ActionBar actionBar =getSupportActionBar();
            if(actionBar!=null) {
                Log.e("Toolbar","true");
                actionBar.setDisplayHomeAsUpEnabled(true);
                mToolbar.setNavigationOnClickListener(v -> performNavigationClick());



            }
        }
    }
    public void setAppbarAlpha(float alpha){
        if(appbarlayout!=null) {
            appbarlayout.setAlpha(alpha);

        }
    }

    public void hideorShowToolbar(){
        if(appbarlayout!=null){
            appbarlayout.animate()
                    .translationY(mIsHidden?0:-appbarlayout.getHeight())
                    .setInterpolator(new DecelerateInterpolator(2))
                    .start();
            mIsHidden =!mIsHidden;
        }
    }
    public void setToolbarTitle(String s){
        if(mToolbar!=null){
            mToolbar.setTitle(s);
        }
    }
    private void initTheme() {
        if (DayNightHelper.isDay()) {

            setTheme(R.style.AppDayTheme);
        } else {
            setTheme(R.style.AppThemeNight);
        }
    }

    protected  void performNavigationClick(){};

    protected abstract int getContentViewId();

    protected boolean canBack(){
        return false;
    }

}
