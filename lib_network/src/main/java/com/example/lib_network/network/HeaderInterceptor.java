package com.example.lib_network.network;

import com.blankj.utilcode.util.SPUtils;
import com.example.lib_network.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 头部拦截器
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();//原始请求对象
        Request nrequest = originalRequest.newBuilder()
                .addHeader("userId",SpUtils.getInstance().getSp("userId"))
                .addHeader("sessionId",SpUtils.getInstance().getSp("sessionId"))
                //.addHeader("userId","270")
                //.addHeader("sessionId","1550566757244270")
                .build();

        Response response = chain.proceed(nrequest);

        return response;
    }
}
