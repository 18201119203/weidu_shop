package com.example.weiduapp.fragment;

import android.view.KeyEvent;
import android.view.View;

import com.example.lib_core.base.BaseFragment;
import com.example.weiduapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFragment extends BaseFragment {
    private Unbinder bind;

    @Override
    protected void initpresenter() {

    }

    @Override
    protected int getViewId() {
        return R.layout.fragmentorder;
    }

    @Override
    protected void initView(View view) {
        bind = ButterKnife.bind(this, view);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bind!=null){
            bind.unbind();
        }
    }

    @Override
    public boolean onKeyDown(KeyEvent event) {
        return false;
    }
}
