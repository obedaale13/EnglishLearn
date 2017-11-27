package com.tarena.app.app;

import android.app.Application;

import com.tarena.app.huanxin.HXAccount;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
        HXAccount.initHX(this);
    }

    private void initBmob() {
        Bmob.initialize(this, "01ee8334b4ae5dcd88d68e8624026436");
    }

}
