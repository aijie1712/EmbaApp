package com.emba.embaapp.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.emba.embaapp.R;
import com.emba.embaapp.web.MyWebChromeClient;
import com.emba.embaapp.web.WebJsClient;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutId();

    protected void initView() {
        webView = ButterKnife.findById(this, R.id.webView);
        if (webView == null) {
            return;
        }
        WebSettings websetting = webView.getSettings();
        websetting.setDomStorageEnabled(true);
        String appCacheDir = this.getApplicationContext()
                .getDir("cache", Context.MODE_PRIVATE).getPath();
        websetting.setAppCachePath(appCacheDir);
        websetting.setAllowFileAccess(true);
        websetting.setAppCacheEnabled(true);
        websetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        websetting.setJavaScriptEnabled(true);

        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            websetting.setAllowUniversalAccessFromFileURLs(true);
        }

        // 为WebView设置WebViewClient处理某些操作
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.addJavascriptInterface(new WebJsClient(this), "embaApp");
    }

    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        if (webView == null) {
            super.onBackPressed();
            return;
        }
        if (webView.canGoBack()) {
            webView.goBack();
            webView.goBackOrForward(-1);
        } else {
            finish();
        }
    }


}
