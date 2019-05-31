package com.riven.daggermvp.ui.activity.main;

import com.riven.daggermvp.base.BasePAV;

import javax.inject.Inject;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class MainPresenter extends BasePAV<MainContract.View> implements MainContract.Presenter{

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
