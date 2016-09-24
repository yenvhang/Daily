package com.example.creep.daily.model.juejin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.creep.daily.R;
import com.example.creep.daily.data.JueJinData;
import com.example.creep.daily.model.juejin.ui.JuejinDescribleActivity;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by creep on 2016/9/14.
 */

public class JueJinAdapter extends RecyclerView.Adapter<JueJinAdapter.ViewHolder> {
    private List<JueJinData.ResultsBean> datas ;
    private Context mContext;

    public JueJinAdapter(Context context, List<JueJinData.ResultsBean> datas){
        mContext =context;
        this.datas =datas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(mContext)
                .inflate(R.layout.item_juejin,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JueJinData.ResultsBean resultsBean =datas.get(position);
        String url =resultsBean.getScreenshot()!=null?resultsBean.getScreenshot().getUrl():null;
        Glide.with(mContext)
                .load(url)
//                .placeholder(R.drawable.default_juejin)
                .fitCenter()
                .into(holder.imageView);
        holder.title.setText(resultsBean.getTitle());
        holder.collection.setText(resultsBean.getCollectionCount()+"");
        holder.author.setText(resultsBean.getUser().getUsername());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card)
        RelativeLayout card;
        @Bind(R.id.iv_display)
        SelectableRoundedImageView imageView;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.tv_collection)
        TextView collection;
        @Bind(R.id.tv_time)
        TextView time;
        @Bind(R.id.tv_author)
        TextView author;
        @OnClick(R.id.card)
        void startDesActivity(){
            JueJinData.ResultsBean resultsBean =datas.get(getLayoutPosition());
            Intent intent =new Intent(mContext,JuejinDescribleActivity.class);
            intent.putExtra(JuejinDescribleActivity.CONTENT_URL_CODE,resultsBean.getUrl());
            String url =resultsBean.getScreenshot()!=null?resultsBean.getScreenshot().getUrl():null;
            intent.putExtra(JuejinDescribleActivity.HEAD_URL_CODE,url);
            mContext.startActivity(intent);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}