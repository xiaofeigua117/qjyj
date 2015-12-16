package com.qijiayiju.qijiayiju_v2;


import java.util.List;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.adapter.Myadapter;
import com.qijiayiju.entity.Data;
import com.qijiayiju.utils.CreatExcels;
import com.qijiayiju.utils.DBHelper;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_newdetail extends Fragment {
	private ListView listView_palce;
	private ArrayAdapter<String> adapter;
	private Myadapter myadapter;
    private String [] rooms;
    DbUtils dbUtils;
    private Button button_detail2;
    TextView textView_detail_wendu;
    TextView textView_detail_shidu;
	private Typeface tf;
	private Typeface tf2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fragment_new_detail, container,false);
		listView_palce=(ListView)view.findViewById(R.id.listView1);
		button_detail2=(Button)view.findViewById(R.id.button_detail2);
		textView_detail_wendu=(TextView)view.findViewById(R.id.textView_detail_wendu);
		textView_detail_shidu=(TextView)view.findViewById(R.id.textView_detail_shidu);
		dbUtils=DBHelper.getUtils();
		//			Cursor cur=dbUtils.execQuery("select * from Data where id=(select max(id) from Data)");
//			int nameColumn = cur.getColumnIndex("id");
//			String name = cur.getString(nameColumn);
		//String string = cur.getString(0);
		//Data data2 =dbUtils.findFirst(Selector.from(Data.class).where("id","=", "(select max(id) from Data)"));  
//			List<Data> list = DBHelper.getUtils().findAll(Data.class);//通过类型查找
//			Data data=list.get(list.size()-1);
		textView_detail_shidu.setText((float)(Math.round(MainActivity.shiwaishidu*10))/10+"Rh%");
		textView_detail_wendu.setText((float)(Math.round(MainActivity.shiwaiwendu*10))/10+"℃");

		
	
		rooms=getResources().getStringArray(R.array.room);
		adapter=new ArrayAdapter<String>(getActivity(), R.layout.listview_item, rooms);
		myadapter=new Myadapter(getActivity(), R.layout.listview_item, rooms);
		listView_palce.setAdapter(myadapter);
	//	listView_palce.setSelection(0);
		listView_palce.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				myadapter.changeSelected(arg2);//刷新
				//arg1.setBackgroundResource(R.drawable.xx1);
				if(arg2==1){
					textView_detail_shidu.setText((float)(Math.round(MainActivity.shineishidu*10))/10+"Rh%");
					textView_detail_wendu.setText((float)(Math.round(MainActivity.shiwen*10))/10+"℃");
				}if(arg2==0){
					textView_detail_shidu.setText((float)(Math.round(MainActivity.shineishidu*10))/10+"Rh%");
					textView_detail_wendu.setText((float)(Math.round(MainActivity.shiwaiwendu*10))/10+"℃");
				}
				
				
			}
		});
listView_palce.setOnItemSelectedListener(new OnItemSelectedListener() {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		myadapter.changeSelected(arg2);//刷新
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
});
		button_detail2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),RecordActivity.class);
				startActivity(intent);
			}
		});
		
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		  AssetManager mgr=getActivity().getAssets();
	        tf=Typeface.createFromAsset(mgr, "fonts/StencilStd.otf");
	        tf2=Typeface.createFromAsset(mgr, "fonts/time.ttf");
	        	textView_detail_wendu.setTypeface(tf);
	        	textView_detail_shidu.setTypeface(tf);
	        
	}
	public static void setData(){
		
	}

}
