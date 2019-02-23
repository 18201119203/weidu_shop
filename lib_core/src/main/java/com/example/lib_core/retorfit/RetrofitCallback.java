package com.example.lib_core.retorfit;

public interface RetrofitCallback {

    void failure(String msg);
    void success(String response);

}
