package com.riven.daggermvp.net;

import com.riven.daggermvp.bean.LoginBean;
import com.riven.daggermvp.net.apiservice.AccountApiService;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Description: 网络请求
 * Author: djs
 * Date: 2019/6/4.
 */
public class RetrofitUtils {

    private AccountApiService accountApiService;

    @Inject
    public RetrofitUtils(Retrofit retrofit) {
        accountApiService = retrofit.create(AccountApiService.class);
    }

    public Observable<ResponseBean<LoginBean>> postLogin(String username, String password){
        return accountApiService.postLogin(username, password);
    }
}
