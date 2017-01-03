package com.emba.embaapp.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseFragment;
import com.emba.embaapp.model.UserAccountBean;
import com.emba.embaapp.ui.FriendInfoActivity;
import com.emba.embaapp.ui.adapter.SortAdapter;
import com.emba.embaapp.widget.addressbook.CharacterParser;
import com.emba.embaapp.widget.addressbook.PinyinComparator;
import com.emba.embaapp.widget.addressbook.SideBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc 通讯录
 */

public class FriendsFragment extends MyBaseFragment implements ListView.OnItemClickListener {

    @Bind(R.id.listView_friends)
    ListView listView_friends;
    @Bind(R.id.dialog)
    TextView dialog;
    @Bind(R.id.sidrbar)
    SideBar sideBar;

    private SortAdapter adapter;
    private List<UserAccountBean> sourceDateList;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

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
        sideBar.setTextView(dialog);
        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    listView_friends.setSelection(position);
                }
            }
        });

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sourceDateList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            UserAccountBean accountBean = new UserAccountBean();
            accountBean.setId(i + "");
            accountBean.setName("你好");
            accountBean.setSortLetters("nihao" + i);
            sourceDateList.add(accountBean);
        }
        adapter = new SortAdapter(this.getActivity(), sourceDateList);
        adapter.setIscheckBoxShow(false);
        listView_friends.setAdapter(adapter);
        listView_friends.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<UserAccountBean> filledData(List<UserAccountBean> date) {
        List<UserAccountBean> mSortList = new ArrayList<>();

        for (int i = 0; i < date.size(); i++) {
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                date.get(i).setSortLetters(sortString.toUpperCase());
            } else {
                date.get(i).setSortLetters("#");
            }

            mSortList.add(date.get(i));
        }
        return mSortList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FriendInfoActivity.class);
        startActivity(intent);
    }
}
