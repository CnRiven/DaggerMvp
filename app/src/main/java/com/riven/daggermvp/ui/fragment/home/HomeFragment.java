package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseFragment;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.showView("Home!!!Home");
    }

}
