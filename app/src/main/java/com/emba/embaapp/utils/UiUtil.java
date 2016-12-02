package com.emba.embaapp.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author YH
 */
public class UiUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable mRunnable = new Runnable() {
        public void run() {
            if (mToast != null) {
                mToast.cancel();
                mToast = null;              // toast 隐藏后，将其置为 null
            }
        }
    };

    /**
     * 显示toast信息
     *
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示 
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示toast信息
     *
     * @param context
     * @param message
     */
    public static void showToastLong(Context context, String message) {
        mHandler.removeCallbacks(mRunnable);
        if (mToast == null) {           // 只有 mToast == null 时才重新创建，否则只需更改提示文字
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示 
        mToast.setText(message);
        mHandler.postDelayed(mRunnable, 2000);  // 延迟 duration 事件隐藏 toast
        mToast.show();
    }

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    @SuppressWarnings("deprecation")
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 实现粘贴功能
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String paste(Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }

    /**
     * 检查文字是否为空
     *
     * @param text
     * @return
     */
    public static boolean IsValueEmpty(String text) {
        if (text != null && text.trim().length() > 0
                && !text.toLowerCase().equals("null")) {
            return false;
        }
        return true;
    }

    public static String numToString(int num) {
        String numString = "";
        switch (num) {
            case 0:
                numString = "零";
                break;
            case 1:
                numString = "一";
                break;
            case 2:
                numString = "二";
                break;
            case 3:
                numString = "三";
                break;
            case 4:
                numString = "四";
                break;
            case 5:
                numString = "五";
                break;
            case 6:
                numString = "六";
                break;
            case 7:
                numString = "七";
                break;
            case 8:
                numString = "八";
                break;
            case 9:
                numString = "九";
                break;
            case 10:
                numString = "十";
                break;
            default:
                break;
        }
        return numString;
    }
}
