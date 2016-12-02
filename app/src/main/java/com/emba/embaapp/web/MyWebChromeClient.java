package com.emba.embaapp.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public class MyWebChromeClient extends WebChromeClient {
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
    }
}
