package com.emba.embaapp.ui;

import android.content.Intent;
import android.text.TextUtils;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.base.BaseActivity;
import com.emba.embaapp.model.EmbaLoginModel;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        String userName = SpUtils.getInstance(MyApplication.getApplication())
                .getString(AppConstant.MY_ACCOUNT);
        if (TextUtils.isEmpty(userName)) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        } else {
            // 本地登陆
            login();
//            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//            finish();
        }
    }

    private void login() {
        SpUtils spUtils = SpUtils.getInstance(MyApplication.getApplication());
        String userName = spUtils.getString(AppConstant.MY_ACCOUNT);
        String pwd = spUtils.getString(AppConstant.MY_PWD);
        OkHttpUtils
                .post()
                .url(AppConstant.URL_LOGIN)
                .addParams("ReturnUrl", "serviceNeeds")
                .addParams("deviceType", "android")
                .addParams("account", userName)
                .addParams("password", pwd)
                .addParams("remAccount", "on")
                .addParams("remPassword", "on")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.i("闪屏登陆成功：" + response);
                        Gson gson = new Gson();
                        EmbaLoginModel embaLoginModel = gson.fromJson(response, EmbaLoginModel.class);
                        if (embaLoginModel != null) {
                            if (embaLoginModel.getStatus().equalsIgnoreCase(EmbaLoginModel.SUCCESS)) {
                                // 登录成功
                                loginToMain(embaLoginModel.getSessionId(), embaLoginModel.getAccount(), embaLoginModel.getPassword(), true, true);
                            } else {
                                // 登录失败
                                exitAccount();
                            }
                        }
                    }
                });
//        webView.loadUrl(AppConstant.URL_LOGIN+"/?ReturnUrl=serviceNeeds&deviceType=android&account="
//                +userName+"&password="+pwd+"&remAccount=on&remPassword=on");
    }
}
