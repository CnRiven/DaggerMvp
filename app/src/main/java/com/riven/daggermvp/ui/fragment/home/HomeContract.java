package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;
import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.bean.NewProjectBean;

import java.util.List;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeContract  {
    interface View extends BaseView{
        void setLoadMoreEnable(boolean isLoadMore);
        void showBanner(List<BannerBean> beanList);
        void showNewProject(List<NewProjectBean.DatasBean> datasBeanList);
    }
    interface Presenter extends BasePresenter<View>{
        void getBannerData();
        void getNewProject(int page);
    }
}
