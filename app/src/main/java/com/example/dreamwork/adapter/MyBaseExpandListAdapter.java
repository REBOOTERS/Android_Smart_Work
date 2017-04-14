package com.example.dreamwork.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by co-mall on 2016/5/16.
 */
public class MyBaseExpandListAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private List<String> groups = new ArrayList<>();
	private List<List<Class>> items = new ArrayList<>();

	public MyBaseExpandListAdapter(Context mContext, List<String> groups, List<List<Class>> items) {
		this.mContext = mContext;
		this.groups = groups;
		this.items = items;
	}

	@Override
	public int getGroupCount() {
		return groups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return items.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return items.get(groupPosition).get(childPosition);
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
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		GroupHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group_expandlistview, parent, false);
			holder = new GroupHolder();
			holder.groupName = V.f(convertView, R.id.groupname);
			holder.statusImg = V.f(convertView, R.id.statusImage);
			convertView.setTag(holder);
		} else {
			holder = (GroupHolder) convertView.getTag();
		}

		if (isExpanded) {
			holder.statusImg.setImageResource(R.drawable.zhankai);
		} else {
			holder.statusImg.setImageResource(R.drawable.shousuo);
		}

		holder.groupName.setText(groups.get(groupPosition));

		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		GroupItemHolder itemHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group_item_expandlistview, parent, false);
			itemHolder = new GroupItemHolder();
			itemHolder.groupItemName = V.f(convertView, R.id.groupitem);
			convertView.setTag(itemHolder);
		} else {
			itemHolder = (GroupItemHolder) convertView.getTag();
		}

		itemHolder.groupItemName
				.setText(childPosition + 1 + ". " + items.get(groupPosition).get(childPosition).getSimpleName());
		return convertView;
	}

	class GroupHolder {
		TextView groupName;
		ImageView statusImg;
	}

	class GroupItemHolder {
		TextView groupItemName;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
