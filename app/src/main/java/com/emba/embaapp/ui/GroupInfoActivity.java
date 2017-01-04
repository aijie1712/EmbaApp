package com.emba.embaapp.ui;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.emba.embaapp.R;
import com.emba.embaapp.base.MyBaseActivity;
import com.emba.embaapp.ui.adapter.GroupAllMemberAdapter;
import com.emba.embaapp.ui.adapter.GroupMemberGridAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorInflater;
import com.nineoldandroids.animation.ObjectAnimator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 群组信息页面
 */
public class GroupInfoActivity extends MyBaseActivity {
    @Bind(R.id.group_members)
    GridView group_members;
    @Bind(R.id.iv_more)
    ImageView iv_more;
    @Bind(R.id.listView_all_members)
    ListView listView_all_members;

    private List<String> groupMembers;
    private GroupMemberGridAdapter groupMemberGridAdapter;

    private GroupAllMemberAdapter allMemberAdapter;

    ObjectAnimator rotateAnim;

    // 是否显示所有成员列表
    private boolean showAllMembers = false;
    private float fromDegree =0;
    private float toDegree = 180;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_info;
    }

    @Override
    protected void initView() {
        initToolbar();

        initGridView();

        initListView();
    }

    private void initToolbar() {
        showBack();
        settitleCenter(getString(R.string.title_group_members, 25));

        rotateAnim = ObjectAnimator.ofFloat(iv_more, "rotation", fromDegree, toDegree);
        rotateAnim.setDuration(300);
    }

    private void initGridView() {
        groupMembers = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            groupMembers.add("北京中云微讯");
        }
        groupMemberGridAdapter = new GroupMemberGridAdapter(this, groupMembers);
        group_members.setAdapter(groupMemberGridAdapter);
        group_members.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initListView(){
        allMemberAdapter = new GroupAllMemberAdapter(this,groupMembers);
        listView_all_members.setAdapter(allMemberAdapter);
        listView_all_members.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_more:  // 显示更多
                if(showAllMembers){
                    fromDegree = 180;
                    toDegree = 360;
                    gone(listView_all_members);
                }else{
                    fromDegree = 0;
                    toDegree = 180;
                    visible(listView_all_members);
                }
                showAllMembers = !showAllMembers;
                rotateAnim.setFloatValues(fromDegree,toDegree);
                rotateAnim.start();
                break;
        }
    }
}
