package com.example.weiduapp.bean;

public class ShopYiang {


    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {

        public String categoryId;
        public String categoryName;
        public String commentNum;
        public String commodityId;
        public String commodityName;
        public String describe;
        public String details;
        public String picture;
        public String price;
        public String saleNum;
        public String stock;
        public String weight;
        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "[{" +
                    "commodityId:" + commodityId +
                    ",count:" + count +
                    "}]";
        }
    }
}
