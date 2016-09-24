package com.example.creep.daily.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by creep on 2016/9/12.
 */

public class NestedParentLayout extends FrameLayout {
    private boolean isHorizontal;

    public NestedParentLayout(Context context) {

        super(context);
    }

    public NestedParentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//Parent 收到onStartNestedScroll()回调，决定是否需要配合 Child 一起进行处理滑动
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    //决定是否需要配合 Child 一起进行处理滑动，如果需要配合，还会回调onNestedScrollAccepted()
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        super.onNestedScrollAccepted(child, target, axes);
    }
//Parent 可以在这个回调中“劫持”掉 Child 的滑动，也就是先于 Child 滑动。
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if(Math.abs(dx)-Math.abs(dy)>1){

        }
        if (Math.abs(dx)-Math.abs(dy)<3){
            isHorizontal=false;
        }
        super.onNestedPreScroll(target, dx, dy, consumed);
    }
//这里就是 Child 滑动后，剩下的给 Parent 处理，也就是 后于 Child 滑动。
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }
//调用onStopNestedScroll()表示本次处理结束
    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
    }
}
