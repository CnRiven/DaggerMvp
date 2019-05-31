package com.riven.daggermvp.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
public interface BaseView {
    /**
     * 显示加载框
     */
    void showLoading();

    /**
     * 隐藏加载框
     */
    void hideLoading();

    /**
     * Toast
     * @param toastStr
     */
    void showToast(String toastStr);

    /**
     * 跳转Activity
     * @param activity
     */
    void goPage(Class<? extends Activity> activity);

    /**
     * 跳转Activity
     * @param activity
     * @param bundle
     */
    void goPage(Class<? extends Activity> activity, Bundle bundle);

    /**
     * 跳转Activity
     * @param activity
     * @param bundle
     * @param requestCode
     */
    void goPage(Class<? extends Activity> activity, Bundle bundle, int requestCode);
}
