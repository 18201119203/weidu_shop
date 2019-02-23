package com.example.weiduapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib_core.base.mvp.BasemvpActivity;
import com.example.lib_core.base.mvp.Basepresenter;
import com.example.weiduapp.R;
import com.example.weiduapp.bean.BannerBean;
import com.example.weiduapp.bean.HomeListBean;
import com.example.weiduapp.bean.LoginBean;
import com.example.weiduapp.bean.RegBean;
import com.example.weiduapp.bean.SelectShopBean;
import com.example.weiduapp.bean.ShopBase;
import com.example.weiduapp.bean.ShopYiang;
import com.example.weiduapp.contract.ProductContract;
import com.example.weiduapp.presenter.ProductPresenter;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegActivity extends BasemvpActivity<ProductContract.IProductModel,ProductContract.ProductPresentervoid> implements ProductContract.IProductView {

    @BindView(R.id.et_yz)
    EditText et_yz;

    @BindView(R.id.et_phone)
    EditText et_phone;

    @BindView(R.id.et_pwd)
    EditText et_pwd;

    @BindView(R.id.show_pwd)
    ImageView show_pwd;

    @BindView(R.id.returnLogin)
    TextView returnLogin;

    @BindView(R.id.reg)
    TextView reg;

    private Unbinder bind;

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        bind = ButterKnife.bind(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_reg;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    /**
     * 密码明密文
     */
    Boolean isYeye = true;
    @OnClick(R.id.show_pwd)
    public void show_pwd(){
        if (isYeye){
            //明文
            et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isYeye=false;
        }else {
            //密文
            et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isYeye=true;
        }
    }

    @OnClick(R.id.reg)
    public void reg(){

        String phone = et_phone.getText().toString();
        String pwd = et_pwd.getText().toString();

        HashMap<String,String> params = new HashMap<>();
        params.put("phone",phone);
        params.put("pwd",pwd);
        presenter.getRegList(params);


    }



    @OnClick(R.id.returnLogin)
    public void returnLogin(){
        startActivity(new Intent(RegActivity.this,LoginActivity.class));
    }

    @Override
    public void successreg(RegBean regBean) {
        if("注册成功".equals(regBean.message)){
            Toast.makeText(RegActivity.this,"注册成功",Toast.LENGTH_LONG).show();
            startActivity(new Intent(RegActivity.this,LoginActivity.class));
        }else{
            Toast.makeText(RegActivity.this,regBean.message,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void successlogin(LoginBean loginBean) {

    }

    @Override
    public void successHomeData(List homeListBean) {

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

    }


    @Override
    public Basepresenter initPresenter() {
        return new ProductPresenter();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(RegActivity.this,msg,Toast.LENGTH_LONG).show();
    }



}
