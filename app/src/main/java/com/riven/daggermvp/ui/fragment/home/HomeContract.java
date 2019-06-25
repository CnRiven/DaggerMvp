package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;
import com.riven.daggermvp.bean.BannerBean;

import java.util.List;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeContract  {
    interface View extends BaseView{
        void showBanner(List<BannerBean> beanList);
    }
    interface Presenter extends BasePresenter<View>{
        void getBannerData();
    }
}
