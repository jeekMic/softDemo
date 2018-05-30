package com.example.administrator.myp2p.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private View innerview;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //当指定的xml文件被解析完毕的时候执行这个方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            innerview = getChildAt(0);
        }
    }
    //重写onTouchEvent

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerview == null) {
            return super.onTouchEvent(ev);
        } else {
            if (animationFinish)
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    //自定义的touch事件
    private void commonTouchEvent(MotionEvent ev) {
        int action  = ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                y = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //在触摸移动的过程中，计算当前点个按下点的距离
                float prey = y==0?ev.getY():y;
                float nowy = ev.getY();
                int  detailY = (int) (prey-nowy);
                if(isNeedMove())
                {
                    //记录下最初状态
                    if (normal.isEmpty()){

                        normal.set(innerview.getLeft(),innerview.getTop(),innerview.getRight(),innerview.getBottom());
                    }
                    innerview.layout(innerview.getLeft(),innerview.getTop()-detailY/2,innerview.getRight(),detailY/2+innerview.getBottom());
                    Log.e("zoubo","innerview.getTop----|"+(innerview.getTop()-detailY/2)+"-------bottom"+(innerview.getBottom()+detailY/2));
                    Log.e("zoubo","detailY|"+detailY);
                }

                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()){
                    animation();
                }

                break;
            default:
                y = 0;
                break;

        }

    }

    private void animation() {
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,innerview.getTop()-normal.top);
        translateAnimation.setDuration(2000);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerview.clearAnimation();
                animationFinish = true;
                innerview.layout(normal.left,normal.top,normal.right,normal.bottom);
                normal.setEmpty();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerview.startAnimation(translateAnimation);

    }

    //判断是否需要拖动
    public boolean isNeedMove() {
        //getMeasureHeight()是实际View的大小在这里只有一个view因为scroview只有一个节点
        // getHeight是屏幕的大小 与屏幕无关当超出屏幕的时候getMeasureHeight = getHeight+屏幕外没有显示的大小实际上也是view的总高度
        int offset = innerview.getMeasuredHeight()-getHeight();
        int scrollY = getScrollY();
        if(scrollY==0||scrollY==offset){
        //这个时候移动布局
        return true;
        }
//        Log.e("zoubo","innerview.getMeasuredHeight()----"+innerview.getMeasuredHeight()+"-------getHeight()"+getHeight());
//        Log.e("zoubo","offset----"+offset+"-------scrollY"+scrollY);

        return false;
    }
    //判断是否需要回滚
    public boolean isNeedAnimation() {
        if (!normal.isEmpty()){
            return true;
        }else{
            return true;
        }

    }
}
