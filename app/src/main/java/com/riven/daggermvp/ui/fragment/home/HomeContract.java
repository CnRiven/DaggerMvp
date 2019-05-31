package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomeContract  {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{
        void showView(String str);
    }
}
