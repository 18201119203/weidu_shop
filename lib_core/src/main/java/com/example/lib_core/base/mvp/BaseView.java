package com.example.lib_core.base.mvp;

public interface BaseView {

    Basepresenter initPresenter();
    void failure(String msg);
}
