package com.example.creep.daily.model;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creep.daily.R;
import com.example.creep.daily.ToolbarActivity;
import com.example.creep.daily.service.OffLineDownLoadService;
import com.example.creep.daily.util.DayNightHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by creep on 2016/9/19.
 */

public class SettingActivity extends ToolbarActivity {
    @Bind(R.id.netscrollview)
    NestedScrollView netscrollview;
    @Bind(R.id.switch_DayNight_mode)
    Switch mSwitch;
    @Bind(R.id.day_night_mode)
    TextView day_night_mode;
    @Bind(R.id.download)
    TextView download;
    @Bind(R.id.network)
    TextView network;
    @Bind(R.id.push_message)
    TextView push_message;
    @Bind(R.id.clear_cache)
    TextView clear_cache;
    @Bind(R.id.check_update)
    TextView check_update;
    @Bind(R.id.small_text1)
    TextView small_text1;
    @Bind(R.id.small_text2)
    TextView small_text2;

    private List<TextView> textViewList;
    private List<TextView> sTextViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        ButterKnife.bind(this);
        setTitle("设置");
        mSwitch.setChecked(!DayNightHelper.isDay());
        initData();
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingActivity.this,"click",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(SettingActivity.this, OffLineDownLoadService.class);
                startService(intent);
            }
        });
        mSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> toggleThemSetting());
    }

    private void initData() {
        textViewList =new ArrayList<>();
        sTextViewList =new ArrayList<>();
        textViewList.add(day_night_mode);
        textViewList.add(download);
        textViewList.add(network);
        textViewList.add(push_message);
        textViewList.add(clear_cache);
        textViewList.add(check_update);
        sTextViewList.add(small_text1);
        sTextViewList.add(small_text2);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_setting;
    }


    private void refreshUI() {
        TypedValue background =new TypedValue();
        TypedValue textColor =new TypedValue();
        TypedValue stextColor =new TypedValue();
        Resources.Theme theme =getTheme();

        theme.resolveAttribute(R.attr.card_backgroundcolor,background,true);
        theme.resolveAttribute(R.attr.card_textcolor,textColor,true);
        theme.resolveAttribute(R.attr.card_small_textcolor,stextColor,true);
        Resources resources =getResources();
        for (TextView textview:textViewList) {
            textview.setTextColor(resources.getColor(textColor.resourceId));
        }
        for(TextView textView:sTextViewList){
            textView.setTextColor(resources.getColor(stextColor.resourceId));
        }
        netscrollview.setBackgroundResource(background.resourceId);

    }



    private void toggleThemSetting() {
        if (DayNightHelper.isDay()) {
            DayNightHelper.setMode(1);
            setTheme(R.style.AppThemeNight);
            refreshUI();
        } else {
            DayNightHelper.setMode(0);
            setTheme(R.style.AppDayTheme);
            refreshUI();
        }
    }

    @Override
    protected boolean canBack() {
       return true;
    }

    @Override
    protected void performNavigationClick() {

        this.finish();

    }
}
