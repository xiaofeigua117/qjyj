//package com.qijiayiju.activity;
//
//import java.util.Date;
//
//import com.qijiayiju.entity.Fan;
//import com.qijiayiju.qijiayiju_v2.R;
//import com.qijiayiju.utils.SerialPort;
//import com.qijiayiju.view.WiperSwitch;
//import com.qijiayiju.view.WiperSwitch.OnChangedListener;
//
//import android.content.res.AssetManager;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemSelectedListener;
//import android.widget.ArrayAdapter;
//import android.widget.CheckedTextView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.RadioGroup.OnCheckedChangeListener;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class Fragment_fans extends Fragment {
//	Typeface typeface;
//	private View view;
//	public Fan fan1;
//	public Fan fan2;
//	RadioGroup group_buton;
//	RadioButton button1;
//	RadioButton button2;
//	RadioButton button3;
//    TextView textView_select;
//    CheckedTextView checkedTextView;
//    Spinner spinner;
//    public static boolean flag=true;
//    //public static int fan1.getLevel()=1;
//    WiperSwitch wiperSwitch;
//    TextView textView_xianshi;
//    LinearLayout myLinear;
//   
//    SerialPort serialPort;
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
//    
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		Log.i("oncreat", "creat");
//		 wiperSwitch=new WiperSwitch(getActivity());
//		 fan1=new Fan(0,"fan1", false, 1);
//		  fan2=new Fan(0,"fan2", false, 1);
//		  
////		 myLinear=new LinearLayout(getActivity());
////		//LinearLayout.LayoutParams.WRAP_CONTENT可以设定为你需要的值
////		LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
////						LinearLayout.LayoutParams.MATCH_PARENT,
////						LinearLayout.LayoutParams.MATCH_PARENT
////				);
////						myLinear.addView(wiperSwitch,params1);
//	}
//
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		
//		if(view==null){
//            view=inflater.inflate(R.layout.fans_activity, null);
//        }
// //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        ViewGroup parent = (ViewGroup) view.getParent();
//        if (parent != null) {
//            parent.removeView(view);
//        }  
//		
//		
//		 //view=inflater.inflate(R.layout.fans_activity, container,false);
//		spinner=(Spinner)view.findViewById(R.id.field_item_spinner_content);
//		textView_select=(TextView)view.findViewById(R.id.select);
//		myLinear=(LinearLayout)view.findViewById(R.id.linear_on);
//		textView_xianshi=(TextView)view.findViewById(R.id.xianshi_fan);
//		group_buton=(RadioGroup)view.findViewById(R.id.fan_group);
//		button1=(RadioButton)view.findViewById(R.id.fan_button1);
//		button2=(RadioButton)view.findViewById(R.id.fan_button2);
//		button3=(RadioButton)view.findViewById(R.id.fan_button3);
//		wiperSwitch=(WiperSwitch)view.findViewById(R.id.wiperSwitch1);
//		wiperSwitch.setX(20);
//		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.simple_spinner_item);
//		String level[] = getResources().getStringArray(R.array.affair_level);//资源文件
//		for (int i = 0; i < level.length; i++) {
//		adapter.add(level[i]);
//		}
//		adapter.setDropDownViewResource(R.layout.simple_spinner_item);
//		spinner.setAdapter(adapter);
//		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//				
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				Log.i("int arg     long arg", "arg2===="+arg2+"arg3==="+arg3);
//				if(arg2==0){
//					flag=true;
//					
//					wiperSwitch.setChecked(false);
//					switch (fan1.getLevel()) {
//					case 1:
//						group_buton.check(button1.getId());
//						break;
//
//					case 2:
//						group_buton.check(button2.getId());
//						break;
//					case 3:
//						group_buton.check(button3.getId());
//						break;
//					}
//					
//					
//					
//				}if(arg2==1){
//					flag=false;
//					wiperSwitch.setChecked(false);
//					switch (fan2.getLevel()) {
//					case 1:
//						group_buton.check(button1.getId());
//						break;
//
//					case 2:
//						group_buton.check(button2.getId());
//						break;
//					case 3:
//						group_buton.check(button3.getId());
//						break;
//					}
//				}
//				wiperSwitch.setOnChangedListener(new OnChangedListener() {
//					
//					@Override
//					public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
//						// TODO Auto-generated method stub
//						
//					if(checkState==true){
//						
//						fan1.setStatus(true);
//						 serialPort=new com.qijiayiju.utils.SerialPort("S0", 115200, 8, 1, 'n');
//						 serialPort.isOpen=true;
//						// serialPort.receiveData("HEX");
//						new ReceiveThread().start();
//						nhandler.postDelayed(runnable, 8000);
//						switch (fan1.getLevel()) {
//						case 1:
//							serialPort.sendData("021000810002045aa50100362c", "HEX");
//							
//							break;
//						case 2:
//							serialPort.sendData("021000810002045aa5020036DC", "HEX");
//							break;
//							case 3:
//								serialPort.sendData("021000810002045aa50400357c", "HEX");
//								break;
//					
//						}
//						
//						
//					}else{
//						fan1.setStatus(false);
//						//serialPort.closeSerial();
//						serialPort.sendData("021000810002045aa5000037bc", "HEX");
//						nhandler.removeCallbacks(runnable); 
//						
//					}
//					if(fan1.isStatus()==false){
//						textView_xianshi.setText("风机关闭");
//					}if(fan1.isStatus()==true){
//						textView_xianshi.setText("风机打开");
//						}
//					}
//				});
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		group_buton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			@Override
//			public void onCheckedChanged(RadioGroup arg0, int id) {
//				// TODO Auto-generated method stub
//				if(flag){
//					if(fan1.isStatus()){
//						
//						switch (id) {
//						case R.id.fan_button1:
//							
//							fan1.setLevel(1);
//							
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000810002045aa50100362c", "HEX");
//								serialPort.sendData("021000820001020100ad12", "HEX");
//								
//								
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							break;
//
//						case R.id.fan_button2:
//							fan1.setLevel(2);
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000820001020200ad12", "HEX");
//								serialPort.sendData("021000820001020300ac72", "HEX");
//								//serialPort.sendData("021000810002045aa5020036DC", "HEX");
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							break;
//						case R.id.fan_button3:
//							fan1.setLevel(3);
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000810002045aa50400357c", "HEX");
//								serialPort.sendData("021000810002045aa502103670", "HEX");
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							
//							break;
//						}
//						
//					}
//				}else{
//					if(fan2.isStatus()){
//						switch (id) {
//						case R.id.fan_button1:
//							
//							fan2.setLevel(1);
//							
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000810002045aa50100362c", "HEX");
//								serialPort.sendData("021000820001020100ad12", "HEX");
//								
//								
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							break;
//
//						case R.id.fan_button2:
//							fan2.setLevel(2);
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000820001020200ad12", "HEX");
//								serialPort.sendData("021000820001020300ac72", "HEX");
//								//serialPort.sendData("021000810002045aa5020036DC", "HEX");
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							break;
//						case R.id.fan_button3:
//							fan2.setLevel(3);
//							if(fan1.isStatus()==true){
//								//serialPort.sendData("021000810002045aa50400357c", "HEX");
//								serialPort.sendData("021000810002045aa502103670", "HEX");
//							}
//							//serialPort.sendData("021000810002045aa5000037bc", "HEX");
//							
//							
//							break;
//						}
//					}
//					
//				}
//				
//				
//				textView_xianshi.append("档位为"+fan1.getLevel());
//			}
//		});
//		
//		
//		
//		WiperSwitch wiperSwitch = (WiperSwitch)view.findViewById(R.id.wiperSwitch1);  
//        
//        //设置初始状态为false  
//        wiperSwitch.setChecked(false);  
//          
//        //设置监听  
//        
//        Log.i("oncreatview", "oncreatview");
//		return view;
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onActivityCreated(savedInstanceState);
//		
//		  AssetManager mgr=getActivity().getAssets();
//	        typeface=Typeface.createFromAsset(mgr, "fonts/time.ttf");
//	        
//	        	textView_select.setTypeface(typeface);
//	}
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		super.onDestroy();
//		Log.i("ondestory", "destory");
//	}
//	class ReceiveThread extends Thread {
//		public void run() {
//
//			while (serialPort.isOpen) {
//				
//					String type ="HEX";
//					String data = serialPort.receiveData(type);
//					if (data != null) {
//						Message msg = new Message();
//						msg.what = 1;
//						msg.obj = data;
//						System.out.println(data + "<<<<<<<<==========data");
//						mHandler.sendMessage(msg);
//					}
//				
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	private Handler mHandler=new Handler(){
//    	public void handleMessage(android.os.Message msg) {
//    		switch (msg.what) {
//			case 1:
//				Date date = new Date();
//				//textView_xianshi.append("[" + date.getMinutes() + ":"
//				//		+ date.getSeconds() + "] " + (CharSequence) msg.obj);
//				
//				CharSequence data=(CharSequence)msg.obj;
//				if(data.charAt(0)=='0'){
//					Toast.makeText(getActivity(), msg.obj.toString(), 2000).show();
//				}
//				break;
////				case 2:
////					serialPort.sendData("021000810002045aa50100362c", "HEX");
////					
////               break;
//			
//			}
//    	}
//    	;
//    };
//
//
//	@Override
//	public void onPause() {
//		// TODO Auto-generated method stub
//		super.onPause();
//		Log.i("pause", "pause");
//	}
//
//
//	@Override
//	public void onResume() {
//		// TODO Auto-generated method stub
//		super.onResume();
//		Log.i("resume", "resume");
//	}
//
//
//	@Override
//	public void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//		//wiperSwitch.setChecked(flag);
//		Log.i("onstart", "start");
//	}
//
//
//	@Override
//	public void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		Log.i("stop", "stop");
//	}
//    
//    
// }
