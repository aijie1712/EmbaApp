package com.emba.embaapp.web;

import android.app.Activity;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.MainActivity;
import com.emba.embaapp.MyApplication;
import com.emba.embaapp.ui.ContentWebActivity;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc 供js调用的方法类
 */

public class WebJsClient {

    private Activity activity;

    public WebJsClient(Activity activity) {
        this.activity = activity;
    }

    /**
     * 登录成功，跳转到首页的方法
     */
    @JavascriptInterface
    public void loginToMain(String sessionID) {
        LogUtils.i("loginToMain");
        SpUtils.getInstance(MyApplication.getApplication()).saveString(AppConstant.SESSIONID_KEY, sessionID);
        MyApplication.sessionId = sessionID;
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * @param url 打开新页的接口
     */
    @JavascriptInterface
    public void openContentURL(String url) {
        LogUtils.i("openContentURL" + url);
        String allURl = url + ";jsessionid=" + MyApplication.sessionId;
        Intent intent = new Intent(activity, ContentWebActivity.class);
        intent.putExtra(ContentWebActivity.URL, allURl);
        activity.startActivity(intent);
    }

//    @JavascriptInterface
//    public void toMainActivity() {
//        Intent intent = new Intent(activity, MainActivity.class);
//        activity.startActivity(intent);
//        activity.finish();
//    }
}
