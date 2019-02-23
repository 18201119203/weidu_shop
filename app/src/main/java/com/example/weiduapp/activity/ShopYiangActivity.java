package com.example.weiduapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpActivity;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.bean.AddShop;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeListBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.contract.ProductContract;
import com.example.weiduapp.contract.RetrofitContract;
import com.example.weiduapp.presenter.ProductPresenter;
import com.example.weiduapp.presenter.RetrofitPresenter;
import com.example.weiduapp.widget.MyScrollView;
import com.recker.flybanner.FlyBanner;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopYiangActivity extends BasemvpActivity<RetrofitContract.IretrofitModel,RetrofitContract.retrofitPresenter> implements MyScrollView.ScrollviewListener,RetrofitContract.IretrofitView {

    @BindView(R.id.image_return)
    ImageView image_return;
    @BindView(R.id.shop_banner)
    FlyBanner shop_banner;
    @BindView(R.id.shop_price)
    TextView shop_price;
    @BindView(R.id.shop_sold)
    TextView shop_sold;
    @BindView(R.id.shop_title)
    TextView shop_title;
    @BindView(R.id.shop_weight)
    TextView shop_weight;
    @BindView(R.id.shop_jieshao)
    TextView shop_jieshao;
    @BindView(R.id.smalltitle)
    RelativeLayout smalltitle;
    @BindView(R.id.title_shop)
    TextView title_shop;
    @BindView(R.id.title_xiang)
    TextView title_xiang;
    @BindView(R.id.title_ping)
    TextView title_ping;
    @BindView(R.id.bo_title)
    TextView bo_title;
    @BindView(R.id.bo_xiang)
    TextView bo_xiang;
    @BindView(R.id.bo_ping)
    TextView bo_ping;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.scroll_change)
    MyScrollView scroll_change;
    @BindView(R.id.btn_cart)
    ImageView btn_cart;
    @BindView(R.id.btn_buy)
    ImageView btn_buy;
    @BindView(R.id.details_relat_changecolor)
    RelativeLayout details_relat_changecolor;


    private String[] split;
    private Unbinder bind;
    private String commodityId;
    private ShopYiang shopData;


    @Override
    protected void initView() {
        bind = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        scroll_change.setScrollviewListener(this);
    }

    @Override
    protected int getViewId() {

        return R.layout.activity_yiangshop;
    }


    @Subscribe(sticky = true)
    public void getShopData(ShopYiang shopYiang){


        shopData = shopYiang;


        if (shopData!=null){
            List<String> list = new ArrayList<>();
            split = shopYiang.result.picture.split(",");
            for (String s : split) {
                list.add(s);
            }

            shop_banner.setImagesUrl(list);
            shop_title.setText(shopYiang.result.commodityName);
            shop_price.setText("￥"+shopYiang.result.price);
            shop_sold.setText("已售"+shopYiang.result.saleNum+"件");
            shop_weight.setText(shopYiang.result.weight+"kg");
            shop_jieshao.setText(shopYiang.result.describe);

            //设置可以支持缩放
            web.getSettings().setSupportZoom(true);
            //设置扩大比例的缩放
            web.getSettings().setUseWideViewPort(true);
            //自适应屏幕
            web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            web.getSettings().setLoadWithOverviewMode(true);

            web.setWebViewClient(new WebViewClient());
            web.loadDataWithBaseURL(null,shopYiang.result.details,"text/html","utf-8",null);

        }


    }


    @OnClick(R.id.btn_cart)
    public void addCart(){

        shopData.result.setCount(1);
        String data = shopData.result.toString();


        HashMap<String, String> bodyparams = new HashMap<>();
        bodyparams.put("data",data);
        presenter.putData(Api.ADDSHOPCART,bodyparams,AddShop.class);


    }

    @OnClick(R.id.shop_banner)
    public void onClickImage(View view){
        EventBus.getDefault().postSticky(split);
        startActivity(new Intent(ShopYiangActivity.this,ShopYiangImageActivity.class));

    }


    /**
     * 解绑
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }

    }


    /**
     * 点击联动
     * @param view
     */

    @OnClick(R.id.title_shop)
    public void title_shop(View view){
        scroll_change.fullScroll(ScrollView.FOCUS_UP);
    }

    @OnClick(R.id.title_xiang)
    public void title_xiang(View view){
        scroll_change.scrollTo(0,800);
    }

    @OnClick(R.id.title_ping)
    public void title_ping(View view){
        scroll_change.fullScroll(ScrollView.FOCUS_DOWN);
    }

    /**
     * 滑动监听
     *
     */
    @Override
    public void OnScrollViewChage(MyScrollView scrollView, int l, int t, int oldl, int oldt) {


        smalltitle.setVisibility(View.VISIBLE);
        details_relat_changecolor.getBackground().setAlpha(128);
        View view = scrollView.getChildAt(0);

        if (t>=0&&t<800){
            bo_title.setVisibility(View.VISIBLE);
            bo_xiang.setVisibility(View.GONE);
            bo_ping.setVisibility(View.GONE);
        }if (t>=800&&t<view.getHeight()){
            bo_title.setVisibility(View.GONE);
            bo_xiang.setVisibility(View.VISIBLE);
            bo_ping.setVisibility(View.GONE);
        }if (scrollView.getHeight() + scrollView.getScrollY() == view.getHeight()){
            bo_title.setVisibility(View.GONE);
            bo_xiang.setVisibility(View.GONE);
            bo_ping.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public Basepresenter initPresenter() {
        return new RetrofitPresenter();
    }

    @Override
    public void failure(String msg) {
        if (msg!=null){
            Toast.makeText(ShopYiangActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public <T> void SuccessData(T data) {
        if (data!=null){
            Toast.makeText(ShopYiangActivity.this, (java.lang.String) data,Toast.LENGTH_LONG).show();
        }
    }


}
