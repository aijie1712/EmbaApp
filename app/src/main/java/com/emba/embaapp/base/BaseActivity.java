package com.emba.embaapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.MainActivity;
import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.ui.ContentWebActivity;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.emba.embaapp.web.MyWebChromeClient;
import com.emba.embaapp.web.WebJsClient;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected WebView webView;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        handler = new Handler();
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
//        webView.addJavascriptInterface(new WebJsClient(this), "embaApp");
        webView.addJavascriptInterface(this, "embaApp");
    }

    protected void initData() {

    }

    /**
     * 登录成功，跳转到首页的方法
     */
    @JavascriptInterface
    public void loginToMain(String sessionID) {
        LogUtils.i("loginToMain");
        SpUtils.getInstance(MyApplication.getApplication()).saveString(AppConstant.SESSIONID_KEY, sessionID);
        MyApplication.sessionId = sessionID;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * @param url 打开新页的接口
     */
    @JavascriptInterface
    public void openContentURL(String url) {
        LogUtils.i("openContentURL" + url);
        String allURl = url + ";jsessionid=" + MyApplication.sessionId;
        Intent intent = new Intent(this, ContentWebActivity.class);
        intent.putExtra(ContentWebActivity.URL, allURl);
        startActivity(intent);
    }

    @JavascriptInterface
    public void toMainActivity() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("toMainActivity");
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        LogUtils.i("onBackPressed");
        if (webView == null) {
            LogUtils.i("webView is null");
            finish();
            return;
        }
        if (webView.canGoBack()) {
            LogUtils.i("canGoBack");
            webView.goBack();
            webView.goBackOrForward(-1);
        } else {
            LogUtils.i("finish");
            finish();
        }
    }


}
