package com.riven.daggermvp.dagger.module;

import com.riven.daggermvp.MyApp;
import com.riven.daggermvp.dagger.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
@Module
public class AppModule {
    private final MyApp application;

    public AppModule(MyApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MyApp providesApplication(){
        return application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    MyApp provideApplicationContext() {
        return application;
    }

}
