package com.example.creep.daily.model.home.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.creep.daily.model.home.data.Item;

/**
 * Created by creep on 2016/9/13.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private Item[] datas;

    public HomePagerAdapter(FragmentManager fm,Item [] datas) {
        super(fm);
        this.datas =datas;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Fragment getItem(int position) {
        try {
           return (Fragment) datas[position].clz.newInstance();
        } catch (InstantiationException e) {
            Log.e("HOME","1");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.e("HOME","2");
        }
        return null;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return datas[position].name;

    }
}
