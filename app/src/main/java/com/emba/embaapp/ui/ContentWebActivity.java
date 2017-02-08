package com.emba.embaapp.ui;

import android.content.Intent;
import android.text.TextUtils;

import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.base.BaseActivity;
import com.emba.embaapp.utils.SpUtils;
import com.emba.embaapp.utils.UiUtil;

/**
 * 新页面的WebView
 */
public class ContentWebActivity extends BaseActivity {

    public static final String URL = "url";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_content_web_view;
    }

    @Override
    protected void initData() {
        super.initData();
        String url = getIntent().getStringExtra(URL);
        if (TextUtils.isEmpty(url)) {
            UiUtil.showToast(this, "参数错误");
            finish();
        }
        syncCookie(MyApplication.embaCookies);
        webView.loadUrl(url);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String url = intent.getStringExtra(URL);
        if (TextUtils.isEmpty(url)) {
            UiUtil.showToast(this, "参数错误");
            finish();
        }
        syncCookie(MyApplication.embaCookies);
        webView.loadUrl(url);
    }


}
