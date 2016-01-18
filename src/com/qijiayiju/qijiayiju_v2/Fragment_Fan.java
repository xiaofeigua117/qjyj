package com.qijiayiju.qijiayiju_v2;

import java.nio.ByteBuffer;
import java.util.Date;

import com.dwin.dwinapi.SerialPort;
import com.qijiayiju.utils.DBHelper;

import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Fan extends Fragment {
	private Switch switch_fengji1;
	// private SerialPort serialPort;
	private com.qijiayiju.utils.SerialPort serialPort;
	private TextView textView_value;
	private TextView textView_fan_main;
	private TextView textView_fan_washroom;
	TextView textView_showmain;
	private TextView textView_fan_three;
	DBHelper dbHelper;
	Handler nhandler = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 要做的事情
			serialPort.sendData("021000810001025aa5566a", "HEX");
			nhandler.postDelayed(this, 5000);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dbHelper = new DBHelper();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// mHandler.removeCallbacks(ReceiveThread.)
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_control, container,
				false);
		switch_fengji1 = (Switch) view.findViewById(R.id.switch_fengji1);
		textView_value = (TextView) view.findViewById(R.id.textView_fengsuzhi);
		textView_fan_main = (TextView) view.findViewById(R.id.main_fan);
		textView_fan_washroom = (TextView) view.findViewById(R.id.washroom_fan);
		textView_fan_main.setBackgroundResource(R.drawable.fan_inner2);
		textView_fan_main.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// textView_value.setText("1111111111111111111111");
				textView_fan_main.setBackgroundResource(R.drawable.fan_inner2);
				textView_fan_washroom.setBackgroundResource(0);
			}
		});
		textView_fan_washroom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// textView_value.setText("22222222222222222222222");
				textView_fan_washroom
						.setBackgroundResource(R.drawable.fan_inner2);
				textView_fan_main.setBackgroundResource(0);
			}
		});

		// serialPort = new SerialPort("S0",115200, 8, 1, 'n');
		serialPort = new com.qijiayiju.utils.SerialPort("S0", 115200, 8, 1, 'n');
		serialPort.isOpen = true;
		// serialPort.receiveData("HEX");
		new ReceiveThread().start();
		// serialPort = new SerialPort(mTag, mBackStackNesting,
		// mBackStackNesting, mBackStackNesting, mBackStackNesting);
		switch_fengji1
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean checked) {
						// TODO Auto-generated method stub
						if (checked) {
							// byte[] sbuf =
							// CRC16M.getSendBuf(editText_fa.getText().toString());
							// textView_jiaoyan.setText(CRC16M.getBufHexStr(sbuf));
							serialPort.sendData("021000810002045aa50100362c",
									"HEX");
							// new SendThread().start();
							nhandler.postDelayed(runnable, 8000);
						} else {
							serialPort.sendData("021000810002045aa5000037bc",
									"HEX");
							nhandler.removeCallbacks(runnable);
						}
					}
				});
		return view;
	}

	// class SendThread extends Thread{
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// //serialPort.sendData("021000810002045aa50100362c", "HEX");
	// Message msg = new Message();
	// msg.what = 1;
	// nHandler.sendMessage(msg);
	// try {
	// Thread.sleep(5000);//可能有冲突时间
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// }
	class ReceiveThread extends Thread {
		public void run() {

			while (serialPort.isOpen) {

				String type = "HEX";
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

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Date date = new Date();
				textView_value.append("[" + date.getMinutes() + ":"
						+ date.getSeconds() + "] " + (CharSequence) msg.obj);

				CharSequence data = (CharSequence) msg.obj;
				if (data.charAt(0) == '0') {
					Toast.makeText(getActivity(), msg.obj.toString(), 2000)
							.show();

				}
				MainActivity.comm1 = msg.obj.toString();
				Toast.makeText(getActivity(), MainActivity.comm1, 2000).show();
				break;
			// case 2:
			// serialPort.sendData("021000810002045aa50100362c", "HEX");
			//
			// break;

			}
		};
	};

	private String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

}
