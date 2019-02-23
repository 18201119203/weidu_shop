package com.example.lib_core.net;


import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient okHttpClient;
    private Handler handler = new Handler();

    private OkHttpUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }


    public void doPost(String url, HashMap<String,String> params, final OkHttpCallback okHttpCallback){

        FormBody.Builder formBoy = new FormBody.Builder();

        for (Map.Entry<String, String> p : params.entrySet()) {
            formBoy.add(p.getKey(),p.getValue());
        }

        Request build = new Request.Builder().url(url).post(formBoy.build()).build();

        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallback.failure("网络异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.code()==200){
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.success(string);
                        }
                    });
                }
            }
        });
    }

    public void doGet(String url,HashMap<String,String> params, final OkHttpCallback okHttpCallback){

        Request.Builder builder = new Request.Builder();

        for (Map.Entry<String, String> p : params.entrySet()) {
            builder.addHeader(p.getKey(),p.getValue());
        }

        Request build = builder.url(url).get().build();

        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        okHttpCallback.failure("网络异常");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response.code()==200){
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okHttpCallback.success(string);
                        }
                    });
                }
            }
        });
    }



    public void unOkhttp(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }


}
