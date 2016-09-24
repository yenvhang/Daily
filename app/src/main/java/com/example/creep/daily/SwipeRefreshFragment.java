package com.example.creep.daily;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by creep on 2016/9/13.
 */

public class SwipeRefreshFragment extends BaseFragment implements SwipeRefreshLayer {
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) (view.findViewById(R.id.mSwipeRefreshLayout));
        if(mSwipeRefreshLayout!=null) {
            mSwipeRefreshLayout.setOnRefreshListener(() -> requestDataRefresh());
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light,
                    android.R.color.holo_green_dark, android.R.color.holo_green_light);
        }

    }



    @Override
    public void requestDataRefresh() {

    }

    @Override
    public void setRefresh(boolean refresh) {
        if(mSwipeRefreshLayout==null){
            return;
        }
        if(refresh){
            mSwipeRefreshLayout.setRefreshing(true);
        }
        else{
            mSwipeRefreshLayout.postDelayed(()->mSwipeRefreshLayout.setRefreshing(false),1000);
        }
    }
}
