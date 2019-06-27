package com.riven.daggermvp.ui.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.riven.daggermvp.R;
import com.riven.daggermvp.base.BaseFragment;
import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.bean.NewProjectBean;
import com.riven.daggermvp.utils.GlideApp;
import com.riven.daggermvp.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:首页
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View, OnRefreshListener,OnLoadmoreListener {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rcHome)
    RecyclerView rcHome;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    //最新文章默认加载第0页
    private int page = 0;

    private CommonAdapter<NewProjectBean.DatasBean> adapter;
    private List<NewProjectBean.DatasBean> projectBeanList = new ArrayList<>();

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
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadmoreListener(this);
        initViewBanner();
        initAdapter();
        initRecyclerView();
        mPresenter.getBannerData();
        mPresenter.getNewProject(page);
    }

    private void initAdapter() {
        adapter = new CommonAdapter<NewProjectBean.DatasBean>(getActivity(), R.layout.item_home, projectBeanList) {
            @Override
            protected void convert(ViewHolder holder, final NewProjectBean.DatasBean datasBean, int position) {
                holder.setText(R.id.title, datasBean.getTitle());

                ImageView imageView = holder.getView(R.id.img);
                GlideApp.with(getActivity())
                        .load(datasBean.getEnvelopePic())
                        .into(imageView);
                if (position == projectBeanList.size()) {
                    holder.setVisible(R.id.lineView, false);
                }
                holder.setOnClickListener(R.id.itemHome, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("title", datasBean.getTitle());
                        bundle.putString("link", datasBean.getLink());
                        goWebPage(bundle);
                    }
                });
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
            }
        };
    }

    private void initViewBanner() {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.NOT_INDICATOR);
    }

    private void initRecyclerView() {
        rcHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcHome.setAdapter(adapter);
    }

    @Override
    public void setLoadMoreEnable(boolean isLoadMore) {
        if (isLoadMore){
            refreshLayout.setEnableLoadmore(isLoadMore);
        }
    }

    @Override
    public void showBanner(final List<BannerBean> beanList) {
        final List<String> urlList = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            urlList.add(beanList.get(i).getImagePath());
        }
        banner.update(urlList);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("title", beanList.get(position).getTitle());
                bundle.putString("link", beanList.get(position).getUrl());
                goWebPage(bundle);
            }
        });
    }

    @Override
    public void showNewProject(List<NewProjectBean.DatasBean> projectBeanList) {
        this.projectBeanList.addAll(projectBeanList);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 0;
        mPresenter.getBannerData();
        mPresenter.getNewProject(page);

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        mPresenter.getNewProject(page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        projectBeanList = null;
        unbinder.unbind();
    }
}
