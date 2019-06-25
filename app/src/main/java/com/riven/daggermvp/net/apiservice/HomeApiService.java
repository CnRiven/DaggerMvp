package com.riven.daggermvp.net.apiservice;

import com.riven.daggermvp.bean.BannerBean;
import com.riven.daggermvp.net.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Description:
 * Author: djs
 * Date: 2019/6/14.
 */
public interface HomeApiService {

    @GET("/banner/json")
    Observable<ResponseBean<List<BannerBean>>> getBannerData();

}
