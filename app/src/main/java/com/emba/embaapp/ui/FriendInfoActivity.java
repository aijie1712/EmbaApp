package com.emba.embaapp.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseActivity;
import com.emba.embaapp.ui.adapter.GroupMemberGridAdapter;
import com.emba.embaapp.ui.adapter.UserinfoCityAdapter;
import com.emba.embaapp.ui.chat.ChatActivity;
import com.emba.embaapp.utils.DisplayUtils;
import com.emba.embaapp.utils.UiUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class FriendInfoActivity extends MyBaseActivity {
    @Bind(R.id.sdf_user_icon)
    SimpleDraweeView sdf_user_icon;
    @Bind(R.id.user_name)
    TextView user_name;
    @Bind(R.id.tv_user_gender)
    TextView tv_user_gender;
    @Bind(R.id.tv_user_grade)
    TextView tv_user_grade;
    @Bind(R.id.tv_user_city)
    TextView tv_user_city;
    @Bind(R.id.user_cities)
    GridView user_cities;
    @Bind(R.id.ll_company)
    LinearLayout ll_company;

    private UserinfoCityAdapter cityAdapter;
    private List<String> cityList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info;
    }

    @Override
    protected void initView() {
        initToolbar();
        for (int i = 0; i < 3; i++) {
            addCompany();
        }

        initCityList();
    }

    private void initCityList() {
        cityList = new ArrayList<>();
        cityList.add("上海");
        cityList.add("广州");
        cityList.add("北京");
        cityAdapter = new UserinfoCityAdapter(this, cityList);
        user_cities.setAdapter(cityAdapter);
        // getTotalHeightOfGridView(user_cities);
    }

    private void initToolbar() {
        showBack();
        settitleCenter(R.string.title_user_info);
    }

    private void addCompany() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.item_company, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtils.dp2px(this, 45)));
        ll_company.addView(rootView);
    }

    @OnClick({R.id.btn_send_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send_message:  // 发送消息
                Intent intent = new Intent(FriendInfoActivity.this, ChatActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 动态获取gridView的高度
     *
     * @param gridView
     */
    private void getTotalHeightOfGridView(GridView gridView) {
        ListAdapter adapter = gridView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {

            View view = adapter.getView(i, null, gridView);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            if (i % 2 == 0) {
                totalHeight += view.getMeasuredHeight();
            }
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
        gridView.requestLayout();
    }
}
