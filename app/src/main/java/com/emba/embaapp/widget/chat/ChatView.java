package com.emba.embaapp.widget.chat;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.emba.embaapp.R;
import com.emba.embaapp.utils.LogUtils;
import com.rockerhieu.emojicon.EmojiconEditText;

/**
 * Created by Administrator on 2017-01-04
 *
 * @desc
 */

public class ChatView extends LinearLayout implements View.OnClickListener {

    private FrameLayout chat_background;
    private DropDownListView chat_list;
    private ImageView iv_emoji;
    private EmojiconEditText chat_input_et;
    private View view_send;

    private Context context;

    private boolean mHasInit;

    private onKybdsChangeListener mKybdsListener;

    public ChatView(Context context) {
        this(context, null);
    }

    public ChatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        chat_background = (FrameLayout) findViewById(R.id.chat_background);
        chat_list = (DropDownListView) findViewById(R.id.chat_list);
        iv_emoji = (ImageView) findViewById(R.id.iv_emoji);
        chat_input_et = (EmojiconEditText) findViewById(R.id.chat_input_et);
        view_send = findViewById(R.id.view_send);

        iv_emoji.setOnClickListener(this);
        view_send.setOnClickListener(this);

        chat_input_et.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_emoji:  // 显示表情

                break;
            case R.id.view_send:  // 发送消息

                break;
        }
    }

    /**
     * 隐藏软键盘
     *
     * @param context
     */
    public static void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     *
     * @param context
     */
    public static void showKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            // 获取输入控制管理器服务
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus()
                    .getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        if (!mHasInit) {
//            mHasInit = true;
//            mHeight = b;
//            if (mKybdsListener != null) {
//                mKybdsListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
//            }
//        } else {
//            mKybdsListener.onKeyBoardStateChange(KEYBOARD_STATE_INIT);
//
//            mHeight = mHeight < b ? b : mHeight;
//        }
//        if (mHasInit && mHeight > b) {
//            mHasKeybord = true;
//            if (mKybdsListener != null) {
//                mKybdsListener.onKeyBoardStateChange(KEYBOARD_STATE_SHOW);
//            }
//        }if (mHasInit && mHasKeybord && mHeight == b) {
//            mHasKeybord = false;
//            if (mKybdsListener != null) {
//                mKybdsListener.onKeyBoardStateChange(KEYBOARD_STATE_HIDE);
//            }
//        }
    }

    public interface onKybdsChangeListener{
        void onKeyBoardStateChange(int state);
    }
}
