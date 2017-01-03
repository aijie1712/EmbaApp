package com.emba.embaapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.emba.embaapp.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc
 */

public abstract class MyBaseFragment extends Fragment{
    TextView title_back,tv_title_center;

    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        activity = getActivity();
        this.inflater = inflater;
        return parentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initData(){}

    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected void settitleCenter(String title){
        tv_title_center = (TextView) parentView.findViewById(R.id.tv_title_center);
        tv_title_center.setText(title);
    }

    protected void settitleCenter(int title){
        tv_title_center = (TextView) parentView.findViewById(R.id.tv_title_center);
        tv_title_center.setText(getString(title));
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftware(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isSoftActive = imm.isActive();
        if (isSoftActive) {
            imm.hideSoftInputFromWindow(
                    view.getApplicationWindowToken(), 0); // 强制隐藏键盘
        }
    }
}
