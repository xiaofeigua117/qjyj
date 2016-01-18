package com.qijiayiju.adapter;

import java.util.List;

import com.qijiayiju.adapter.DataAdapter.ViewHolder;
import com.qijiayiju.entity.Data;
import com.qijiayiju.qijiayiju_v2.Qijia;
import com.qijiayiju.qijiayiju_v2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAboutAdapter extends BaseAdapter {
	Context context;
	List<Qijia> list;

	public MyAboutAdapter(Context context, List<Qijia> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_item, parent, false);
			viewHolder.textView_item = (TextView) convertView
					.findViewById(R.id.Text_detail);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final Qijia data = list.get(position);
		viewHolder.textView_item.setText(data.getName());

		return convertView;
	}

	public static class ViewHolder {
		private TextView textView_item;
	}
}
