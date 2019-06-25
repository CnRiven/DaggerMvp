package com.riven.daggermvp.net;

import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.bean.LoginBean;
import com.riven.daggermvp.net.apiservice.AccountApiService;
import com.riven.daggermvp.net.apiservice.HomeApiService;

import java.util.List;

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
    private HomeApiService homeApiService;

    @Inject
    public RetrofitUtils(Retrofit retrofit) {
        accountApiService = retrofit.create(AccountApiService.class);
        homeApiService = retrofit.create(HomeApiService.class);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Observable<ResponseBean<LoginBean>> postLogin(String username, String password){
        return accountApiService.postLogin(username, password);
    }

    /**
     * 获取 banner
     * @return
     */
    public Observable<ResponseBean<List<BannerBean>>> getBannerData(){
        return homeApiService.getBannerData();
    }
}
