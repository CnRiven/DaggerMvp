package com.riven.daggermvp.ui.fragment.article;


import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseFragment;

/**
 * Description:
 * Author:
 * Date:
 */

public class ArticleFragment extends BaseFragment<ArticlePresenter> implements ArticleContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

    }
}
