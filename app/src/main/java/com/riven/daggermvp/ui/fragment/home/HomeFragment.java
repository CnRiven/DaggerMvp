package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseFragment;
import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.utils.GlideImageLoader;
import com.riven.daggermvp.utils.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Description:首页
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.banner)
    Banner banner;
    //需要手动解绑
    Unbinder unbinder;

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
        initView();
        mPresenter.getBannerData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showBanner(List<BannerBean> beanList) {
        List<String> urlList = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            urlList.add(beanList.get(i).getImagePath());
            LogUtil.e(i + 1 + "----图片地址----:" + urlList.get(i));
        }
        banner.update(urlList);
    }

    private void initView() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.NOT_INDICATOR);
    }
}
