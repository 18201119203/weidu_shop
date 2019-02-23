package com.example.weiduapp.api;

import com.example.lib_core.base.BaseApi;

public class Api {
    public static String LOGIN_URL = BaseApi.WEIDU_URL+"small/user/v1/login";
    public static String REG_URL = BaseApi.WEIDU_URL+"small/user/v1/register";
    public static String HOME_BANNER = BaseApi.WEIDU_URL+"small/commodity/v1/bannerShow";
    public static String HOME_LIST = BaseApi.WEIDU_URL+"small/commodity/v1/commodityList";
    public static String HOME_SHOP = BaseApi.WEIDU_URL+"small/commodity/v1/findCommodityListByLabel";
    public static String SELECE_SHOP = BaseApi.WEIDU_URL+"small/commodity/v1/findCommodityByKeyword";
    public static String Yiang_SHOP = BaseApi.WEIDU_URL+"small/commodity/v1/findCommodityDetailsById";
    public static String SHOPCART = BaseApi.WEIDU_URL+"small/order/verify/v1/findShoppingCart";

    public static String ADDSHOPCART = BaseApi.WEIDU_URL+"small/order/verify/v1/syncShoppingCart";

}
