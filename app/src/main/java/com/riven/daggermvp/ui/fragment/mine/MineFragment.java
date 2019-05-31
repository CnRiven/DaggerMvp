package com.riven.daggermvp.ui.fragment.mine;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseFragment;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {
        mPresenter.showStr("Mine!!!");
    }
}
