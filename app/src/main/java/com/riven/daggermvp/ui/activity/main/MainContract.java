package com.riven.daggermvp.ui.activity.main;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class MainContract {
    interface View extends BaseView{

    }
    interface Presenter extends BasePresenter<View>{

    }

}
