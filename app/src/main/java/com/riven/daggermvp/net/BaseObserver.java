package com.riven.daggermvp.net;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.riven.daggermvp.App;
import com.riven.daggermvp.ui.activity.login.LoginActivity;
import com.riven.daggermvp.utils.AAM;
import com.riven.daggermvp.utils.LogUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import retrofit2.HttpException;

/**
 * Description:
 * Author: djs
 * Date: 2019/6/4.
 */
public abstract class BaseObserver<T> implements Observer<T> {

    private Context mContext;
    private Activity mActivity;

    public BaseObserver() {
    }

    public BaseObserver(Context mContext) {
        this.mContext = mContext;
    }

    public BaseObserver(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onNext(T t) {
        if (t instanceof ResponseBean){
            ResponseBean responseBean = (ResponseBean) t;
            LogUtil.e("----" + responseBean.toString());
            if (HttpCode.TOKEN_NOT_VAILD == responseBean.getErrorCode()){
                AAM.getInstance().finishActivity(LoginActivity.class);
                Intent mIntent = new Intent(App.getInstance(), LoginActivity.class);
                mActivity.startActivity(mIntent);
                return;
            }
        }
        dealNext(t);
    }

    protected abstract void dealNext(T t);
    protected abstract void dealError(Throwable ex, String toastText);

    @Override
    public void onError(Throwable e) {
        String toastText = "";
        if (e instanceof ConnectException){
            toastText = "网络连接异常";
        }else if (e instanceof SocketTimeoutException){
            toastText = "网络连接超时";
        }else if (e instanceof UnknownHostException){
            toastText = "网络异常";
        }else if (e instanceof UnknownServiceException){
            toastText = "网络异常";
        }else if (e instanceof HttpException){
            toastText = "网络异常";
        } else if (e instanceof Error){
            toastText = e.getMessage();
        }
        dealError(e, toastText);
    }
}
