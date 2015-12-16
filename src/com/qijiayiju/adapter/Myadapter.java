package com.qijiayiju.adapter;

import com.qijiayiju.qijiayiju_v2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Myadapter extends ArrayAdapter<String>{
Context context;
String[] rooms;
	 public Myadapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.context=context;
         rooms=objects;
		// TODO Auto-generated constructor stub
	}



	int mSelect = 0;   //选中项

	 public void changeSelected(int positon){ //刷新方法
	     if(positon != mSelect){
	      mSelect = positon;
	     notifyDataSetChanged();
	     }
	    }



	public View getView(int position, View convertView, ViewGroup parent) {
//	     if(convertView==null){
	        LayoutInflater factory = LayoutInflater.from(context);
	        View v = (View) factory.inflate(R.layout.listview_item_room, null);
	        TextView tv = (TextView) v.findViewById(R.id.Text_detail);
	       /// tv.setText("test");
	        tv.setText(rooms[position]);
//	     }
	        if(mSelect==position){    
	         v.setBackgroundResource(R.drawable.button_press);  //选中项背景
	        }else{
	         v.setBackgroundResource(R.drawable.button_normal);  //其他项背景
	        }
	        
	        return v;
	    }

	}