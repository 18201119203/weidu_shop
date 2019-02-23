package com.example.weiduapp.presenter;

import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.model.RetrofitModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class RetrofitPresenter extends RetrofitContract.retrofitPresenter {
    @Override
    public void getData(String url, HashMap<String, String> headparams, Class tclass) {

    }

    @Override
    public void postData(String url, HashMap<String, String> bodyparams, Class tclass) {

    }

    @Override
    public void putData(String url, HashMap<String, String> bodyparams, final Class tclass) {

        model.putData(url, bodyparams, new RetrofitModel.IRetrofitCallback() {
            @Override
            public void Success(String response) {
                if (view!=null){

                    view.SuccessData(response);
                }
            }

            @Override
            public void Failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }
        });


    }




}
