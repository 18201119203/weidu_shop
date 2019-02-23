package com.example.weiduapp.contract;

import com.example.lib_core.base.mvp.BaseView;
import com.example.lib_core.base.mvp.Basemodel;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.model.RetrofitModel;


import java.util.HashMap;

public interface RetrofitContract {

    abstract class retrofitPresenter extends Basepresenter<IretrofitModel,IretrofitView> {
        @Override
        public IretrofitModel getModel() {
            return new RetrofitModel();
        }

        public abstract void getData(String url,HashMap<String,String> headparams,Class tclass);
        public abstract void postData(String url,HashMap<String,String> bodyparams,Class tclass);
        public abstract void putData(String url,HashMap<String,String> bodyparams,Class tclass);
    }

    interface IretrofitModel extends Basemodel {

        void getData(String url, HashMap<String,String> params, RetrofitModel.IRetrofitCallback callback);
        void postData(String url,HashMap<String,String> bodyparams, RetrofitModel.IRetrofitCallback callback);
        void putData(String url,HashMap<String,String> bodyparams, RetrofitModel.IRetrofitCallback callback);
    }

    interface IretrofitView extends BaseView {
        <T> void  SuccessData(T data);
    }

}
