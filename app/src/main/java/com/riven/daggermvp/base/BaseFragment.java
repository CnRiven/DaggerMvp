package com.riven.daggermvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.dagger.component.DaggerFragmentComponent;
import com.riven.daggermvp.dagger.component.FragmentComponent;
import com.riven.daggermvp.dagger.module.FragmentModule;
import com.riven.daggermvp.utils.MyToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    @Nullable
    protected T mPresenter;

    protected Unbinder unbinder;
    protected View mRootView;
    protected KProgressHUD mKProgressHUD;

    protected abstract int getLayoutId();

    protected abstract void initInject();

    protected abstract void initEventAndData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        unbinder = ButterKnife.bind(this, mRootView);
        initInject();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        unbinder = ButterKnife.bind(this, view);
        initEventAndData();
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(MyApp.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void goPage(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    @Override
    public void goPage(Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    @Override
    public void goPage(Class<? extends Activity> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), activity);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        mKProgressHUD = KProgressHUD.create(getActivity());
        mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
    }

    @Override
    public void hideLoading() {
        if (mKProgressHUD != null) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void showToast(String toastStr) {
        MyToast.showToast(getActivity(), toastStr);
    }
}
