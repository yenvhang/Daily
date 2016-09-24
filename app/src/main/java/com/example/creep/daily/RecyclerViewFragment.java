package com.example.creep.daily;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by creep on 2016/9/13.
 */

public abstract class RecyclerViewFragment extends SwipeRefreshFragment {
    protected RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    protected int page =0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new android.os.Handler().postDelayed(() -> {
            setRefresh(true);
            loadData(true);
        },300);
    }



    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycylerview);

        layoutManager = getLayoutManager();
        setLayoutManager(layoutManager);
        setAdapter();
        recyclerView.addOnScrollListener(getOnScrollListener());
    }


    protected abstract void setAdapter();

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
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

    protected void showError(){
        Snackbar.make(recyclerView,"请检查异常",Snackbar.LENGTH_LONG)
                .setAction("重试", v -> loadData(true));
    }

    protected abstract void loadData(boolean clean);
}
