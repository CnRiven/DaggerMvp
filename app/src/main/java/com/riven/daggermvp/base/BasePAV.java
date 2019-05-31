package com.riven.daggermvp.base;

/**
 * Description:Presenter与View的关联类
 * Author: djs
 * Date: 2019/5/28.
 */
public class BasePAV<T extends BaseView> implements BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
