package com.emba.embaapp.base;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.emba.embaapp.AppConstant;
import com.emba.embaapp.MainActivity;
import com.emba.embaapp.MyApplication;
import com.emba.embaapp.R;
import com.emba.embaapp.ui.ContentWebActivity;
import com.emba.embaapp.ui.LoginActivity;
import com.emba.embaapp.utils.CommonUtils;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.emba.embaapp.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Cookie;

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
        LogUtils.i("onCreate==" + this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.i("Activity onDestroy");
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
        websetting.setAllowContentAccess(true);
        websetting.setDomStorageEnabled(true);

        websetting.setDatabaseEnabled(true);
        websetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        websetting.setJavaScriptEnabled(true);

        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            websetting.setAllowUniversalAccessFromFileURLs(true);
        }

        // 为WebView设置WebViewClient处理某些操作
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.addJavascriptInterface(this, "embaApp");
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtils.i("shouldOverrideUrlLoading", "shouldOverrideUrlLoading==" + url);
            //url += "?" + SpUtils.getInstance(MyApplication.getApplication()).getString("cookie");
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String cookie = CookieManager.getInstance().getCookie(AppConstant.BASE_URL);
            LogUtils.i("cookieValue", "onPageStarted==cookie  " + cookie + "  url:" + url);
            // syncCookie(MyApplication.embaCookies);
            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            try {
                if (null == mUploadMessage) {
                    return;
                }
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                if (result == null) {
                    return;
                }
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            Toast.makeText(getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }

    private ValueCallback<Uri[]> uploadMessage;
    private static ValueCallback<Uri> mUploadMessage;
    private final static int REQUEST_SELECT_FILE = 1;
    private final static int FILECHOOSER_RESULTCODE = 2;

    public class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        // For 3.0+ Devices (Start)
        // onActivityResult attached before constructor
        protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        @TargetApi(21)
        public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            if (uploadMessage != null) {
                uploadMessage.onReceiveValue(null);
                uploadMessage = null;
            }

            uploadMessage = filePathCallback;

            Intent intent = fileChooserParams.createIntent();
            intent.setType("image/*");
            try {
                startActivityForResult(intent, REQUEST_SELECT_FILE);
            } catch (ActivityNotFoundException e) {
                uploadMessage = null;
                Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }

        //For Android 4.1 only
        protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
            mUploadMessage = uploadMsg;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
        }

        protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }
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

    public void syncCookie(List<Cookie> cookies) {
        String url = "http://beiyou.it371.cn";
        CookieSyncManager.createInstance(BaseActivity.this);
        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(true);
        if (cookies != null && cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                cm.setCookie(url, cookie.name() + "=" + cookie.value());//注意端口号和域名，这种方式可以同步所有cookie，包括sessionid
            }
        }
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 登录成功，跳转到首页的方法
     */
    @JavascriptInterface
    public void loginToMain(String sessionID, String account, String pwd, boolean saveAccount, boolean savePwd) {
        LogUtils.i("loginToMain sessionID==" + sessionID + "  account==" + account + "  pwd==" + pwd + "  saveAccount==" + saveAccount + "  savePwd==" + savePwd);
        SpUtils spUtils = SpUtils.getInstance(MyApplication.getApplication());
        spUtils.saveString(AppConstant.SESSIONID_KEY, sessionID);
        if (saveAccount) {
            spUtils.saveBoolean(AppConstant.IS_SAVE_ACCOUNT, saveAccount);
            spUtils.saveString(AppConstant.MY_ACCOUNT, account);
            if (savePwd) {
                spUtils.saveBoolean(AppConstant.IS_SAVE_PWD, savePwd);
                spUtils.saveString(AppConstant.MY_PWD, pwd);
            }
        } else {
            spUtils.saveBoolean(AppConstant.IS_SAVE_ACCOUNT, false);
            spUtils.saveString(AppConstant.MY_ACCOUNT, "");
            spUtils.saveBoolean(AppConstant.IS_SAVE_PWD, false);
            spUtils.saveString(AppConstant.MY_PWD, "");
        }

        MyApplication.sessionId = sessionID;

        // IM登录
        OkHttpUtils
                .post()
                .url(AppConstant.IM_LOGIN)
                .addParams("account", account)
                .addParams("password", pwd)
                .addParams("imei", CommonUtils.getDeviceIMEI(this))
                .addParams("version", "1.0.9")
                .addParams("osType", "android")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.i("IM登录成功:" + response);
                        // 同步webView的sessionId
                        List<Cookie> cookies = MyApplication.cookieJar.getCookieStore().getCookies();
                        MyApplication.embaCookies.clear();
                        MyApplication.embaCookies.addAll(cookies);
                        syncCookie(MyApplication.embaCookies);
                        toMain();
                    }
                });

    }

    private void toMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @JavascriptInterface
    public void downLoadFile(String url, final String fileName) {
        LogUtils.i("开始下载==url==" + url + "==fileName==" + fileName);
        Toast.makeText(BaseActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
        File file = new File(getExternalCacheDir(), fileName);
        if (file.exists()) {
            file.delete();
        }
        // 下载文件
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new FileCallBack(getExternalCacheDir().getAbsolutePath(), fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("onError==" + e.toString());
                        Toast.makeText(BaseActivity.this, "下载失败，请重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        Toast.makeText(BaseActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        LogUtils.i("onResponse==filePath==" + response.getAbsolutePath());
                        // 下载完成，点击查看
                        openFile(response);
                    }
                });

    }

    /**
     * 打开文件
     *
     * @param file
     */
    public void openFile(final File file) {
        if (!file.exists()) {
            UiUtil.showToast(this, "文件不存在");
            return;
        }
        Intent openIntent = new Intent();
        openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        openIntent.setAction(Intent.ACTION_VIEW);
        openIntent.setDataAndType(Uri.fromFile(file), getMIMEType(file));
        startActivity(openIntent);
    }

    private String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "")
            return type;
        // 在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < AppConstant.MIME_MapTable.length; i++) {
            if (end.equals(AppConstant.MIME_MapTable[i][0]))
                type = AppConstant.MIME_MapTable[i][1];
        }
        return type;
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
                onBackPressed();
            }
        });
    }

    /**
     * 退出帐号 embaApp.exitAccount();
     */
    @JavascriptInterface
    public void exitAccount() {
        MyApplication.sessionId = "";
        SpUtils.getInstance(getApplicationContext()).saveString(AppConstant.SESSIONID_KEY, "");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (webView == null) {
            finish();
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
