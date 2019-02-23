package com.example.weiduapp.bean;

import java.util.List;

public class HomeListBean {


    public String message;
    public String status;
    public Arraylist result;

    public class Arraylist{
        public List<hot> rxxp;
        public List<live> pzsh;
        public List<style> mlss;

        public class hot{
            public int id;
            public String name;
            public List<Datalist> commodityList;

            public class Datalist{
                public int commodityId;
                public String commodityName;
                public String masterPic;
                public int price;
                public int saleNum;

            }

        }
        public class live{
            public int id;
            public String name;
            public List<Datalist> commodityList;

            public class Datalist{
                public int commodityId;
                public String commodityName;
                public String masterPic;
                public int price;
                public int saleNum;

            }


        }
        public class style{

            public int id;
            public String name;
            public List<Datalist> commodityList;

            public class Datalist{
                public int commodityId;
                public String commodityName;
                public String masterPic;
                public int price;
                public int saleNum;

            }


        }


    }
}
