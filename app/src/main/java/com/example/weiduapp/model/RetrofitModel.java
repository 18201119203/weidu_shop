package com.example.weiduapp.model;

import android.annotation.SuppressLint;
import com.example.lib_network.network.RetrofitUtils;
import com.example.lib_network.network.RxUtils;
import com.example.weiduapp.api.RetrofitServerApi;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.contract.RetrofitContract;
import java.util.HashMap;
import io.reactivex.functions.Consumer;

public class RetrofitModel implements RetrofitContract.IretrofitModel {


    @Override
    public void getData(String url, HashMap<String, String> params, RetrofitModel.IRetrofitCallback callback) {

    }

    @Override
    public void postData(String url, HashMap<String, String> bodyparams, RetrofitModel.IRetrofitCallback callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void putData(String url, HashMap<String, String> bodyparams, final IRetrofitCallback callback) {

        RetrofitUtils.getInstance().createService(RetrofitServerApi.class)
                .doPut(url,bodyparams)
                .compose(RxUtils.<AddShop>schdulers())
                .subscribe(new Consumer<AddShop>() {
                    @Override
                    public void accept(AddShop addShop) throws Exception {

                        if (callback!=null){
                            callback.Success(addShop.message);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("网络异常,请重新加载");
                        }
                    }
                });
    }

    private IRetrofitCallback iRetrofitCallback;

    public interface IRetrofitCallback{
        void Success(String response);
        void Failure(String msg);
    }

}
