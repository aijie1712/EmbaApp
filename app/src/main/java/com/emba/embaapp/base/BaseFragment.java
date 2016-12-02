package com.emba.embaapp.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.emba.embaapp.R;
import com.emba.embaapp.web.MyWebChromeClient;
import com.emba.embaapp.web.WebJsClient;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public abstract class BaseFragment extends Fragment {

    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;

    protected Context mContext;

    protected WebView webView;

    public abstract
    @LayoutRes
    int getLayoutResId();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        attachView();
        initDatas();
    }

    public void attachView() {
        webView = ButterKnife.findById(parentView, R.id.webView);
        initWebView();
    }

    private void initWebView() {
        if (webView == null) {
            return;
        }
        WebSettings websetting = webView.getSettings();
        websetting.setDomStorageEnabled(true);
        String appCacheDir = activity.getApplicationContext()
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
        webView.addJavascriptInterface(new WebJsClient(getActivity()), "embaApp");
    }

    public abstract void initDatas();

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
