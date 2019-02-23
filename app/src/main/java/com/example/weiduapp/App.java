package com.example.weiduapp;

import android.content.Context;

import com.example.lib_network.BaseApp;
import com.example.weiduapp.umlogin.Contstans;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends BaseApp {

    public static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
        initUmeng();

    }

    private void initUmeng() {
        //第三方登陆
        UMConfigure.init(mcontext, Contstans.APPKEY
                ,null ,UMConfigure.DEVICE_TYPE_PHONE,null);
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

        Fresco.initialize(this);
        //gug
        CrashReport.initCrashReport(getApplicationContext(), "d9883bc2a8", true);

    }

}
