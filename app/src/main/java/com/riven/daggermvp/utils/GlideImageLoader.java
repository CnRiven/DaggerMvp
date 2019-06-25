package com.riven.daggermvp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.riven.daggermvp.R;
import com.youth.banner.loader.ImageLoader;

/**
 * Description: 用于 Home 页加载 Banner
 * Author: djs
 * Date: 2019/6/14.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        GlideApp.with(context)
                .load(path)
                .placeholder(R.mipmap.placeholder_img)
                .error(R.mipmap.placeholder_img)
                .into(imageView);
    }
}
