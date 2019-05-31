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

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
}
