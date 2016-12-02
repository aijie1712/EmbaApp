package com.emba.embaapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.emba.embaapp.AppConstant;

/**
 * Created by Administrator on 2016-12-02
 *
 * @desc SharedPreferences 工具类
 */

public class SpUtils {

    private final String spName = "userInfo";

    private static SpUtils instance;

    private SharedPreferences sharedPreferences;

    private SpUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static SpUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SpUtils.class) {
                if (instance == null) {
                    instance = new SpUtils(context);
                }
            }
        }
        return instance;
    }

    public void saveString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void saveBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public Boolean getBoolean(String key, Boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void loginSuccess() {
        saveBoolean(AppConstant.IS_LOGIN, true);
    }

    public Boolean isLogin() {
        return getBoolean(AppConstant.IS_LOGIN, false);
    }
}
