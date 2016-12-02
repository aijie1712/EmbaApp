package com.emba.embaapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc
 */

public class NetUtils {
    /**
     * 检测当前的网络连接是否可用<br/>
     * 注意：需要添加权限&lt;uses-permission
     * android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
     *
     * @param context false没联网
     * @return
     */
    public static boolean isConnected(Context context) {
        boolean flag = false;
        try {
            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null != connManager) {
                NetworkInfo info = connManager.getActiveNetworkInfo();
                if ((null != info) && info.isAvailable()) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            LogUtils.e("NetworkInfo", "Exception", e);
        }
        return flag;
    }

    /**
     * 检测当前网络连接的类型<br/>
     * 注意：需要添加权限&lt;uses-permission
     * android:name="android.permission.ACCESS_NETWORK_STATE"/&gt;
     *
     * @param context
     * @return 返回0代表GPRS网络;返回1,代表WIFI网络;返回-1代表网络不可用
     */
    public static int getNetworkType(Context context) {
        int code = -1;
        try {
            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null != connManager) {
                NetworkInfo.State state = connManager.getNetworkInfo(
                        ConnectivityManager.TYPE_WIFI).getState();
                if (NetworkInfo.State.CONNECTED == state) {
                    code = ConnectivityManager.TYPE_WIFI;
                } else {
                    state = connManager.getNetworkInfo(
                            ConnectivityManager.TYPE_MOBILE).getState();
                    if (NetworkInfo.State.CONNECTED == state) {
                        code = ConnectivityManager.TYPE_MOBILE;
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.e("NetworkInfo", "Exception", e);
        }
        return code;
    }
}
