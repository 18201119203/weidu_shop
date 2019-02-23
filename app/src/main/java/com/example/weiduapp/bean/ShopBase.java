package com.example.weiduapp.bean;

import java.util.List;

public class ShopBase {
    public String message;
    public String status;
    public List<DataBean> result;

    public class DataBean{

        public String commodityId;
        public String commodityName;
        public String masterPic;
        public String price;

    }

}
