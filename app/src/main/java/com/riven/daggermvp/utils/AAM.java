package com.riven.daggermvp.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

import java.util.Stack;

/**
 * Description:
 * Author: djs
 * Date: 2019/6/4.
 */
public class AAM {

    //存储ActivityStack
    private static Stack<Activity> activityStack = new Stack<Activity>();

    //单例模式
    private static AAM instance;


    /**
     * 单列堆栈集合对象
     * @return AAM 单利堆栈集合对象
     */
    public static AAM getInstance() {
        if (null == instance) {
            synchronized (AAM.class){
                if (null == instance) {
                    instance = new AAM();
                }
            }
        }
        return instance;
    }

    private AAM(){

    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }


    public void removeAvitity(Activity activity){
        if (null != activity && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (null != activityStack && activityStack.size() > 0) {
            return activityStack.lastElement();
        }
        return null ;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            finishAnimtor(activity);
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Stack<Activity> deleteActivityStack = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                // finishActivity(activity);
                deleteActivityStack.add(activity);
            }
        }

        activityStack.removeAll(deleteActivityStack);
        for (Activity activity : deleteActivityStack) {
            finishAnimtor(activity);
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (;!activityStack.empty();){
            Activity activity = activityStack.pop() ;//出栈
            if (null != activity){
                finishAnimtor(activity);
            }
        }
    }

    /**
     * 结束除指定activity意外的所有activity
     *
     * @param clazz
     */
    public void finishAllActivityWithOutThis(Class<?> clazz) {
        Stack<Activity> tmp = new Stack<>();

        for (;!activityStack.empty();){
            Activity current = activityStack.pop() ;//获取到并移除栈顶的对象。

            if (current != null
                    && !current.getClass().getSimpleName()
                    .equals(clazz.getSimpleName())) {
                finishAnimtor(current);
            }else if (null != current &&
                    (current.getClass().getSimpleName().equals(clazz.getSimpleName()))){
                tmp.push(current) ;
            }
        }

        if (null != tmp && tmp.size() > 0) {
            activityStack.addAll(tmp) ;
        }
    }


    /**
     * 5.0以后的activity动画切换
     */
    protected void finishAnimtor(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAfterTransition();
        }else{
            activity.finish();
        }
    }




    /**
     * 退出应用程序
     */
    public void appExit(Context context) {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }

}
