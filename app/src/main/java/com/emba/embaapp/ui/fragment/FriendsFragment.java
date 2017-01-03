package com.emba.embaapp.ui.fragment;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseFragment;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc 通讯录
 */

public class FriendsFragment extends MyBaseFragment {
    public static FriendsFragment newInstance() {
        return new FriendsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void initView() {
        settitleCenter(R.string.tab_title_friends);
    }
}
