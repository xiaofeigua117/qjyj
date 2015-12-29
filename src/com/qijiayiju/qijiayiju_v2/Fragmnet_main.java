package com.qijiayiju.qijiayiju_v2;

import java.text.SimpleDateFormat;
import java.util.Date;








import com.qijiayiju.utils.MySeriailPort;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragmnet_main extends Fragment {
	String date;
    private static final int UPDATE=1;
    Message message=null;
    TextView textView_title;
    TextView textView_second;
    TextView textView_date;
    TextView titTextView;
    private int jishu=0;
    private TextView textView_shiwen;
    private TextView textView_shiwaishidu;
    private TextView textView_shineishidu;
    private TextView textView_woshiwendu;
    private TextView textView_woshishidu;
    private Button button_shuaxin;
    private TextView textView_co2;
    private TextView textView_shiwai;
    String time;
    static String s=" ";
    public Typeface tf;
    public Typeface tf2;
    public Typeface tf3;
    Handler handler=new Handler(){
    
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE:
				time=(String)msg.obj;
				textView_date.setText(time.substring(0, 12));
				time=time.substring(12);
				jishu=jishu+1;
			    if(jishu==5){
			    	//MySeriailPort.getSeriail().sendData("0103000b000435cb", "HEX");
			    	MySeriailPort.getSeriail().sendData("0103000b000835ce", "HEX");
			    	//000b0008   35ce
			    	//MySeriailPort.getSeriail().sendData("0103000c00014409", "HEX");
			    //	MySeriailPort.getSeriail().sendData("0103000d000115c9", "HEX");
			    	textView_shiwen.setText((float)(Math.round(MainActivity.shiwen*10))/10+"℃");
			    	textView_shineishidu.setText( (float)(Math.round(MainActivity.shineishidu*10))/10+"Rh%");
			    	textView_shiwai.setText((float)((Math.round(MainActivity.shiwaiwendu)*10))/10+"℃");
			    	textView_shiwaishidu.setText((float)(Math.round(MainActivity.shiwaishidu*10))/10+"Rh%");
			    	textView_co2.setText((float)(Math.round(MainActivity.shineico2))+"ppm");
			    	textView_woshiwendu.setText((float)(Math.round(MainActivity.woshiwendu1*10))/10+"℃");
			    	textView_woshishidu.setText((float)(Math.round(MainActivity.woshishidu1*10))/10+"Rh%");
			    	
			    	jishu=0;
			    }
				                                                                                                                                                                                                                                          
				textView_second.setText(time+"");
				
				break;
			}
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
    };
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			 
			
			//fengji();
			Time time = new Time("GMT+8");       
	        time.setToNow(); 
	      
	        new Thread(new Runnable() {
	        	@Override
	        	public void run() {
	        		// TODO Auto-generated method stub
	        		SimpleDateFormat formatter =   
	                new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");  
	        		Date curDate = new Date(System.currentTimeMillis());  
	        		String currentTime = formatter.format(curDate);  
	            	message = handler.obtainMessage(UPDATE, currentTime);  
	            	handler.sendMessage(message);  
	            	//use Handler to control the time  
	            	handler.postDelayed(this, 1000);  
	        	}
	        }).start();
	      
		}
		
		
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			
			  AssetManager mgr=getActivity().getAssets();
		        tf=Typeface.createFromAsset(mgr, "fonts/StencilStd.otf");
		        tf2=Typeface.createFromAsset(mgr, "fonts/time.ttf");
		        tf3=Typeface.createFromAsset(mgr, "fonts/mnjxl.ttf");
		        	textView_second.setTypeface(tf2);
		        	textView_co2.setTypeface(tf2);
		        	textView_shineishidu.setTypeface(tf2);
		        	titTextView.setTypeface(tf);
		        	textView_date.setTypeface(tf);
		        	textView_shiwen.setTypeface(tf2);
		        	textView_shiwai.setTypeface(tf2);
		        	textView_shiwaishidu.setTypeface(tf2);
		        	titTextView.setTypeface(tf3);
		        	
		}



		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			View view=inflater.inflate(R.layout.fragment_first,container,false);
			  titTextView=(TextView)view.findViewById(R.id.title);
		        textView_date=(TextView)view.findViewById(R.id.date);
		        textView_second=(TextView)view.findViewById(R.id.time);
		       textView_shiwen=(TextView)view.findViewById(R.id.show_shiwen);
		       textView_co2=(TextView)view.findViewById(R.id.co2_value);
		       textView_shineishidu=(TextView)view.findViewById(R.id.shidu);
		       textView_shiwai=(TextView)view.findViewById(R.id.show_shiwai);
		       textView_woshiwendu=(TextView)view.findViewById(R.id.wendu_woshi);
		       textView_woshishidu=(TextView)view.findViewById(R.id.shidu_woshi);
		       
		       textView_shiwaishidu=(TextView)view.findViewById(R.id.show_shiwaishidu);
		     //  textView_shiwen.setText(MainActivity.comm1+" ℃");
		  
		       
		        
		        //得到AssetManager
	        	//根据路径得到Typeface
	        	
	        	
		return view;
		}
}
