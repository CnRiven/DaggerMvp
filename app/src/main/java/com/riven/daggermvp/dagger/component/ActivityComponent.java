package com.riven.daggermvp.dagger.component;

import android.app.Activity;

import com.riven.daggermvp.dagger.module.ActivityModule;
import com.riven.daggermvp.dagger.scope.ActivityScope;
import com.riven.daggermvp.ui.activity.login.LoginActivity;
import com.riven.daggermvp.ui.activity.main.MainActivity;

import dagger.Component;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(LoginActivity loginActivity);
    void inject(MainActivity loginActivity);

    Activity getActivity();
}
