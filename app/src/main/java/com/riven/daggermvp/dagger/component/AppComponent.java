package com.riven.daggermvp.dagger.component;

import com.riven.daggermvp.App;
import com.riven.daggermvp.dagger.module.AppModule;
import com.riven.daggermvp.dagger.ContextLife;
import com.riven.daggermvp.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Description: AppComponent
 * Author: djs
 * Date: 2019/5/24.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    App getApplication();

    /**
     * 提供App的Context
     * @return
     */
    @ContextLife("Application")
    App getContext();

    OkHttpClient getClient();

    Retrofit getRetrofit();
}
