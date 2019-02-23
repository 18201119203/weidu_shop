package com.example.weiduapp.presenter;

import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeItemShopBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.contract.ProductContract;
import com.example.weiduapp.model.ProductModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductPresenter extends ProductContract.ProductPresentervoid {


    @Override
    public void getLoginList(HashMap<String, String> params) {

        model.getLoginList(params, new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                view.failure(msg);
            }

            @Override
            public void success(String result) {
                LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
                view.successlogin(loginBean);
            }

        });

    }

    @Override
    public void getRegList(HashMap<String, String> params) {

        model.getRegList(params, new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                view.failure(msg);
            }

            @Override
            public void success(String result) {
                RegBean regBean = new Gson().fromJson(result, RegBean.class);
                view.successreg(regBean);
            }


        });
    }

    @Override
    public void getHomeList() {

        model.getHomeList(new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                view.failure(msg);
            }

            @Override
            public void success(String result) {
                HomeItemShopBean homeItemShopBean = new Gson().fromJson(result, HomeItemShopBean.class);

                List<HomeItemShopBean.ResultBean.listBean> list = new ArrayList<>();

                list.addAll(homeItemShopBean.result.rxxp);
                list.addAll(homeItemShopBean.result.mlss);
                list.addAll(homeItemShopBean.result.pzsh);


                view.successHomeData(list);
            }
        });
    }

    @Override
    public void getBannerList() {

        model.getBannerList(new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                view.failure(msg);
            }

            @Override
            public void success(String result) {
                BannerBean bannerBean = new Gson().fromJson(result, BannerBean.class);
                view.successBanner(bannerBean);
            }
        });
    }

    @Override
    public void getShopList(String url) {

        model.getShopList(url,new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (view!=null){
                    ShopBase shopBase = new Gson().fromJson(result, ShopBase.class);
                    view.successShop(shopBase);
                }
            }
        });


    }

    @Override
    public void getselectshopData(HashMap<String, String> params) {

        model.getselectshopData(params, new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (view!=null){
                    SelectShopBean selectShopBean = new Gson().fromJson(result, SelectShopBean.class);
                    view.successSelectShopBean(selectShopBean);
                }
            }
        });


    }

    @Override
    public void getshopYiang(String id) {

        model.getshopYiang(id, new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (view!=null){
                    ShopYiang shopYiang = new Gson().fromJson(result, ShopYiang.class);
                    view.shopYiangSuccess(shopYiang);
                }
            }
        });


    }

    @Override
    public void getData(String url, HashMap<String, String> params, final Class tclass) {

        model.getData(url, params, new ProductModel.IProductCallback() {
            @Override
            public void failure(String msg) {
                if (view!=null){
                    view.failure(msg);
                }
            }

            @Override
            public void success(String result) {
                if (view!=null) {
                    Object o = new Gson().fromJson(result, tclass);
                    view.SuccessData(o);
                }
            }
        });

    }


}
