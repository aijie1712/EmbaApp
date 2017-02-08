package com.emba.embaapp;

import android.app.Application;

import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    /**
     * 全局sessionId
     */
    public static String sessionId;

    public static CookieJarImpl cookieJar;

    public static List<Cookie> embaCookies;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        embaCookies = new ArrayList<>();
        sessionId = SpUtils.getInstance(this).getString(AppConstant.SESSIONID_KEY);
        initHttp();

        initFresco();
    }

    private void initFresco() {
        //fresco
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder().setBaseDirectoryPath(getExternalCacheDir())
                .setBaseDirectoryName("imageCache")
                .setMaxCacheSize(100 * ByteConstants.MB)
                .setMaxCacheSizeOnLowDiskSpace(10 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(5 * ByteConstants.MB)
                .setVersion(1)
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig()).build();
        Fresco.initialize(this, imagePipelineConfig);
    }

    private void initHttp() {
        cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }

    public static MyApplication getApplication() {
        return instance;
    }
}
