package com.example.weiduapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.weiduapp.R;
import com.example.weiduapp.adapter.ViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShopYiangImageActivity extends AppCompatActivity {

    private Unbinder bind;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopyiangimage);
        EventBus.getDefault().register(this);
    }



    @Subscribe(sticky = true)
    public void getImage(String[] image){

        bind = ButterKnife.bind(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this,image);
        viewpage.setAdapter(viewPagerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
