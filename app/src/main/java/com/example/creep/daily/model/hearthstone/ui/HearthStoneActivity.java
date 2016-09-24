package com.example.creep.daily.model.hearthstone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import com.example.creep.daily.R;
import com.example.creep.daily.RecyclerViewActivity;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.model.hearthstone.ui.adapter.HSRecyclerViewAdapter;
import com.example.creep.daily.http.HSFactory;
import com.example.creep.daily.util.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/7.
 */

public class HearthStoneActivity extends RecyclerViewActivity {
    private String latest ;
    private int timestamp =0;
    private List<List<String>> datas =new ArrayList<List<String>>();
    private HSRecyclerViewAdapter hsRecyclerViewAdapter;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);


    }


    @Override
    protected void setAdapter() {
        hsRecyclerViewAdapter =new HSRecyclerViewAdapter(this,datas);
        recyclerView.setAdapter(hsRecyclerViewAdapter);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        timestamp =0;
        loadData(true);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new android.os.Handler().postDelayed(() -> {
         setRefresh(true);
            loadData(true);
        },300);

    }

    private void loadData(boolean clean) {
        Map<String,Integer> maps =new HashMap();
        maps.put(HSFactory.TIMESTAMP_CODE,timestamp);
        maps.put(HSFactory.TOKEN_CODE,null);
        maps.put(HSFactory.SIZE_CODE,HSFactory.DEFAULT_SIZE);
        maps.put(HSFactory.MODULE_CODE,HSFactory.DEFAULT_MODULE);
        maps.put(HSFactory.VERSION_CODE,HSFactory.DEFAULT_VERSION);



        Subscription s = ApiManager.getInstance().getHearthStoneApi().getArticleData(maps)
                .map(articleData -> articleData.getArticles())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lists -> {
                    latest = TimeUtils.getTimeByMills(lists.get(0).get(2));
                    addData(lists,clean);
                    setRefresh(false);
                }, throwable -> {
                    throwable.printStackTrace();
                    setRefresh(false);
                    showError();
                }
                );
        addSubscription(s);



    }

    private void addData(List<List<String>> lists,boolean clean) {
        if(clean){
            datas.clear();
        }
        datas.addAll(lists);
        hsRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadMore() {
        super.loadMore();
        timestamp = TimeUtils.substractOne(latest);

        loadData(false);

    }

    public void showError(){
        Snackbar.make(recyclerView,"请检查异常",Snackbar.LENGTH_LONG)
                .setAction("重试", v -> loadData(true));
    }
}
