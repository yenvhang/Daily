package com.example.creep.daily;

/**
 * Created by creep on 2016/9/8.
 */

public interface SwipeRefreshLayer {
    void requestDataRefresh();
    void setRefresh(boolean refresh);
}
