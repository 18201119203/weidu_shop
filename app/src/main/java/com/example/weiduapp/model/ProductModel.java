package com.example.weiduapp.model;

import com.example.lib_core.net.OkHttpCallback;
import com.example.lib_core.net.OkHttpUtils;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.contract.ProductContract;

import java.util.HashMap;

public class ProductModel implements ProductContract.IProductModel {


    /**
     * 登陆
     * @param params
     * @param callback
     */
    @Override
    public void getLoginList(HashMap<String, String> params, final IProductCallback callback) {

        OkHttpUtils.getInstance().doPost(Api.LOGIN_URL, params, new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }

        });
    }
    /**
     * 注册
     * @param params
     * @param callback
     */
    @Override
    public void getRegList(HashMap<String, String> params, final IProductCallback callback) {
        OkHttpUtils.getInstance().doPost(Api.REG_URL, params, new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }

        });
    }

    /**
     * 首页
     * @param callback
     */
    @Override
    public void getHomeList(final IProductCallback callback) {

        OkHttpUtils.getInstance().doGet(Api.HOME_LIST,new HashMap<String, String>(),new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });

    }


    /**
     * Banner
     * @param callback
     */
    @Override
    public void getBannerList(final IProductCallback callback) {

        OkHttpUtils.getInstance().doGet(Api.HOME_BANNER,new HashMap<String, String>(), new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });

    }

    @Override
    public void getShopList(final String url, final IProductCallback callback) {

        OkHttpUtils.getInstance().doGet(url,new HashMap<String, String>(), new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });

    }

    @Override
    public void getselectshopData(HashMap<String, String> params, final IProductCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        OkHttpUtils.getInstance().doGet(Api.SELECE_SHOP + "?keyword=" + keyword + "&page=" + page + "&count=" + count,new HashMap<String, String>(), new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });
    }

    @Override
    public void getshopYiang(String id, final IProductCallback callback) {
        OkHttpUtils.getInstance().doGet(Api.Yiang_SHOP + "?commodityId=" + id,new HashMap<String, String>(), new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });
    }

    @Override
    public void getData(String url, HashMap<String, String> params, final IProductCallback callback) {

        OkHttpUtils.getInstance().doGet(url,params, new OkHttpCallback() {
            @Override
            public void failure(String msg) {
                if (callback!=null){
                    callback.failure(msg);
                }
            }

            @Override
            public void success(String response) {
                if (callback!=null){
                    callback.success(response);
                }
            }
        });

    }


    public interface IProductCallback {
        void failure(String msg);
        void success(String result);

    }
}
