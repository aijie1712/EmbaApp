package com.emba.embaapp.ui;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.R;
import com.emba.embaapp.base.BaseActivity;
import com.emba.embaapp.utils.NetUtils;
import com.emba.embaapp.utils.UiUtil;

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
        if (NetUtils.isConnected(this.getApplicationContext())) {
            webView.loadUrl(AppConstant.loginUrl);
//            webView.loadUrl("file:///android_asset/guide.html");
        } else {
            UiUtil.showToast(this,"没有网络，请先设置网络");
            this.finish();
        }
    }
}
