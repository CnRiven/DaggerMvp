package com.riven.daggermvp.ui.activity.login;

import android.text.TextUtils;

import com.riven.daggermvp.base.BasePAV;
import com.riven.daggermvp.bean.LoginBean;
import com.riven.daggermvp.net.BaseObserver;
import com.riven.daggermvp.net.ResponseBean;
import com.riven.daggermvp.net.RetrofitUtils;
import com.riven.daggermvp.net.SchedulersTransformer;
import com.riven.daggermvp.ui.activity.main.MainActivity;
import com.riven.daggermvp.utils.LogUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/27.
 */
public class LoginPresenter extends BasePAV<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    RetrofitUtils retrofitUtils;

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
        } else {
            retrofitUtils.postLogin(username, password)
                    .compose(new SchedulersTransformer<ResponseBean<LoginBean>>())
                    .subscribe(new BaseObserver<ResponseBean<LoginBean>>() {

                        @Override
                        public void onSubscribe(Disposable d) {
                            mView.showLoading();
                        }

                        @Override
                        public void onComplete() {
                            mView.hideLoading();
                        }

                        @Override
                        protected void dealNext(ResponseBean<LoginBean> loginBeanResponseBean) {
                            if (null != loginBeanResponseBean.getData()) {
                                LogUtil.e("userName---" + loginBeanResponseBean.getData().getUsername());
                                mView.goPage(MainActivity.class);
                            } else {
                                mView.showToast(loginBeanResponseBean.getErrorMsg());
                            }
                        }

                        @Override
                        protected void dealError(Throwable ex, String toastText) {
                            mView.showToast(toastText);
                            mView.hideLoading();
                        }
                    });

        }
        mView.hideLoading();


    }

}
