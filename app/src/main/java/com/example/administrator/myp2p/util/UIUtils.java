package com.example.administrator.myp2p.util;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.example.administrator.myp2p.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    public static Handler gethandler(){
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

    public static boolean isInMainThread() {
        //当前线程的id
        int tid = android.os.Process.myTid();
        if (tid==MyApplication.mainThreadID){
            return true;
        }
        return false;
    }
    /**
     * 保证runable对象的闰方法是运行在主线程中
     */
    public static void runOnUIThread(Runnable runnable){
        if (isInMainThread()){
            runnable.run();
        }else {
            gethandler().post(runnable);
        }
    }
    public static String[] getStringarr(int arrId){
        return getContext().getResources().getStringArray(arrId);
    }

    //两个时间戳是否是同一天 时间戳是long型的（11或者13）
    public static boolean isSameData(String currentTime,String lastTime) {
        try {
            Calendar nowCal = Calendar.getInstance();
            Calendar dataCal = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            Long nowLong = new Long(currentTime);
            Long dataLong = new Long(lastTime);
            String data1 = df1.format(nowLong);
            String data2 = df2.format(dataLong);
            java.util.Date now = df1.parse(data1);
            java.util.Date date = df2.parse(data2);
            nowCal.setTime(now);
            dataCal.setTime(date);
            return isSameDay(nowCal, dataCal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                    && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            return false;
        }
    }

}
