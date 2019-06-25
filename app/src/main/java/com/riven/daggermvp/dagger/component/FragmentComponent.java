package com.riven.daggermvp.dagger.component;

import android.app.Activity;

import com.riven.daggermvp.dagger.module.FragmentModule;
import com.riven.daggermvp.dagger.scope.FragmentScope;
import com.riven.daggermvp.ui.fragment.article.ArticleFragment;
import com.riven.daggermvp.ui.fragment.home.HomeFragment;
import com.riven.daggermvp.ui.fragment.mine.MineFragment;

import dagger.Component;

/**
 * Description: FragmentComponent
 * Author: djs
 * Date: 2019/5/28.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {


    void inject(HomeFragment homeFragment);
    void inject(ArticleFragment articleFragment);
    void inject(MineFragment mineFragment);

    Activity getActivity();
}
