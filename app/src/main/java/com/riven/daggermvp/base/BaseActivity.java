package com.riven.daggermvp.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.dagger.component.ActivityComponent;
import com.riven.daggermvp.dagger.component.DaggerActivityComponent;
import com.riven.daggermvp.dagger.module.ActivityModule;
import com.riven.daggermvp.utils.MyToast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    @Nullable
    public T mPresenter;

    protected KProgressHUD mKProgressHUD;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initViewAndData();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApp.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected abstract int getLayout();

    protected abstract void initInject();

    protected abstract void initViewAndData();

    @Override
    public void goPage(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public void goPage(Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    @Override
    public void goPage(Class<? extends Activity> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, activity);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void showLoading() {
        mKProgressHUD = KProgressHUD.create(this);
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
        MyToast.showToast(this, toastStr);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        unbinder.unbind();
    }
}
