package com.riven.daggermvp.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Description:
 * Author: djs
 * Date: 2019/5/24.
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
