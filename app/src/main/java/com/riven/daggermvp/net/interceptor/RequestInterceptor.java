package com.riven.daggermvp.net.interceptor;

import android.text.TextUtils;

import com.riven.daggermvp.utils.GsonUtil;
import com.riven.daggermvp.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Description: 请求参数拦截器
 * Author: djs
 * Date: 2019/6/11.
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request oldRequest = chain.request();
        Request newRequest = null;

        if (TextUtils.equals(oldRequest.method(), "GET")) {             //Get方式
            newRequest = new Request.Builder()
                    .url(oldRequest.url())
                    .build();
        } else if (TextUtils.equals(oldRequest.method(), "POST")) {
            if (oldRequest.body() instanceof FormBody) {
                Map<String, Object> map = new HashMap<>();

                FormBody formBody = (FormBody) oldRequest.body();
                for (int i = 0; i < formBody.size(); i++) {
                    map.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                String oldJsonRequest = GsonUtil.GsonString(map);
                LogUtil.e("加密前---" + oldRequest.url() + oldJsonRequest);


                FormBody.Builder newFormBody = new FormBody.Builder();
                for (int i = 0; i < formBody.size(); i++) {
                    /**
                     * 这个位置可以进行加密处理
                     */
                    newFormBody.add(formBody.encodedName(i), formBody.encodedValue(i));
                }
                RequestBody body = newFormBody.build();
                newRequest = new Request.Builder()
                        .url(oldRequest.url())
                        .post(body)
                        .build();
                FormBody newFormBody1 = (FormBody) newRequest.body();
                Map<String, Object> map1 = new HashMap<>();
                for (int i = 0; i < newFormBody1.size(); i++) {
                    map1.put(formBody.encodedName(i), formBody.encodedValue(i));
                }
                String newJsonRequest = GsonUtil.GsonString(map1);
                LogUtil.e("加密后---" + newRequest.url() + newJsonRequest);

            } else if (oldRequest.body() instanceof MultipartBody) {
                MultipartBody multipartBody = (MultipartBody) oldRequest.body();
                if (null != multipartBody && multipartBody.size() > 0) {
                    Request.Builder requestBuilder = oldRequest.newBuilder();
                    return chain.proceed(requestBuilder.build());
                }
            }
        }
        return chain.proceed(newRequest);
    }
}
