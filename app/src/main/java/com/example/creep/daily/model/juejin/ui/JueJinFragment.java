package com.example.creep.daily.model.juejin.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.creep.daily.model.juejin.adapter.JueJinAdapter;
import com.example.creep.daily.R;
import com.example.creep.daily.RecyclerViewFragment;
import com.example.creep.daily.data.JueJinData;
import com.example.creep.daily.http.ApiManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by creep on 2016/9/14.
 */

public class JueJinFragment extends RecyclerViewFragment {
    private String latest ;
    private int timestamp =0;
    private JueJinAdapter jueJinAdapter;
    private List<JueJinData.ResultsBean> datas =new ArrayList<>();

    public JueJinFragment(){

    }



    @Override
    protected void setAdapter() {
        jueJinAdapter =new JueJinAdapter(getContext(),datas);
        recyclerView.setAdapter(jueJinAdapter);
    }


    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        timestamp =0;
        loadData(true);
    }

    private void addData(List<JueJinData.ResultsBean> lists, boolean clean) {
        if(clean){
            datas.clear();
        }
        datas.addAll(lists);
        jueJinAdapter.notifyDataSetChanged();
    }
    public void showError(){
        Snackbar.make(recyclerView,"请检查异常",Snackbar.LENGTH_LONG)
                .setAction("重试", v -> loadData(true));
    }
    protected void loadMore() {
        super.loadMore();
//        timestamp = TimeUtils.substractOne(latest);

        loadData(false);

    }
    @Override
    protected void loadData(boolean clean) {

        Map<String,String> maps =new HashMap<>();
        maps.put("where","{\"tags\":{\"__type\":\"Pointer\",\"className\":\"Tag\",\"objectId\":\"5597838ee4b08a686ce2319d\"}}");
        maps.put("include","user");
        maps.put("order","-rankIndex");
        Subscription s = ApiManager.getInstance().getJueJinApi()
                .getAndroidData(maps)
                .map(JueJinData::getResults)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsBeen -> {

                    if(clean){
                        datas.clear();
                    }
                    datas.addAll(resultsBeen);
                    jueJinAdapter.notifyDataSetChanged();
                    setRefresh(false);
                }, throwable -> {
                    showError();
                });
        getCompositeSubscription().add(s);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_juejin,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String toString() {
        return "掘金";
    }
}
