package com.qijiayiju.adapter;

import java.util.List;

import com.qijiayiju.entity.Data;
import com.qijiayiju.qijiayiju_v2.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DataAdapter extends BaseAdapter{
      List<Data> datas;
      Context context;
	public DataAdapter(List<Data> list,Context arg1) {
		datas=list;
		context=arg1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
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
		final Data data = datas.get(datas.size()-position-1);
		viewHolder.textView_item.setText(data.getDate()+" "+data.getWendu_shinei()+data.getShidu_shinei()+"  "+data.getShidu_shiwai()+data.getWendu_shiwai()+"  "+data.getShidu_shiwai());
		return convertView;
	}
	public static class ViewHolder {
		private TextView textView_item;
	}
}
