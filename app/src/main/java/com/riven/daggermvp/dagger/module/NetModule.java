package com.riven.daggermvp.dagger.module;

import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.config.Constants;
import com.riven.daggermvp.net.HTTPSTools;
import com.riven.daggermvp.net.interceptor.NetCheckInterceptor;
import com.riven.daggermvp.net.interceptor.RequestInterceptor;
import com.riven.daggermvp.net.interceptor.ResponseInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description: 网络配置
 * Author: djs
 * Date: 2019/6/4.
 */
@Module
public class NetModule {

    public NetModule(){

    }

    @Provides
    @Singleton
    public OkHttpClient providesOkHttpClient(MyApp myApp, Cache cache){

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new NetCheckInterceptor())
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(new ResponseInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(55, TimeUnit.SECONDS)
                .cache(cache)
                .sslSocketFactory(HTTPSTools.getSSLSocketFactoryValue(myApp,null),
                        (X509TrustManager) HTTPSTools.getX509TrustManager(myApp,null)[0])
                .hostnameVerifier(HTTPSTools.getHostnameVerifier(null))
                .retryOnConnectionFailure(true) //请求失败，是否可以重试
                .build();

        return client;
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient client){

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    public Cache providesCache(MyApp myApp){
        return new Cache(myApp.getExternalCacheDir(), Constants.CACHE_SIZE);
    }
}
