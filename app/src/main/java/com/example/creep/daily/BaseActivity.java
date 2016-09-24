package com.example.creep.daily;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by creep on 2016/9/7.
 */

public class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mCompositeSubscription;

    public CompositeSubscription getCompositeSubscription(){
        if(mCompositeSubscription==null){
            mCompositeSubscription =new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    public void addSubscription(Subscription s){
        if(mCompositeSubscription==null){
            mCompositeSubscription =new CompositeSubscription();

        }
        mCompositeSubscription.add(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCompositeSubscription!=null){
            mCompositeSubscription.unsubscribe();
        }
    }


}
