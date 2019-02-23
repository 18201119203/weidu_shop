package com.example.weiduapp.fragment;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.BaseFragment;
import com.example.lib_core.base.mvp.BasemvpFragment;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.lib_network.utils.SpUtils;
import com.example.weiduapp.CartCallback.NotifyNum;
import com.example.weiduapp.R;
import com.example.weiduapp.adapter.ShopCartAdapter;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeListBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopCartBean;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.contract.ProductContract;
import com.example.weiduapp.presenter.ProductPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

public class ShopFragment extends BasemvpFragment<ProductContract.IProductModel,ProductContract.ProductPresentervoid> implements ProductContract.IProductView,NotifyNum {
    private Unbinder bind;

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.jie)
    TextView jie;
    private ShopCartAdapter shopCartAdapter;
    private ShopCartBean shopCartBean;


    @Override
    protected int getViewId() {
        return R.layout.fragmentshop;
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this,view);
    }

    @Override
    protected void initpresenter() {
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()){
                    for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
                        resultBean.ischelick=true;
                    }
                }else{
                    for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
                        resultBean.ischelick=false;
                    }
                }
                shopCartAdapter.notifyDataSetChanged();
                zPrice();
            }
        });

        SharedPreferences sp = SpUtils.getInstance().getSp();
        String userId = sp.getString("userId", "");
        String sessionId = sp.getString("sessionId", "");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("sessionId",sessionId);

        presenter.getData(Api.SHOPCART+"?userId="+userId+"&sessionId="+sessionId,hashMap,ShopCartBean.class);
    }
    @Override
    public boolean onKeyDown(KeyEvent event) {
        return false;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    @Override
    public Basepresenter initPresenter() {
        return new ProductPresenter();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void successreg(RegBean regBean) {

    }

    @Override
    public void successlogin(LoginBean loginBean) {

    }

    @Override
    public void successHomeData(List shopBean) {

    }


    @Override
    public void successBanner(BannerBean bannerBean) {

    }

    @Override
    public void successShop(ShopBase shopBase) {

    }

    @Override
    public void successSelectShopBean(SelectShopBean shopBase) {

    }

    @Override
    public void shopYiangSuccess(ShopYiang shopYiang) {

    }

    @Override
    public void SuccessData(Object data) {
        shopCartBean = (ShopCartBean) data;
        for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
            resultBean.ischelick = false;
        }
        shopCartAdapter = new ShopCartAdapter(getActivity(), shopCartBean.result, this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(false);
        rv.setAdapter(shopCartAdapter);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initpresenter();
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                rv.loadMoreComplete();
            }
        });

    }


    @Override
    public void notifynum() {
        zPrice();
    }

    @Override
    public void isCheck(boolean che) {
        checkbox.setChecked(che);
    }

    private void zPrice(){
        double price=0;
        for (ShopCartBean.ResultBean resultBean : shopCartBean.result) {
            if (resultBean.ischelick){
                price += resultBean.num * resultBean.price;
            }
        }
        checkbox.setText("¥:" + price);
    }

}
