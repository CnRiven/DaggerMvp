package com.riven.daggermvp.base;

import android.os.Bundle;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
public interface BasePresenter<T extends BaseView> {

    /**
     * 绑定View
     * @param view
     */
    void attachView(T view);

    /**
     * 解除绑定
     */
    void detachView();
}
