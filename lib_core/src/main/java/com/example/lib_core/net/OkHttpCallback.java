package com.example.lib_core.net;

public interface OkHttpCallback {

    void failure(String msg);
    void success(String response);

}
