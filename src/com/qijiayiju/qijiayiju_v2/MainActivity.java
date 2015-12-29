package com.qijiayiju.qijiayiju_v2;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.activity.Fragment_fansa;
import com.qijiayiju.entity.Data;
import com.qijiayiju.utils.DBHelper;
import com.qijiayiju.utils.MySeriailPort;
import com.qijiayiju.utils.MyStrinUtils;
import com.qijiayiju.utils.SerialPort;

import android.R.integer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.Choreographer.FrameCallback;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
	String sub;
	private static int i=0;
	private TextView textView_value;
    private  RadioGroup group_main;
    Fragment mcontent;
   public static float shiwen=0;
   public static float shineishidu=0;
   public static float shiwaiwendu=0;
   public static float shiwaishidu=0;
   public static float shineico2=0;
   public static float woshiwendu1=0;
   public static float woshishidu1=0;
   
    static String comm1="";
   Fragmnet_main fragmnet_main;
   Fragment_fansa fans;
   Fragment_aoubtus aoubtus;
   Fragment_details details;
    public static String wendu_value;
    FragmentManager fragmentManager;
    private RadioButton button_main;
    SerialPort serialPort;
    
    private Handler mHandler=new Handler(){
    	private DbUtils dbUtils;
		private String sub2;
		private String sub3;
		private String sub4;
		private String sub5;
		private String sub6;
		private String sub7;

		public void handleMessage(android.os.Message msg) {
    		switch (msg.what) {
			case 1:
				Date date = new Date();
				//textView_xianshi.append("[" + date.getMinutes() + ":"
				//		+ date.getSeconds() + "] " + (CharSequence) msg.obj);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = format.format(date);
				CharSequence data=(CharSequence)msg.obj;
				if(data.charAt(4)=='3'){
//					Toast.makeText(MainActivity.this, msg.obj.toString(), 2000).show();
					//	DbUtils dbUtils=DBHelper.getUtils();
					String mystring=msg.obj.toString();
					StringBuffer stringBuffer=new StringBuffer(mystring);
					//
						sub=(String) stringBuffer.subSequence(9, 14);
						sub2=(String) stringBuffer.subSequence(15, 20);
						sub3=(String) stringBuffer.substring(21, 26);
						sub4=(String) stringBuffer.substring(27, 32);
						sub5=(String)stringBuffer.subSequence(33, 38);
						sub6=(String)stringBuffer.subSequence(39, 44);
						sub7=(String)stringBuffer.subSequence(45, 50);
//						
//						float ad=MyStrinUtils.fiveToint(sub);
//						float ad2=MyStrinUtils.fiveToint(sub2);
//						float ad3=MyStrinUtils.fiveToint(sub3);
//						float ad4=MyStrinUtils.fiveToint(sub4);
//						float ad5=MyStrinUtils.fiveToint(sub5);
//						float ad6=MyStrinUtils.fiveToint(sub6);
//						float ad7=MyStrinUtils.fiveToint(sub7);
//						float mA=(float)(ad/2479.0);
//						float mA2=(float)(ad2/2479.0);
//						float mA3=(float)(ad3/2479.0);
//						float mA4=(float)(ad4/2479.0);
						
						String s1=sub.replaceAll(" ", "");
						String s2=sub2.replaceAll(" ", "");
						String s3=sub3.replaceAll(" ", "");
						String s4=sub4.replaceAll(" ", "");
						String s5=sub5.replaceAll(" ", "");
						String s6=sub6.replaceAll(" ", "");
						String s7=sub7.replaceAll(" ", "");
						
						int a1=Integer.parseInt(s1, 16);
						int a2=Integer.parseInt(s2, 16);
						int a3=Integer.parseInt(s3, 16);
						int a4=Integer.parseInt(s4, 16);
						int a5=Integer.parseInt(s5, 16);
						int a6=Integer.parseInt(s6, 16);
						int a7=Integer.parseInt(s7, 16);
						
						//shiwen=(float) ((a1-9687.0)/38748*50);
						shiwen=((float) (a1*22000/65535)*3/2480-4)*50/16;
						shineishidu=((float) (a2*22000/65535)*3/2480-4)*100/16;
						shiwaiwendu=((float) (a3*22000/65535)*3/2480-(16/4+4))*80/16;
						//shiwaiwendu=((float)(a1-6400)/512);
						//shiwaiwendu=(float) ((a1-9687.0)/38748*50);				
							shiwaishidu=((float) (a4*22000/65535)*3/2480-4)*100/16;
							shineico2=((float) (a5*22000/65535)*3/2480-4)*2000/16;
							woshiwendu1=((float) (a6*22000/65535)*3/2480-4)*50/16;
							woshishidu1=((float) (a7*22000/65535)*3/2480-4)*100/16;
//						 shiwen=(mA-4)*50/16;
//						 shineishidu=(mA2-4)*100/16;
//					shiwaiwendu=(mA3-4)*120/16;
//						 shiwaishidu=(mA4-4)*100/16;
						
						/// Toast.makeText(MainActivity.this,ad+"", 2000).show();
						 Toast.makeText(MainActivity.this,shiwaiwendu+""+shiwaishidu, 2000).show();
						//fragmnet_main.textView_shiwen.setText(shiwen);A
					
					
					if(i==600){
						
					}
					Data entity=new Data();
					entity.setDate(str);
					entity.setShidu_shiwai(shiwaishidu+"");
					entity.setWendu_shiwai(shiwaiwendu+"");
					entity.setShidu_shinei(shineishidu+"");
					entity.setWendu_shinei(shiwen+"");
					entity.setCo2(shineico2+"");
				    try {
				    	
					dbUtils=	DBHelper.getUtils();
					dbUtils.save(entity);
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
				break;
//				case 2:
//					serialPort.sendData("021000810002045aa50100362c", "HEX");
//					
//               break;
			
			}
    	}
    	;
    };

    
    
    
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		  DBHelper.initUtils(MainActivity.this);
	    MySeriailPort.initSeriailPort(MainActivity.this);
	    
   serialPort=  MySeriailPort.getSeriail();
   new ReceiveThread().start();
  
	this.getActionBar().hide();
			initView();
		   
			
			
			group_main.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedID) {
					// TODO Auto-generated method stub
					
					switch (checkedID) {
					case R.id.rb_main_menu1:
						switchContent(getVisibleFragment(), fragmnet_main);
//						FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
//						//Replace whatever is in the fragment_container view with thisfragment,
//						//and add the transaction to the backstack
//					    
//						transaction.replace(R.id.Fragment_jiemian,new Fragmnet_main());
//						transaction.addToBackStack(null);
//						transaction.commit();

					
						break;

					case R.id.rb_main_menu2:
						
						switchContent(getVisibleFragment(), fans);
					
//						FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
//						//Replace whatever is in the fragment_container view with thisfragment,
//						//and add the transaction to the backstack
//						transaction2.replace(R.id.Fragment_jiemian,new Fragment_Fan());
//						
//						transaction2.commit();
						
					
						break;
					case R.id.rb_main_menu3:
						switchContent(getVisibleFragment(), new Fragment_heatExchange());
//						FragmentTransaction transaction2=getSupportFragmentManager().beginTransaction();
//						//Replace whatever is in the fragment_container view with thisfragment,
//						//and add the transaction to the backstack
//						transaction2.replace(R.id.Fragment_jiemian,new Fragment_Fan());
//						
//						transaction2.commit();
						
					
						break;
					case R.id.rb_main_menu4:
						switchContent(getVisibleFragment(), new Fragment_newdetail());
//						FragmentTransaction transaction3=getSupportFragmentManager().beginTransaction();
//						//Replace whatever is in the fragment_container view with thisfragment,
//						//and add the transaction to the backstack
//						transaction3.replace(R.id.Fragment_jiemian,new Fragment_details());
//						transaction3.commit();
						break;
					case R.id.rb_main_menu5:
						switchContent(getVisibleFragment(), new Fragment_Settings());
//							FragmentTransaction transaction4=getSupportFragmentManager().beginTransaction();
//							//Replace whatever is in the fragment_container view with thisfragment,
//							//and add the transaction to the backstack
//						    
//							transaction4.replace(R.id.Fragment_jiemian,new Fragment_Settings());
//							
//							transaction4.commit();
							break;
					case R.id.rb_main_menu6:
						switchContent(getVisibleFragment(), new Fragment_aoubtus());
//						FragmentTransaction transaction5=getSupportFragmentManager().beginTransaction();
//						//Replace whatever is in the fragment_container view with thisfragment,
//						//and add the transaction to the backstack
//					    
//						transaction5.replace(R.id.Fragment_jiemian,new Fragment_aoubtus());
//						
//						transaction5.commit();
						break;
					}
										
				}
			});
	}
	private void initView() {
		// TODO Auto-generated method stub
		group_main=(RadioGroup)findViewById(R.id.radioGroup_main);
		button_main=(RadioButton)findViewById(R.id.rb_main_menu1);
		group_main.check(button_main.getId()); 
		fragmnet_main=new Fragmnet_main();
		fans=new Fragment_fansa();
		
		FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
		//Replace whatever is in the fragment_container view with thisfragment,
		//and add the transaction to the backstack
	    
		transaction.replace(R.id.Fragment_jiemian,new Fragmnet_main());
		transaction.addToBackStack(null);
		transaction.commit();
	}
	public void switchContent(Fragment from, Fragment to) {  
        if (mcontent!= to) {  
        	mcontent = to;  
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();  //donghua
            if (!to.isAdded()) {    // 先判断是否被add过  
                transaction.hide(from).add(R.id.Fragment_jiemian, to).commit(); // 隐藏当前的fragment，add下一个到Activity中  
            } else {  
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个  
            }  
        }  
}
//	 private Fragment mContent;
//     /** 修改显示的内容 不会重新加载 **/
//     public void switchContent(Fragment to) {
//             if (mContent != to) {
//                     FragmentTransaction transaction = getSupportFragmentManager()
//                                     .beginTransaction();
//                     if (!to.isAdded()) { // 先判断是否被add过
//                             transaction.hide(mContent).add(R.id.content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
//                     } else {
//                             transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
//                     }
//                     mContent = to;
//             }
//             showContent();
//     }
public Fragment getVisibleFragment(){
    FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
    List<Fragment> fragments = fragmentManager.getFragments();
    for(Fragment fragment : fragments){
        if(fragment != null && fragment.isVisible())
            return fragment;
    }
    return null;
}
class ReceiveThread extends Thread {
	public void run() {

		while (serialPort.isOpen) {
			
				String type ="HEX";
				String data = serialPort.receiveData(type);
				if (data != null) {
					Message msg = new Message();
					msg.what = 1;
					msg.obj = data;
					System.out.println(data + "<<<<<<<<==========data");
					mHandler.sendMessage(msg);
				}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


}

