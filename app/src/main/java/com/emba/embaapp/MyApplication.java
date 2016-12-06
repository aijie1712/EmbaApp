package com.emba.embaapp;

import android.app.Application;

import com.emba.embaapp.utils.SpUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

        initHttp();
    }

    private void initHttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static MyApplication getApplication(){
        return instance;
    }
}
