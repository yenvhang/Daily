package com.example.creep.daily;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * Created by creep on 2016/9/9.
 */

public abstract class RecyclerViewActivity extends SwipeRefreshActivity {
    protected RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    protected int page =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRecyclerView();
    }

    protected void initRecyclerView() {

        recyclerView = (RecyclerView) findViewById(R.id.recycylerview);
        layoutManager = getLayoutManager();
        setLayoutManager(layoutManager);
        setAdapter();
        recyclerView.addOnScrollListener(getOnScrollListener());

    }

    protected abstract void setAdapter();

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }


    private void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    protected RecyclerView.OnScrollListener getOnScrollListener() {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isScrollAtBottom()&&!mSwipeRefreshLayout.isRefreshing()){
                    loadMore();
                }
            }



        };
    }
    protected boolean isScrollAtBottom() {
        if(layoutManager instanceof LinearLayoutManager){
            int num =((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

            return num>=layoutManager.getItemCount()-getReloadSize();
        }
        else if(layoutManager instanceof GridLayoutManager){
            int num =((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            return num>=layoutManager.getItemCount()-getReloadSize();
        }
        else if(layoutManager instanceof StaggeredGridLayoutManager){
            int [] positions=new int[2];
            int num [] =((StaggeredGridLayoutManager)layoutManager).findLastCompletelyVisibleItemPositions(positions);
            if(num[0]<num[1]){
                num[0]=num[1];
            }
            return num[0]>=layoutManager.getItemCount()-getReloadSize();
        }
        return false;
    }

    private int getReloadSize() {
        return 6;
    }

    protected void loadMore() {
        page++;
        setRefresh(true);

    }

}
