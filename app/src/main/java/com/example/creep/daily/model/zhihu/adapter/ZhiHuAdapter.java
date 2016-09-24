package com.example.creep.daily.model.zhihu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.creep.daily.R;
import com.example.creep.daily.data.ZhiHuData;
import com.example.creep.daily.http.ApiManager;
import com.example.creep.daily.model.zhihu.ui.ZhiHuDescribleActivity;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by creep on 2016/9/18.
 */

public class ZhiHuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ZhiHuData.StoriesBean> datas;
    private Context mContext;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    public ZhiHuAdapter(Context mContext,List<ZhiHuData.StoriesBean> datas){
        this.datas =datas;
        this.mContext =mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_zhihu,
                    parent, false));
        }
        else {
            return new HeaderViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_base_header,
                    parent,false));
        }
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType =getItemViewType(position);
        ZhiHuData.StoriesBean story= datas.get(position);
        switch (viewType){
            case TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                bindHeadViewHolder(headerViewHolder,story);
                break;
            case TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                bindItemViewHolder(itemViewHolder,story);
                break;
        }



    }

    private void bindItemViewHolder(ItemViewHolder holder, ZhiHuData.StoriesBean story) {
        String url = story!=null&&story.getImages()!=null&&story.getImages().get(0)!=null
                ? story.getImages().get(0) :null;

        if (url!=null) {
            Glide.with(mContext)
                    .load(url)
                    .fitCenter()
                    .into(holder.imageView);
        }
        holder.title.setText(story.getTitle());
    }

    private void bindHeadViewHolder(HeaderViewHolder holder, ZhiHuData.StoriesBean story) {
        Subscription s = ApiManager.getInstance().getZhiHuApi()
                .getZhihuStory(String.valueOf(story.getId()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(zhiHuArticle -> {
                    Glide.with(mContext)
                            .load(zhiHuArticle.getImage())
                            .centerCrop()
                            .into(holder.imageView);
                    holder.title.setText(story.getTitle());
                }, throwable -> {
                    Log.e("ZHIHU",Log.getStackTraceString(throwable));

                });




    }


    @Override
    public int getItemViewType(int position) {
        return position==0?TYPE_HEADER:TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_header);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            imageView.setOnClickListener(v -> {
                ZhiHuData.StoriesBean story =datas.get(getLayoutPosition());
                Intent intent =new Intent(mContext, ZhiHuDescribleActivity.class);
                intent.putExtra(ZhiHuDescribleActivity.CONTENT_ID_URL_CODE,String.valueOf(story.getId()));
                String url = story!=null&&story.getImages()!=null&&story.getImages().get(0)!=null
                        ? story.getImages().get(0) :null;
                intent.putExtra(ZhiHuDescribleActivity.HEAD_URL_CODE,url);
                mContext.startActivity(intent);
            });
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.card)
        RelativeLayout card;
        @Bind(R.id.iv_image)
        SelectableRoundedImageView imageView;
        @Bind(R.id.tv_title)
        TextView title;
        @OnClick(R.id.card)
        void startWebActivity(){
            ZhiHuData.StoriesBean story =datas.get(getLayoutPosition());
            Intent intent =new Intent(mContext, ZhiHuDescribleActivity.class);
            intent.putExtra(ZhiHuDescribleActivity.CONTENT_ID_URL_CODE,String.valueOf(story.getId()));
            String url = story!=null&&story.getImages()!=null&&story.getImages().get(0)!=null
                    ? story.getImages().get(0) :null;
            intent.putExtra(ZhiHuDescribleActivity.HEAD_URL_CODE,url);
            mContext.startActivity(intent);
        }
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
