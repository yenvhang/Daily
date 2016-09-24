package com.example.creep.daily.model.hearthstone.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.creep.daily.R;
import com.example.creep.daily.RecyclerViewCallBack;
import com.example.creep.daily.data.ZhiHuData;
import com.example.creep.daily.model.hearthstone.ui.DescribleHearthStoneActivity;
import com.example.creep.daily.http.HSFactory;
import com.example.creep.daily.model.zhihu.adapter.ZhiHuAdapter;
import com.example.creep.daily.model.zhihu.ui.ZhiHuDescribleActivity;
import com.example.creep.daily.util.TimeUtils;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by creep on 2016/9/7.
 */

public class HSRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public RecyclerViewCallBack callBack;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    private List<List<String>> datats;
    private Context mcontext;

    public HSRecyclerViewAdapter(Context context, List<List<String>> datas) {
        this.datats = datas;
        mcontext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.item_base_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.item_hearthstone, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<String> strings = datats.get(position);
        if (getItemViewType(position) == TYPE_HEADER) {
            bindHeaderViewHolder((HeaderViewHolder) holder, strings);
        } else {
            bindItemViewHolder((ItemViewHolder) holder, strings);


        }
    }

    private void bindHeaderViewHolder(HeaderViewHolder holder, List<String> strings) {
        Glide.with(mcontext)
                .load(strings.get(7))
                .centerCrop()

                .into(holder.imageView);
        holder.title.setText(strings.get(1));

    }

    private void bindItemViewHolder(ItemViewHolder holder, List<String> strings) {
        Glide.with(mcontext)
                .load(strings.get(7))
                .fitCenter()

                .into(holder.imageView);
        holder.title.setText(strings.get(1));
        holder.type.setText(strings.get(8));
        holder.comment.setText(strings.get(4));
        String stime = TimeUtils.getTimeByMills(strings.get(2));
        holder.time.setText(stime);
    }

    @Override
    public int getItemCount() {
        return datats.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_header);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            imageView.setOnClickListener(v -> startDesActivity(v,imageView));
        }


    private void startDesActivity(View v, ImageView imageview) {
        List<String> strings = datats.get(getLayoutPosition());
        Intent intent = new Intent(v.getContext(), DescribleHearthStoneActivity.class);

        intent.putExtra(DescribleHearthStoneActivity.HEAD_URL_CODE, strings.get(7));
        String url2 = HSFactory.getDesURL(Integer.parseInt(strings.get(0)));
        intent.putExtra(DescribleHearthStoneActivity.CONTENT_URL_CODE, url2);
        ActivityOptionsCompat optionCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation((Activity) mcontext, imageview, "iv_head");
        ActivityCompat.startActivity((Activity) mcontext, intent, optionCompat.toBundle());


    }
}


    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.card)
        RelativeLayout card;
        @Bind(R.id.iv_image)
        SelectableRoundedImageView imageView;
        @Bind(R.id.tv_title)
        TextView title;
        @Bind(R.id.tv_comment)
        TextView comment;
        @Bind(R.id.tv_time)
        TextView time;
        @Bind(R.id.tv_type)
        TextView type;
        @OnClick(R.id.card)
        void onclick(View v){
            startDesActivity(v,imageView);
        }
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        private void startDesActivity(View v,SelectableRoundedImageView imageview) {
            List<String> strings =datats.get(getLayoutPosition());
            Intent intent =new Intent(v.getContext(), DescribleHearthStoneActivity.class);

            intent.putExtra(DescribleHearthStoneActivity.HEAD_URL_CODE,strings.get(7));
            String url2 =HSFactory.getDesURL(Integer.parseInt(strings.get(0)));
            intent.putExtra(DescribleHearthStoneActivity.CONTENT_URL_CODE,url2);
            ActivityOptionsCompat optionCompat =ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) mcontext,imageview,"iv_head");
            ActivityCompat.startActivity((Activity) mcontext,intent,optionCompat.toBundle());


        }




    }


}
