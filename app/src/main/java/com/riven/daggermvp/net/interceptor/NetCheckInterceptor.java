package com.riven.daggermvp.net.interceptor;

import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.utils.AppNetworkTools;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Description: 网络检查拦截器
 * Author: djs
 * Date: 2019/6/12.
 */
public class NetCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!AppNetworkTools.isNetworkConnected(MyApp.getInstance())){
            throw new UnknownHostException("no network is connected");
        }
        return chain.proceed(chain.request().newBuilder().build());
    }
}
