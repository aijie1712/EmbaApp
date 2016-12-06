package com.emba.embaapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.base.BaseFragment;
import com.emba.embaapp.utils.LogUtils;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc 主页Fragment
 */

public class HomeFragment extends BaseFragment {

    private static final String URL = "url";

    private String url;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String url) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_webview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(URL) + MyApplication.sessionId;
        }
    }

    @Override
    public void attachView() {
        super.attachView();
    }

    @Override
    public void initDatas() {
        webView.loadUrl(url);
    }
}
