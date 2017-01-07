package com.emba.embaapp.ui;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.base.BaseActivity;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;

/**
 * splash页面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        loadUrl();
    }

    private void loadUrl() {
        SpUtils spUtils = SpUtils.getInstance(MyApplication.getApplication());
        boolean isSaveUsername = spUtils.getBoolean(AppConstant.IS_SAVE_ACCOUNT, false);
        boolean isSavePwd = spUtils.getBoolean(AppConstant.IS_SAVE_PWD, false);
        String username = "";
        String pwd = "";
        StringBuilder loginUrl = new StringBuilder(AppConstant.loginUrl);
        loginUrl.append("deviceType=android");
        if (isSaveUsername) {
            username = spUtils.getString(AppConstant.MY_ACCOUNT);
            loginUrl.append("&account=" + username);
            if (isSavePwd) {
                pwd = spUtils.getString(AppConstant.MY_PWD);
                loginUrl.append("&password=" + pwd);
            }
        }
        LogUtils.i("loginUrl==" + loginUrl);
        webView.loadUrl(loginUrl.toString());
    }
}
