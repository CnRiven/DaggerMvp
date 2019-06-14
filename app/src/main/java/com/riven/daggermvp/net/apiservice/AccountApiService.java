package com.riven.daggermvp.net.apiservice;

import com.riven.daggermvp.bean.LoginBean;
import com.riven.daggermvp.net.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Description: AccountApiService
 * Author: djs
 * Date: 2019/6/4.
 */
public interface AccountApiService {

    @POST("/user/login")
    @FormUrlEncoded
    Observable<ResponseBean<LoginBean>> postLogin(@Field("username") String username,
                                                  @Field("password") String password);
}
