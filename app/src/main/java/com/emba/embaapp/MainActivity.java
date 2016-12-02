package com.emba.embaapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.emba.embaapp.ui.adapter.ViewPagerAdapter;
import com.emba.embaapp.ui.fragment.HomeFragment;
import com.emba.embaapp.widget.ScrollControlViewPager;
import com.emba.embaapp.widget.tabview.TabItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ScrollControlViewPager mViewPager;
    @Bind(R.id.actionbar_info)
    TabItem actionbarInfo;
    @Bind(R.id.actionbar_publish)
    TabItem actionbarPublish;

    private int mIndex = 0;
    private List<TabItem> mTabItems;
    private int[] mTabItemsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    protected void initView() {
        initBottomView();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance(AppConstant.server_require));
        fragmentList.add(HomeFragment.newInstance(AppConstant.message_list));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.setScroll(false);
        mViewPager.addOnPageChangeListener(mViewPagerChangeListener);
        mViewPager.setCurrentItem(0);
    }

    private void initBottomView() {
        mTabItemsId = new int[]{R.id.actionbar_info, R.id.actionbar_publish};
        mTabItems = new ArrayList<>();
        for (int i = 0; i < mTabItemsId.length; i++) {
            TabItem tabItem = (TabItem) findViewById(mTabItemsId[i]);
            tabItem.setOnClickListener(mTabItemClickListener);
            mTabItems.add(tabItem);
        }
    }

    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (positionOffset > 0) {
                mTabItems.get(position).setTabAlpha(1 - positionOffset);
                mTabItems.get(position + 1).setTabAlpha(positionOffset);
            } else {
                mTabItems.get(position).setTabAlpha(1 - positionOffset);
            }
        }

        @Override
        public void onPageSelected(int position) {
            mIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private View.OnClickListener mTabItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = Integer.valueOf((String) v.getTag());
            if (mViewPager.getCurrentItem() == position) {
                return;
            }
            for (TabItem tabItem : mTabItems) {
                tabItem.setTabAlpha(0);
            }
            mTabItems.get(position).setTabAlpha(1);
            mViewPager.setCurrentItem(position, false);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
