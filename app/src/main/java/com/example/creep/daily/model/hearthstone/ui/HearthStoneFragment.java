package com.example.creep.daily.model.hearthstone.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.creep.daily.R;
import com.example.creep.daily.RecyclerViewFragment;
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
 * Created by creep on 2016/9/13.
 */

public class HearthStoneFragment extends RecyclerViewFragment {

    private String latest ;
    private int timestamp =0;
    private List<List<String>> datas =new ArrayList<List<String>>();
    private HSRecyclerViewAdapter hsRecyclerViewAdapter;

public HearthStoneFragment(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_base_list,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void setAdapter() {
        hsRecyclerViewAdapter =new HSRecyclerViewAdapter(getContext(),datas);
        recyclerView.setAdapter(hsRecyclerViewAdapter);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        timestamp =0;
        loadData(true);
    }

    protected void loadData(boolean clean) {
        Map<String,Integer> maps =new HashMap();
        maps.put(HSFactory.TIMESTAMP_CODE,timestamp);
        maps.put(HSFactory.TOKEN_CODE,null);
        maps.put(HSFactory.SIZE_CODE,HSFactory.DEFAULT_SIZE);
        maps.put(HSFactory.MODULE_CODE,HSFactory.DEFAULT_MODULE);
        maps.put(HSFactory.VERSION_CODE,HSFactory.DEFAULT_VERSION);

        Subscription s =HSFactory.getHearthstoneService().getArticleData(maps)
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

    protected void loadMore() {
        super.loadMore();
        timestamp = TimeUtils.substractOne(latest);

        loadData(false);

    }

    @Override
    public String toString() {
        return "炉石传说";
    }
}
