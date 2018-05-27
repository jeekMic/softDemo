package com.example.administrator.myp2p.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 这里需要设计成单例模式
 * 同一栈管理,用于管理所有activity栈的管理
 * 添加 删除指定，删除当前，删除所有，求栈大小
 */
public class AppManager {
    private static AppManager  appManager;
    private AppManager() {
    }
    public static AppManager getInstance(){
        if (appManager==null){
            appManager = new AppManager();
        }
        return appManager;
    }
    private Stack<Activity> activityStack = new Stack<>();


    public void addActivity(Activity activity){
        activityStack.add(activity);
    }
    public void removeActivity(Activity activity)
    {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity  activity1 = activityStack.get(i);
            if (activity1.getClass().equals(activity)){
                activity1.finish();
                activityStack.remove(activity1);
                break;
            }
        }
//        for (Activity temp: activityStack)  {
//            if (temp.getClass().equals(activity)){
//                temp.finish();
//                activityStack.remove(temp);
//                break;
//            }
//        }
    }

    public void removeCurrent(){
        Activity  activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }
    public void removeAll(){
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity  activity1 = activityStack.get(i);
                activity1.finish();
                activityStack.remove(activity1);
        }
    }

    public int getSize(){
        return activityStack.size();
    }

}
