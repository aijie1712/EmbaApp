package com.emba.embaapp.ui.fragment;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseFragment;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc 会话列表
 */

public class ConversationsFragment extends MyBaseFragment {
    public static ConversationsFragment newInstance() {
        return new ConversationsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_conversations;
    }

    @Override
    protected void initView() {
        settitleCenter(R.string.tab_title_conversations);
    }
}
