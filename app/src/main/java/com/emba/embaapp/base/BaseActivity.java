package com.emba.embaapp.base;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import com.emba.embaapp.ui.SplashActivity;
import com.emba.embaapp.utils.LogUtils;
import com.emba.embaapp.utils.SpUtils;
import com.emba.embaapp.utils.UiUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import butterknife.ButterKnife;
import okhttp3.Call;

public abstract class BaseActivity extends AppCompatActivity {
    protected WebView webView;

    private Handler handler;

    private final String[][] MIME_MapTable = {
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"}, {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"}, {".rtf", "application/rtf"},
            {".sh", "text/plain"}, {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"}, {".txt", "text/plain"},
            {".wav", "audio/x-wav"}, {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"}, {".xml", "text/plain"},
            {".z", "application/x-compress"}, {".zip", "application/zip"},
            {".rar", "application/x-rar-compressed"}, {"", "*/*"}
    };


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
        websetting.setCacheMode(WebSettings.LOAD_DEFAULT);
        websetting.setJavaScriptEnabled(true);

        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            websetting.setAllowUniversalAccessFromFileURLs(true);
        }

        // 为WebView设置WebViewClient处理某些操作
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.addJavascriptInterface(this, "embaApp");
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

    /**
     * 登录成功，跳转到首页的方法
     */
    @JavascriptInterface
    public void loginToMain(String sessionID,String account,String pwd,boolean saveAccount,boolean savePwd) {
        LogUtils.i("loginToMain sessionID=="+sessionID+"  account=="+account+"  pwd=="+pwd+"  saveAccount=="+saveAccount+"  savePwd=="+savePwd);
        SpUtils spUtils = SpUtils.getInstance(MyApplication.getApplication());
        spUtils.saveString(AppConstant.SESSIONID_KEY, sessionID);
        if (saveAccount) {
            spUtils.saveBoolean(AppConstant.IS_SAVE_ACCOUNT,saveAccount);
            spUtils.saveString(AppConstant.MY_ACCOUNT,account);
            if (savePwd) {
                spUtils.saveBoolean(AppConstant.IS_SAVE_PWD,savePwd);
                spUtils.saveString(AppConstant.MY_PWD,pwd);
            }
        }

        MyApplication.sessionId = sessionID;
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
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
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
        Intent intent = new Intent(this, SplashActivity.class);
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
