package com.riven.daggermvp;

import android.app.Application;

import com.riven.daggermvp.dagger.component.AppComponent;
import com.riven.daggermvp.dagger.component.DaggerAppComponent;
import com.riven.daggermvp.dagger.module.AppModule;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
public class App extends Application {

    private static App instance;

    //App dagger 注入对象
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        injectorAppComponent();
    }

    public static App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }

    void injectorAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    /**
     * 关联apiService
     *
     * @param clz
     * @param <T>
     * @return
     */
//    public static <T> T apiService(Class<T> clz) {
//        return getInstance().mHttpManager.getService(clz);
//    }
}
