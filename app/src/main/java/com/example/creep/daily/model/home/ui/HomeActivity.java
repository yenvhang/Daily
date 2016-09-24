package com.example.creep.daily.model.home.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.example.creep.daily.model.SettingActivity;
import com.example.creep.daily.model.juejin.ui.JueJinFragment;
import com.example.creep.daily.R;
import com.example.creep.daily.ToolbarActivity;
import com.example.creep.daily.model.hearthstone.ui.HearthStoneFragment;
import com.example.creep.daily.model.home.data.Item;
import com.example.creep.daily.model.home.ui.adapter.HomePagerAdapter;
import com.example.creep.daily.model.zhihu.ui.ZhihuFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by creep on 2016/9/13.
 */

public class HomeActivity extends ToolbarActivity{
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewPager;
    private Item [] tabTitle ={  new Item(HearthStoneFragment.class,"炉石传说"),
            new Item(ZhihuFragment.class,"知乎日报"),
            new Item(JueJinFragment.class,"掘金"),
           };
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar.setTitleMargin(100,20,0,10);
        ButterKnife.bind(this);
        HomePagerAdapter homePagerAdapter =new HomePagerAdapter(getSupportFragmentManager(),tabTitle);
        for(int i=0;i<tabTitle.length;i++){
            tablayout.addTab(tablayout.newTab());
        }
        tablayout.setupWithViewPager(viewPager);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setAdapter(homePagerAdapter);
    }
    private long lastback_click= System.currentTimeMillis();
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.e("Home","0");
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-lastback_click<2000){
                Log.e("Home","1");
                System.exit(0);
            }
            else{
                Log.e("Home","2");
                lastback_click =System.currentTimeMillis();
                Snackbar.make(viewPager,"再按一次退出程序",Snackbar.LENGTH_SHORT).show();

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mode:
                if(item.getTitle().equals(getString(R.string.night_mode))){
                    item.setTitle(getString(R.string.day_mode));
                }
                else{
                    item.setTitle(getString(R.string.night_mode));
                }
                break;

            case R.id.setting:
                startActivityForResult(new Intent(this, SettingActivity.class),0);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }
}
