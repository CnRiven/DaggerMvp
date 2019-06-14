package com.riven.daggermvp.ui.activity.login;

import com.riven.daggermvp.base.BasePresenter;
import com.riven.daggermvp.base.BaseView;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/27.
 */
public class LoginContract {

    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<View> {
        void postLogin(String username, String password);
    }

}
