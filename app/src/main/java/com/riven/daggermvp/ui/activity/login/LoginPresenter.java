package com.riven.daggermvp.ui.activity.login;

import android.text.TextUtils;

import com.riven.daggermvp.base.BasePAV;
import com.riven.daggermvp.ui.activity.main.MainActivity;

import javax.inject.Inject;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/27.
 */
public class LoginPresenter extends BasePAV<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void postLogin(String username, String password) {
        mView.showLoading();
        if (TextUtils.isEmpty(username)) {
            mView.showToast("账号不能为空");
        } else if (TextUtils.isEmpty(password)) {
            mView.showToast("密码不能为空");
        } else if (!(TextUtils.equals("111", username)
                && (TextUtils.equals("111", password)))) {
            mView.showToast("账号密码不正确");
        } else {
            mView.showToast("登录成功");
            mView.goPage(MainActivity.class);
        }
        mView.hideLoading();
    }

}
