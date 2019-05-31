package com.riven.daggermvp.ui.fragment.mine;

import com.riven.daggermvp.base.BasePAV;

import javax.inject.Inject;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class MinePresenter extends BasePAV<MineContract.View> implements MineContract.Presenter{
    @Inject
    public MinePresenter() {
    }

    @Override
    public void showStr(String str) {
        mView.showToast(str);
    }
}
