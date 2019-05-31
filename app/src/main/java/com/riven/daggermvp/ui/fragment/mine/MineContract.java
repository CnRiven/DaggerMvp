package com.riven.daggermvp.ui.fragment.mine;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class MineContract {
    interface View extends BaseView{
    }


    interface Presenter extends BasePresenter<View>{
        void showStr(String str);
    }
}
