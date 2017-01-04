package com.emba.embaapp.ui.chat;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseActivity;

public class ChatActivity extends MyBaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    private void initToolbar() {
        showBack();
        settitleCenter("09级同学（25）");
    }
}
