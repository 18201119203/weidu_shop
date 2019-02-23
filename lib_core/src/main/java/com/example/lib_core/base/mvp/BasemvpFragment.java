package com.example.lib_core.base.mvp;

import com.example.lib_core.base.BaseFragment;

public abstract class BasemvpFragment<M extends Basemodel, P extends Basepresenter> extends BaseFragment implements BaseView {

    public P presenter;
    public M model;

    @Override
    protected void initData() {
        presenter = (P) initPresenter();
        if (presenter!=null){
            model = (M) presenter.getModel();
            if (model !=null){
                presenter.attach(model,this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.desttach();
        }
    }
}
