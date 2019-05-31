package com.riven.daggermvp.dagger.module;

import android.app.Activity;

import com.riven.daggermvp.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return mActivity;
    }
}
