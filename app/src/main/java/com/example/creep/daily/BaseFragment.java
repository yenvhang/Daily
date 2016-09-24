package com.example.creep.daily;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.creep.daily.util.DayNightHelper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by creep on 2016/9/7.
 */

public class BaseFragment extends Fragment{



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

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

}
