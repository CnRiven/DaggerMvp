package com.riven.daggermvp.dagger.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.riven.daggermvp.dagger.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Author: djs
 * Date: 2019/5/28.
 */
@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }
}
