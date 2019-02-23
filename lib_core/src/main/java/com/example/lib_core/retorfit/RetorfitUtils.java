package com.example.lib_core.retorfit;

import com.example.lib_core.base.BaseApi;
import com.example.lib_core.net.OkHttpUtils;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetorfitUtils {

    private static RetorfitUtils retorfitUtils;
    private final Retrofit retrofit;

    private RetorfitUtils() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient ok = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        //构建retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.WEIDU_URL)//主机名
                .addConverterFactory(GsonConverterFactory.create())//创建gson解析器
                .client(ok)//关联ok
                .build();

    }

    public static RetorfitUtils getInstance(){
        if (retorfitUtils==null){
            synchronized (OkHttpUtils.class){
                if (retorfitUtils==null){
                    retorfitUtils = new RetorfitUtils();
                }
            }
        }
        return retorfitUtils;
    }

    public void doConnenter(String url, HashMap<String,String> params, final RetrofitCallback callback){

        //
        RetorfitService retorfitService = retrofit.create(RetorfitService.class);

        retorfitService.reg(url,params).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.code()==200){
                    String message = response.body().message;
                    if (callback!=null){
                        callback.success(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                if (callback!=null){
                    callback.failure("网络不稳定,请稍后重试");
                }
            }
        });


    }





}
