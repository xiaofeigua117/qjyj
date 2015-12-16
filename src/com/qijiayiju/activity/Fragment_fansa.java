package com.qijiayiju.activity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.qijiayiju.entity.Fan;
import com.qijiayiju.entity.Tongfengentity;
import com.qijiayiju.qijiayiju_v2.MainActivity;
import com.qijiayiju.qijiayiju_v2.R;
import com.qijiayiju.utils.MySeriailPort;
import com.qijiayiju.utils.SerialPort;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment_fansa extends Fragment {
	Typeface typeface;
	private View view;
	public static Fan fan1;
	public static Fan fan2;
	private Button button_fantime;
	private RadioGroup group_buton;
	private static RadioButton button1;
	private static RadioButton button2;
	private CheckBox box_jiaohuan;
	MainActivity activity;
	SerialPort port;
	private TextView textView_fantime;
    private TextView textView_select;
    Spinner spinner;
    public static boolean flag=true;
    TextView textView_status;
    LinearLayout myLinear;
    static RadioButton  button_off;
    RadioButton button_high;
    String []send={};
    SerialPort serialPort;
//    Handler nhandler=new Handler();  
//	Runnable runnable=new Runnable() {  
//	    @Override  
//	    public void run() {  
//	        // TODO Auto-generated method stub  
//	        //要做的事情  
//	    	serialPort.sendData("021000810001025aa5566a", "HEX");
//	        nhandler.postDelayed(this, 5000);  
//	    }  
//	};  
//
	protected static Handler ahandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if(flag){
					button_off.setChecked(true);
				}else{
					MySeriailPort.sendOrders(true, 0);
				}
				break;
			case 2:
				if(!flag){
					button_off.setChecked(true);
				}else{
					MySeriailPort.sendOrders(false, 0);
				}
				break;
			}
			
		}
		
	};
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("oncreat", "creat");
		 //wiperSwitch=new WiperSwitch(getActivity());
		 fan1=new Fan(0,"fan1", false, 0);
		  fan2=new Fan(0,"fan2", false, 0);
		  
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		if(view==null){
            view=inflater.inflate(R.layout.fans_activitya, null);
        }
 //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }  
    
	
		serialPort=MySeriailPort.getSeriail();
	    spinner=(Spinner)view.findViewById(R.id.field_item_spinner_content);
	    textView_select=(TextView)view.findViewById(R.id.select);
	    textView_status=(TextView)view.findViewById(R.id.textView_status);
	    textView_fantime=(TextView)view.findViewById(R.id.textView_fantime);
	    button_off=(RadioButton)view.findViewById(R.id.fan_button1);
	    button1=(RadioButton)view.findViewById(R.id.fan_button2);
	    button2=(RadioButton)view.findViewById(R.id.fan_button3);
	    group_buton=(RadioGroup)view.findViewById(R.id.fan_group);
	    button_fantime=(Button)view.findViewById(R.id.fantime_setting);
	    box_jiaohuan=(CheckBox)view.findViewById(R.id.jiaohuan_button);
	    button_off.setChecked(true);
	    
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item);
		String level[] = getResources().getStringArray(R.array.affair_level);//资源文件
		for (int i = 0; i < level.length; i++) {
		adapter.add(level[i]);
		}
		adapter.setDropDownViewResource(R.layout.drop_down_item);
		spinner.setAdapter(adapter);
		
		//监听spinner   sio
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.i("-------------------spiner",""+arg2+" "+arg3);
				switch (arg2) {
				case 0:
					//界面1  fan1
			        flag=true;
			        switch (fan1.getLevel()) {
					case 0:
						button_off.setChecked(true);
						break;
					case 1:
						button1.setChecked(true);
						break;
					case 2:
						button2.setChecked(true);
						break;
					}
			        
			        break;
				case 1:
		//界面2  fan2
					flag=false;
					 switch (fan2.getLevel()) {
						case 0:
							button_off.setChecked(true);
							break;
						case 1:
							button1.setChecked(true);
							break;
						case 2:
							button2.setChecked(true);
							break;
						}
					break;
				}
			}
       
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		group_buton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.fan_button1:
					MySeriailPort.sendOrders(flag, 0);
					Log.i("-------------------ooooo按钮1",""+flag);
					
					break;
				case R.id.fan_button2:
					MySeriailPort.sendOrders(flag, 1);
					Log.i("-------------------ooooo按钮2",""+flag);
					delay(flag);
					break;
				case R.id.fan_button3:
					MySeriailPort.sendOrders(flag, 2);
					Log.i("-------------------ooooo按钮3",""+flag);
					delay(flag);
					break;
				}
			}
		});
		
		//shezhi
		button_fantime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity()).setTitle("请选择开启时的通风时间").setIcon(
					     android.R.drawable.ic_dialog_info).setPositiveButton("确定设置", null).setSingleChoiceItems(new String[]{"20分钟","30分钟","60分钟"}, 0, new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								switch (arg1) {
								case 0:
									Tongfengentity.setTime_tongfeng(20);
									textView_fantime.setText("20");
									break;
								case 1:
									Tongfengentity.setTime_tongfeng(30);
									textView_fantime.setText("30");
									break;
								case 2:
									Tongfengentity.setTime_tongfeng(60);
									textView_fantime.setText("60");
									break;
							
								}
							}
						}).show();
						
				
			}
		});
       
		//交换模式
		box_jiaohuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){ 
            @Override 
            public void onCheckedChanged(CompoundButton buttonView, 
                    boolean isChecked) { 
                // TODO Auto-generated method stub 
                if(isChecked){ 
                    textView_status.setText("热交换状态:关闭"); 
                    MySeriailPort.sendOrders(false);
                }else{ 
                	textView_status.setText("热交换状态:开启"); 
                	MySeriailPort.sendOrders(true);
                } 
            } 
        }); 
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		  AssetManager mgr=getActivity().getAssets();
	        typeface=Typeface.createFromAsset(mgr, "fonts/time.ttf");
	        	textView_select.setTypeface(typeface);
	}
	
    	public  static void delay(final Boolean boolean1){
    		Timer timer=new Timer();
    	int i=Tongfengentity.time_tongfeng;
    	
    		TimerTask task=new TimerTask() {
    			
    			@Override
    			public void run() {
    				// TODO Auto-generated method stub
    		
    			//	MySeriailPort.sendOrders(boolean1,0);
    		
    				
    				Message msg = new Message();
    				msg.obj=boolean1;
    				if(boolean1){
    				msg.what=1;
    				Log.i("booooooooo111", ""+boolean1);
    				}else{
    				msg.what = 2;	
    				Log.i("booooooooo22", ""+boolean1);
    				}
					
					//msg.obj = data;
					ahandler.sendMessage(msg);
    			}
    		};
			timer.schedule(task, i*1000*60);
    	}
    	@Override
    	public void onResume() {
    		// TODO Auto-generated method stub
    		super.onResume();
    		Log.i("resume", "resume---------------------------");
    		if(fan1.getLevel()==0){
    			button_off.setChecked(true);
    		}
    	}
    	public static void onResume2(){
    		if(fan1.getLevel()==0){
    			button_off.setChecked(true);
    		}
    		if(fan1.getLevel()==1){
    			button1.setChecked(true);
    		}
    		if(fan1.getLevel()==2){
    			button2.setChecked(true);
    		}
    	}
    }



	
    

