package com.example.creep.daily;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by creep on 2016/9/8.
 */

public abstract class SwipeRefreshActivity extends ToolbarActivity implements SwipeRefreshLayer {
   protected SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        if(mSwipeRefreshLayout!=null) {
            mSwipeRefreshLayout.setOnRefreshListener(() -> requestDataRefresh());
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
