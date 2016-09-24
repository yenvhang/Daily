package com.example.creep.daily.model.zhihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.creep.daily.R;
import com.example.creep.daily.RecyclerViewFragment;
import com.example.creep.daily.data.ZhiHuData;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.model.zhihu.adapter.ZhiHuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/18.
 */

public class ZhihuFragment extends RecyclerViewFragment {
    private List<ZhiHuData.StoriesBean> datas =new ArrayList();
    private String lastDate;
    private ZhiHuAdapter zhAdapter;
    @Override
    protected void setAdapter() {

        zhAdapter =new ZhiHuAdapter(getContext(),datas);
        recyclerView.setAdapter(zhAdapter);
    }

    @Override
    protected void loadData(boolean clean) {
        Subscription s = ApiManager.getInstance().getZhiHuApi()
                .getLastData()
                .map(zhiHuData ->{
                        lastDate =zhiHuData.getDate();
                        return zhiHuData.getStories();})
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(storiesBeen -> {
                    if (clean) {
                        datas.clear();
                    }
                        datas.addAll(storiesBeen);
                        zhAdapter.notifyDataSetChanged();
                        setRefresh(false);
                }, throwable -> {
                    showError();
                });
        getCompositeSubscription().add(s);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_base_list,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    protected void loadMore() {
        super.loadMore();
        Log.e("ZHIHUFRAGMENT",lastDate+"lastdate");
        if(!TextUtils.isEmpty(lastDate)) {
            Subscription s = ApiManager.getInstance().getZhiHuApi()
                    .getBeforData(lastDate)
                    .map(ZhiHuData->{
                        lastDate =ZhiHuData.getDate();
                        return ZhiHuData.getStories();
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(storiesBeen -> {
                        datas.addAll(storiesBeen);
                        zhAdapter.notifyDataSetChanged();
                        setRefresh(false);
                    },throwable -> showError());
            getCompositeSubscription().add(s);

        }
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        loadData(true);
    }
}
