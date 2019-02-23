package com.example.weiduapp.contract;

import com.example.lib_core.base.mvp.BaseView;
import com.example.lib_core.base.mvp.Basemodel;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeItemShopBean;
import com.example.weiduapp.bean.HomeListBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.model.ProductModel;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

public interface ProductContract {


    abstract class ProductPresentervoid extends Basepresenter<IProductModel,IProductView>  {
        @Override
        public IProductModel getModel() {
            return new ProductModel();
        }
        public abstract void getLoginList(HashMap<String,String> params);
        public abstract void getRegList(HashMap<String,String> params);
        public abstract void getHomeList();
        public abstract void getBannerList();
        public abstract void getShopList(String url);
        public abstract void getselectshopData(HashMap<String,String> params);
        public abstract void getshopYiang(String id);
        public abstract void getData(String url,HashMap<String,String> params,Class tclass);
    }

    interface IProductModel extends Basemodel {
        void  getLoginList(HashMap<String,String> params, ProductModel.IProductCallback callback);
        void  getRegList(HashMap<String,String> params, ProductModel.IProductCallback callback);
        void  getHomeList(ProductModel.IProductCallback callback);
        void  getBannerList(ProductModel.IProductCallback callback);
        void  getShopList(String url,ProductModel.IProductCallback callback);
        void  getselectshopData(HashMap<String,String> params, ProductModel.IProductCallback callback);
        void getshopYiang(String id,ProductModel.IProductCallback callback);
        void getData(String url,HashMap<String,String> params, ProductModel.IProductCallback callback);
    }

    interface IProductView<T> extends BaseView {

        void successreg(RegBean regBean);
        void successlogin(LoginBean loginBean);
        void successHomeData(List<HomeItemShopBean.ResultBean.listBean> shopBean);
        void successBanner(BannerBean bannerBean);
        void successShop(ShopBase shopBase);
        void successSelectShopBean(SelectShopBean shopBase);
        void shopYiangSuccess(ShopYiang shopYiang);
        void SuccessData(T data);
    }



}
