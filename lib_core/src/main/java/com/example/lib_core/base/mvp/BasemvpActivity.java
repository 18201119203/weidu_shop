package com.example.lib_core.base.mvp;


import com.example.lib_core.base.BaseActivity;

public abstract class BasemvpActivity<M extends Basemodel, P extends Basepresenter> extends BaseActivity implements BaseView{

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
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.desttach();
        }
    }


}
