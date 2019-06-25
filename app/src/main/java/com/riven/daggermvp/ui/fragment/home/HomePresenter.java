package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePAV;
import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.net.BaseObserver;
import com.riven.daggermvp.net.ResponseBean;
import com.riven.daggermvp.net.RetrofitUtils;
import com.riven.daggermvp.net.SchedulersTransformer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public class HomePresenter extends BasePAV<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    RetrofitUtils retrofitUtils;

    @Inject
    public HomePresenter() {
    }


    @Override
    public void getBannerData() {
        retrofitUtils.getBannerData()
                .compose(new SchedulersTransformer<ResponseBean<List<BannerBean>>>())
                .subscribe(new BaseObserver<ResponseBean<List<BannerBean>>>() {
                    @Override
                    protected void dealNext(ResponseBean<List<BannerBean>> listResponseBean) {
                        if (null != listResponseBean.getData() && listResponseBean.getData().size() != 0) {
                            mView.showBanner(listResponseBean.getData());
                        }
                    }

                    @Override
                    protected void dealError(Throwable ex, String toastText) {
                        mView.hideLoading();
                        mView.showToast(toastText);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }
}
