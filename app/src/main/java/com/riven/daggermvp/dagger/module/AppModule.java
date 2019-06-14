package com.riven.daggermvp.dagger.module;

import com.riven.daggermvp.App;
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
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App providesApplication(){
        return application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    App provideApplicationContext() {
        return application;
    }

}
