package com.example.weiduapp.api;

import com.example.weiduapp.bean.AddShop;
import java.util.HashMap;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitServerApi {

    @PUT
    Observable<AddShop> doPut(@Url String ur,@QueryMap HashMap<String,String> bodyparms);

    @GET()
    Observable<AddShop> getProducts(@QueryMap HashMap<String,String> parms);

}
