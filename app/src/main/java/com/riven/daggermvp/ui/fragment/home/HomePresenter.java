package com.riven.daggermvp.ui.fragment.home;

import com.riven.daggermvp.base.BasePAV;
import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.bean.NewProjectBean;
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

    private int loadingFlag = 2;

    @Inject
    RetrofitUtils retrofitUtils;

    @Inject
    public HomePresenter() {
    }

    private void pageHide(){
        if (loadingFlag == 0){
            mView.hideLoading();
        }
    }

    /**
     * 获取Banner
     */
    @Override
    public void getBannerData() {
        mView.showLoading();
        retrofitUtils.getBannerData()
                .compose(new SchedulersTransformer<ResponseBean<List<BannerBean>>>())
                .subscribe(new BaseObserver<ResponseBean<List<BannerBean>>>() {
                    @Override
                    protected void dealNext(ResponseBean<List<BannerBean>> listResponseBean) {
                        if (null != listResponseBean.getData() && listResponseBean.getData().size() != 0) {
                            mView.showBanner(listResponseBean.getData());
                            loadingFlag--;
                            pageHide();
                        }
                    }

                    @Override
                    protected void dealError(Throwable ex, String toastText) {
                        mView.hideLoading();
                        mView.showToast(toastText);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 获取最新项目
     */
    @Override
    public void getNewProject() {
        retrofitUtils.getNewProjectData()
                .compose(new SchedulersTransformer<ResponseBean<NewProjectBean>>())
                .subscribe(new BaseObserver<ResponseBean<NewProjectBean>>() {
                    @Override
                    protected void dealNext(ResponseBean<NewProjectBean> listResponseBean) {
                        if (null != listResponseBean){
                            NewProjectBean data = listResponseBean.getData();
                            if (null != data){
                                List<NewProjectBean.DatasBean> datas = data.getDatas();
                                if (datas != null && datas.size() > 0){
                                    mView.showNewProject(datas);
                                    loadingFlag--;
                                    pageHide();
                                }
                            }
                        }
                    }

                    @Override
                    protected void dealError(Throwable ex, String toastText) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
