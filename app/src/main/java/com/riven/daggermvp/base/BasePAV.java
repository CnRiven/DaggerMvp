package com.riven.daggermvp.base;

/**
 *
 * Created by hzy on 2019/1/18
 *
 * BasePAV BasePersenter关联/取消关联BaseView
 *
 * @author Administrator
 *
 * */
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
