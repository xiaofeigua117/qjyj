package com.qijiayiju.qijiayiju_v2;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.adapter.DataAdapter;
import com.qijiayiju.diyDialog.Mydialog;
import com.qijiayiju.entity.Data;
import com.qijiayiju.utils.DBHelper;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class RecordActivity extends Activity {
    
	private ListView listView;
     List<Data> Alllist;
     DataAdapter adapter;
     Button button_search;
     Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_record);
		Alllist=new ArrayList<Data>();
		context=getApplicationContext();
        button_search=(Button)findViewById(R.id.search_button);
       
		try {
			Alllist=DBHelper.getUtils().findAll(Data.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adapter=new DataAdapter(Alllist, context);
		listView=(ListView)findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Data mess=(Data) adapter.getItem(arg2);
//				   new AlertDialog.Builder(RecordActivity.this)
//		            .setIcon(R.drawable.logo)
//		            .setTitle("详情")
//		            .setMessage(mess.getDate()+mess.getTime()+mess.getShidu_shinei())
				Mydialog dialog=new Mydialog(RecordActivity.this,R.style.MyDialog);
			    View view=dialog.getCustomView();
			   TextView dialog_data=(TextView)view.findViewById(R.id.dialog_data);
			   dialog_data.setText(mess.getDate()+mess.getCo2()+"223456543");
		            dialog.show();
			}
		});
		 button_search.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					try {
						Toast.makeText(context, "更新数据", Toast.LENGTH_LONG).show();
						//List<Data>  list= DBHelper.getUtils().findAll(Selector.from(Data.class).where("date" ,"=", "1970-01-02"));
					//List<Data> list=new ArrayList<Data>();
					  //  Alllist.clear();
					    List<Data> list = DBHelper.getUtils().findAll(Data.class);//通过类型查找
						//list=DBHelper.getUtils().findAll(Selector.from(Data.class) .where("id","<",54));
					//	DBHelper.getUtils().f
						Alllist.addAll(list);
						adapter.notifyDataSetChanged();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//adapter.notifyDataSetInvalidated();
						
					}
				}
			});
	}
}
