package com.emba.embaapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.emba.embaapp.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc
 */

public abstract class MyBaseActivity extends AppCompatActivity{
    TextView title_back,tv_title_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
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

    protected void showBack(){
        title_back = (TextView) findViewById(R.id.title_back);
        if (title_back != null) {
            visible(title_back);
            title_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    protected void settitleCenter(String title){
        tv_title_center = (TextView) findViewById(R.id.tv_title_center);
        tv_title_center.setText(title);
    }

    protected void settitleCenter(int title){
        tv_title_center = (TextView) findViewById(R.id.tv_title_center);
        tv_title_center.setText(getString(title));
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftware(View view) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isSoftActive = imm.isActive();
        if (isSoftActive) {
            imm.hideSoftInputFromWindow(
                    view.getApplicationWindowToken(), 0); // 强制隐藏键盘
        }
    }
}
