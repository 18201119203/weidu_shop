package com.example.lib_core.retorfit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetorfitService {

    @POST
    @FormUrlEncoded
    Call<UserEntity> reg(@Url String url, @FieldMap HashMap<String,String> params);

    @GET("small/user/v1/register")
    Call<UserEntity> reg2(@Query("phone")String p, @Query("pwd")String pwd, @Body String body);

    @PUT
//  @Headers({"Content-Type: application/json","Accept: application/json"})
    Call<UserEntity> updateNickname(@Header("userId") String id, @Header("sessionId") String sid, @Field("nickName") String name);


}