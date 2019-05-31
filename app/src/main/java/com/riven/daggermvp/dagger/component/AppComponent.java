package com.riven.daggermvp.dagger.component;

import com.riven.daggermvp.App;
import com.riven.daggermvp.dagger.module.AppModule;
import com.riven.daggermvp.dagger.ContextLife;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 提供App的Context
     * @return
     */
    @ContextLife("Application")
    App getContext();
}
