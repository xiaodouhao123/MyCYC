package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mycyc.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 徐达
 * on 2016/9/1 on 15:40.
 * 作用:
 */
public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<String> groupTitle;
    //子项是一个map，key是group的id，每一个group对应一个ChildItem的list
    private Map<Integer, List<String>> childMap;
    public MyBaseExpandableListAdapter(Context context, List<String> groupTitle, Map<Integer, List<String>> childMap) {
        this.mContext = context;
        this.groupTitle = groupTitle;
        this.childMap = childMap;
    }

    @Override
    public int getGroupCount() {
        return groupTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return  childMap.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childMap.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_filter_type_view, null);
            groupHolder = new GroupHolder();
            groupHolder.groupImg = (ImageView) convertView.findViewById(R.id.id_img);
            groupHolder.groupText = (TextView) convertView.findViewById(R.id.id_txt);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        if (isExpanded) {
            groupHolder.groupImg.setBackgroundResource(R.drawable.filter_list_selected);
        }else {
            groupHolder.groupImg.setBackgroundResource(R.drawable.filter_list_unselected);
        }
        groupHolder.groupText.setText(groupTitle.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.childitem, null);
            childHolder = new ChildHolder();
            //childHolder.childImg = (ImageView) convertView.findViewById(R.id.img_child);
            childHolder.childText = (TextView) convertView.findViewById(R.id.tv_child);
            convertView.setTag(childHolder);
        }else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        //childHolder.childImg.setBackgroundResource(childMap.get(groupPosition).get(childPosition).getMarkerImgId());
        childHolder.childText.setText(childMap.get(groupPosition).get(childPosition));
        return convertView;
    }
    private class GroupHolder
    {
        ImageView groupImg;
        TextView groupText;
    }
    private class ChildHolder
    {

        TextView childText;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
