package com.example.administrator.myp2p.util;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.administrator.myp2p.common.MyApplication;

/**
 * 访问跟UI相关的一些操作
 */
public class UIUtils{
    public static Context getContext(){
        return MyApplication.context;
    }
    public static int getColor(int colorID){
        return getContext().getResources().getColor(colorID);
    }

    public static View getXmlView(int layoutID){
        return View.inflate(getContext(), layoutID,null);
    }
    public static Handler handler(){
        return MyApplication.handler;
    }
    public static int  dp2px(int dp){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density*dp+0.5f);
    }
    public static int px2dp(int px){
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(px/scale+0.5f);
    }
    //获得数组
    public String[] getstrr(int arrid){
        return getContext().getResources().getStringArray(arrid);
    }
}
