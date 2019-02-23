package com.example.weiduapp.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.weiduapp.R;
import com.example.weiduapp.fragment.CircleFragment;
import com.example.weiduapp.fragment.HomeFragment;
import com.example.weiduapp.fragment.MyFragment;
import com.example.weiduapp.fragment.OrderFragment;
import com.example.weiduapp.fragment.ShopFragment;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BottomActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_circle:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_shop:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_order:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_my:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
    private Unbinder bind;

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        bind = ButterKnife.bind(this);
        getSupportActionBar().hide();

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setItemIconTintList(null);

        homeFragment = new HomeFragment();


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return homeFragment;
                    case 1:
                        return new CircleFragment();
                    case 2:
                        return new ShopFragment();
                    case 3:
                        return new OrderFragment();
                    case 4:
                        return new MyFragment();
                }

                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_circle);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_shop);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_order);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initView(navigation);

    }

    /**
     * 取消底部按钮动画
     * @param view
     */
    @SuppressLint("RestrictedApi")
    private void initView(BottomNavigationView view) {

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean ret = false;
        if (!ret) {
            ret = homeFragment.onKeyDown(event);  //这里的homeFragment是我们前的Fragment
        }
        if (!ret){
            ret = activityParseOnkey(keyCode);
        }
        return ret;
    }

    private boolean activityParseOnkey(int keyCode) {
        final boolean[] ret = {false};
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                break;
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this)
                        .setTitle("提示框")
                        .setMessage("确认关闭?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                ret[0] =true;
                            }
                        }).create().show();
                break;
        }
        return ret[0];
    }


}
