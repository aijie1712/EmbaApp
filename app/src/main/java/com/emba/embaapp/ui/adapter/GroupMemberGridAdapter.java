package com.emba.embaapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emba.embaapp.R;

import java.util.List;

/**
 * Created by Administrator on 2017-01-03
 *
 * @desc
 */

public class GroupMemberGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> datas;

    public GroupMemberGridAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_members, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_member_name.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        public TextView tv_member_name;

        public ViewHolder(View rootView) {
            tv_member_name = (TextView) rootView.findViewById(R.id.tv_member_name);
        }
    }
}
