package com.emba.embaapp.ui.adapter;

/**
 * Created by win7 on 16/7/13.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.emba.embaapp.R;
import com.emba.embaapp.model.UserAccountBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<UserAccountBean> list = null;
    private Activity mActivity;
    private Map<String, UserAccountBean> selectedMap;
    private List<Integer> selectedItems;
    private String groupId;
    private boolean ischeckBoxShow = true;

    public SortAdapter(Activity activity, List<UserAccountBean> list) {
        this.mActivity = activity;
        this.list = list;
        selectedMap = new HashMap<String, UserAccountBean>();
        selectedItems = new ArrayList<Integer>();
    }


    /**
     * 当ListView数据发生变化时,调用此方法来更新ListView
     *
     * @param list
     */
    public void updateListView(List<UserAccountBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Map<String, UserAccountBean> getSelectedMap() {
        return selectedMap;
    }

    public void setSortData(List<UserAccountBean> sourceDateList) {
        selectedItems.clear();
        this.list = sourceDateList;
        this.notifyDataSetChanged();
    }


    public void setSelectedMap(Map<String, UserAccountBean> selectedMap) {
        this.selectedMap = selectedMap;
        this.notifyDataSetChanged();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean ischeckBoxShow() {
        return ischeckBoxShow;
    }

    public void setIscheckBoxShow(boolean ischeckBoxShow) {
        this.ischeckBoxShow = ischeckBoxShow;
    }

    public int getCount() {
        if (list != null) {
            return this.list.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final UserAccountBean mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mActivity).inflate(R.layout.item_sort_listview, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
            viewHolder.tvDepartment = (TextView) view.findViewById(R.id.tv_user_deparment);
            viewHolder.imUserIcon = (SimpleDraweeView) view.findViewById(R.id.im_user_icon);
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.check_box);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        if (ischeckBoxShow) {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkBox.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getName());
        String headImagUrl = this.list.get(position).getHeadimg();
        if (headImagUrl != null && (!headImagUrl.contains("http") || !headImagUrl.contains("https"))) {
            // headImagUrl = AppContext.URL_PROJECT + headImagUrl;
        }
        // ImageLoader.getInstance().displayImage(headImagUrl, viewHolder.imUserIcon, CRMApplication.getUserImageOption());
        viewHolder.tvDepartment.setText(this.list.get(position).getDepartName());
        if (ischeckBoxShow) {
            setCheckedStatus(position, this.list.get(position), viewHolder.checkBox);
        }

        return view;

    }


    final static class ViewHolder {
        SimpleDraweeView imUserIcon;
        TextView tvLetter;
        TextView tvTitle;
        TextView tvDepartment;
        CheckBox checkBox;
    }


    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 提取英文的首字母，非英文字母用#代替。
     *
     * @param str
     * @return
     */
    private String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        // 正则表达式，判断首字母是否是英文字母
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    private void setCheckedStatus(final int position, final UserAccountBean sortModel, final CheckBox checkBox) {
        if (list.get(position).isChecked()) {
            if (!selectedItems.contains(Integer.valueOf(position))) {
                selectedItems.add(new Integer(position));
            }
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItems.contains(Integer.valueOf(position))) {  // 取消
                    selectedItems.remove(Integer.valueOf(position));
                    checkBox.setChecked(false);
                    list.get(position).setChecked(false);
                } else {  // 选中
                    selectedItems.add(new Integer(position));
                    checkBox.setChecked(true);
                    list.get(position).setChecked(true);
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    selectedMap.put(sortModel.getId(), sortModel);
                } else {
                    selectedMap.remove(sortModel.getId());
                }
            }
        });

        if (selectedItems.contains(new Integer(position))) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }
}
