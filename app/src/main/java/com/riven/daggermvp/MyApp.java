package com.riven.daggermvp;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.riven.daggermvp.dagger.component.AppComponent;
import com.riven.daggermvp.dagger.component.DaggerAppComponent;
import com.riven.daggermvp.dagger.module.AppModule;

/**
 * Description: Application
 * Author: djs
 * Date: 2019/5/24.
 */
public class MyApp extends MultiDexApplication {

    private static MyApp instance;

    //MyApp dagger 注入对象
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        injectorAppComponent();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApp getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }

    void injectorAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

}
