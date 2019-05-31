package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePAV;

import javax.inject.Inject;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomePresenter extends BasePAV<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    public HomePresenter() {
    }

    @Override
    public void showView(String str) {
        mView.showToast(str);
    }
}
