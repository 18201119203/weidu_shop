package com.example.weiduapp.fragment;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lib_core.base.mvp.BasemvpFragment;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.lib_core.net.OkHttpUtils;
import com.example.weiduapp.R;
import com.example.weiduapp.activity.ShopYiangActivity;
import com.example.weiduapp.adapter.HomeShopAdapter;
import com.example.weiduapp.adapter.SelectShopDataAdapter;
import com.example.weiduapp.adapter.ShopAdapter;
import com.example.weiduapp.api.Api;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeItemShopBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.contract.ProductContract;
import com.example.weiduapp.contract.ProductContract.ProductPresentervoid;
import com.example.weiduapp.presenter.ProductPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends BasemvpFragment<ProductContract.IProductModel,ProductPresentervoid> implements ProductContract.IProductView,HomeShopAdapter.onClickitem,SelectShopDataAdapter.onClickitem,ShopAdapter.getShopitemId,HomeShopAdapter.onClickitemitem{


    private Unbinder bind;
    @BindView(R.id.lv)
    RecyclerView lv;
    //private HomeListAdapter homeListAdapter;
    private ShopAdapter shopAdapter;
    private SelectShopDataAdapter selectShopDataAdapter;
    private HomeShopAdapter homeShopAdapter;


    @Override
    protected int getViewId() {
        return R.layout.fragmenthome;
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
       /* homeListAdapter = new HomeListAdapter(getActivity());
        homeListAdapter.initOnClick(this);*/
        homeShopAdapter = new HomeShopAdapter(getActivity());
        homeShopAdapter.setonClickitemitem(this);
        homeShopAdapter.setClickitem(this);
        lv.setAdapter(homeShopAdapter);
    }



    @Override
    public Basepresenter initPresenter() {
        return new ProductPresenter();
    }

    @Override
    protected void initpresenter() {
        presenter.getHomeList();
        presenter.getBannerList();
    }


    /**
     * 数据获取成功
     */

    @Override
    public void successHomeData(List shopBean) {
        homeShopAdapter.setSlist(shopBean);
    }
    @Override
    public void successBanner(BannerBean bannerBean) {
        homeShopAdapter.setBlist(bannerBean.getResult());
    }


    @Override
    public void failure(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (bind!=null){
            bind.unbind();
        }
        OkHttpUtils.getInstance().unOkhttp();
    }
    /**
     * 无用
     * @param regBean
     */
    @Override
    public void successreg(RegBean regBean) { }

    @Override
    public void successlogin(LoginBean loginBean) { }



    @BindView(R.id.hot)
    TextView hot;
    @BindView(R.id.style)
    TextView style;
    @BindView(R.id.live)
    TextView live;
    @BindView(R.id.xlv)
    XRecyclerView xlv;
    @BindView(R.id.xlv2)
    XRecyclerView xlv2;
    @BindView(R.id.shop_class1)
    ImageView shop_class;
    @BindView(R.id.et_seleceshop)
    EditText et_seleceshop;
    @BindView(R.id.select1)
    TextView select;
    @BindView(R.id.Shop_Home)
    ConstraintLayout Shop_Home;
    @BindView(R.id.shop_view)
    ConstraintLayout shop_view;

    private int labelId;
    private int page = 1;
    private int count = 5;
    private int page1 = 1;
    private int count1 = 5;

   /* @Override
    public void onclivk(int positions) {
        if (!"".equals(positions+"")){
            labelId=positions;
            Shop_Home.setVisibility(View.GONE);
            shop_view.setVisibility(View.VISIBLE);
            if (positions==1002){
                hot.setVisibility(View.VISIBLE);
                style.setVisibility(View.GONE);
                live.setVisibility(View.GONE);
            }else if (positions==1003){
                style.setVisibility(View.VISIBLE);
                hot.setVisibility(View.GONE);
                live.setVisibility(View.GONE);
            }else if (positions==1004){
                live.setVisibility(View.VISIBLE);
                hot.setVisibility(View.GONE);
                style.setVisibility(View.GONE);
            }
        }
        initShopView();
        initShopData();
    }*/



    @OnClick(R.id.select)
    public void selectShop(View view){
        initSelectShopView();
        initSelectShopdata();
    }

    private void initSelectShopView() {
        lv.setVisibility(View.GONE);
        xlv2.setVisibility(View.VISIBLE);
        xlv2.setLayoutManager(new GridLayoutManager(getActivity(),2));
        selectShopDataAdapter = new SelectShopDataAdapter(getActivity());
        selectShopDataAdapter.setOnclickitem(this);
        xlv2.setAdapter(selectShopDataAdapter);
        xlv2.setPullRefreshEnabled(true);
        xlv2.setLoadingMoreEnabled(true);
        //刷新加载
        xlv2.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initSelectShopdata();
            }

            @Override
            public void onLoadMore() {
                page++;
                initSelectShopdata();
            }
        });
    }

    @Override
    public void successSelectShopBean(SelectShopBean shopBase) {

        if (shopBase!=null){
            if (page==1){
                selectShopDataAdapter.setList(shopBase.result);
            }else{
                selectShopDataAdapter.addList(shopBase.result);
            }
            xlv2.loadMoreComplete();
            xlv2.refreshComplete();
        }

    }



    //向p层传值
    private void initSelectShopdata() {
        String s = et_seleceshop.getText().toString();
        if ("".equals(s)){
            return;
        }
        HashMap<String,String> params = new HashMap<>();
        params.put("keyword",s);
        params.put("page",page1+"");
        params.put("count",count1+"");
        presenter.getselectshopData(params);
    }


    private void initShopView() {
        xlv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        shopAdapter = new ShopAdapter(getActivity());
        shopAdapter.getItemId(this);
        xlv.setAdapter(shopAdapter);
        xlv.setPullRefreshEnabled(true);
        xlv.setLoadingMoreEnabled(true);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initShopData();
            }

            @Override
            public void onLoadMore() {
                page++;
                initShopData();
            }
        });

    }
    private void initShopData() {

        if (presenter!=null){
            presenter.getShopList(Api.HOME_SHOP+"?labelId="+labelId+"&page="+page+"&count="+count);
        }

    }

    @Override
    public void successShop(ShopBase shopBase) {

        if (page==1){
            shopAdapter.setList(shopBase.result);
        }else{
            shopAdapter.addList(shopBase.result);
        }

        xlv.loadMoreComplete();
        xlv.refreshComplete();

    }



    @Override
    public boolean onKeyDown(KeyEvent event) {

        boolean ret = false;
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_DPAD_UP:
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                break;
            case KeyEvent.KEYCODE_BACK:
                if (Shop_Home.getVisibility()==View.GONE){
                    Shop_Home.setVisibility(View.VISIBLE);
                    shop_view.setVisibility(View.GONE);
                    page=1;
                    ret = true;
                }
                if (lv.getVisibility()==View.GONE){
                    lv.setVisibility(View.VISIBLE);
                    xlv2.setVisibility(View.GONE);
                    page=1;
                    ret = true;
                }
                break;
        }
        return ret;
    }


    private String id;
    @Override
    public void getId(String id) {

        this.id = id;
        initShopYiang(id);

    }

    private void initShopYiang(String id) {

        presenter.getshopYiang(id);

    }

    @Override
    public void shopYiangSuccess(ShopYiang shopYiang) {

        EventBus.getDefault().postSticky(shopYiang);
        startActivity(new Intent(getActivity(),ShopYiangActivity.class));

    }

    @Override
    public void SuccessData(Object data) {

    }
    @Override
    public void getitemid(String id) {
        presenter.getshopYiang(id);
    }



    @Override
    public void getName(int positions) {
        if (!"".equals(id+"")){
            labelId=positions;
            Shop_Home.setVisibility(View.GONE);
            shop_view.setVisibility(View.VISIBLE);
            if (positions==1002){
                hot.setVisibility(View.VISIBLE);
                style.setVisibility(View.GONE);
                live.setVisibility(View.GONE);
            }else if (positions==1003){
                style.setVisibility(View.VISIBLE);
                hot.setVisibility(View.GONE);
                live.setVisibility(View.GONE);
            }else if (positions==1004){
                live.setVisibility(View.VISIBLE);
                hot.setVisibility(View.GONE);
                style.setVisibility(View.GONE);
            }
        }
        initShopView();
        initShopData();
    }


    @Override
    public void getitemName(int id) {
        presenter.getshopYiang(id+"");
    }


}
