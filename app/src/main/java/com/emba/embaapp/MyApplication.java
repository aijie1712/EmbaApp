package com.emba.embaapp;

import android.app.Application;

import com.emba.embaapp.utils.SpUtils;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public class MyApplication extends Application{

    private static MyApplication instance;
    /**
     * 全局sessionId
     */
    public static String sessionId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        sessionId = SpUtils.getInstance(this).getString(AppConstant.SESSIONID_KEY);
    }

    public static MyApplication getApplication(){
        return instance;
    }
}
