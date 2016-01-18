package com.qijiayiju.qijiayiju_v2;

import java.util.Calendar;

import com.qijiayiju.utils.MySeriailPort;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment_heatExchange extends Fragment {
	private Button button_power;
	CheckBox box_power;
	TextView power_icon;
	private Spinner spinner_parttom;
	TextView textView_status;
	Button button_settime;
	String[] parttoms = { "制热模式", "制冷模式", "除霜模式" };
	 Button button_time1;
	 TextView button_time2;
	private String time1String;
	private String time2String;
	private TextView timexianshi;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.heatexchange_item,
				container, false);
		spinner_parttom = (Spinner) view.findViewById(R.id.spinner);
		power_icon = (TextView) view.findViewById(R.id.power_icon);
		textView_status = (TextView) view.findViewById(R.id.textView_status);
		button_settime = (Button) view.findViewById(R.id.button_settime);
		timexianshi=(TextView)view.findViewById(R.id.textView_timeset);
		SharedPreferences settings = getActivity().getPreferences(Activity.MODE_PRIVATE);
		time1String = settings.getString("TIME2", "请设置时间");
		time2String = settings.getString("TIME3", "请设置时间");
		timexianshi.setText("自动制热时间   "+time1String+"——"+time2String);
		box_power = (CheckBox) view.findViewById(R.id.box_power);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.simple_spinner_item);
		adapter.addAll(parttoms);
		box_power.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@SuppressLint("NewApi")
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					MySeriailPort.sendOrdersHeat(true);
					power_icon.setBackgroundResource(R.drawable.iconon);
					power_icon.setBackground(getResources().getDrawable(
							R.drawable.iconon));
					textView_status.setText("热泵已开启");
				}
				if (!arg1) {
					MySeriailPort.sendOrdersHeat(false);
					power_icon.setBackgroundResource(R.drawable.iconoff);
					textView_status.setText("热泵已关闭");
				}

			}
		});

		spinner_parttom.setAdapter(adapter);
		button_settime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showPopWindow(getActivity(), arg0);
			}
		});
		return view;

	}

	private void showPopWindow(Context context, View parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View vPopWindow = inflater.inflate(R.layout.popwindow, null,
				false);
		// 宽300 高300
		final PopupWindow popWindow = new PopupWindow(vPopWindow, 300, 300,
				true);
		SharedPreferences settings = getActivity().getPreferences(Activity.MODE_PRIVATE);
		time1String = settings.getString("TIME2", "请设置时间");
		time2String = settings.getString("TIME3", "请设置时间");
		  button_time1 = (Button) vPopWindow.findViewById(R.id.button_time1);
		  button_time1.setText(time1String);
	  button_time1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showmyDialog(0);
			}
		});
	  button_time2 = (Button) vPopWindow.findViewById(R.id.button_time2);
	  button_time2.setText(time2String);
	  button_time2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showmyDialog2(0);
			}
		});
		Button cancleButton = (Button) vPopWindow.findViewById(R.id.button_update);
		cancleButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getActivity().getPreferences(Activity.MODE_PRIVATE);
				time1String = settings.getString("TIME2", "请设置时间");
				time2String = settings.getString("TIME3", "请设置时间");
				timexianshi.setText("自动制热时间   "+time1String+"—"+time2String);
				popWindow.dismiss(); // Close the Pop Window
			}
		});
		int[] location = new int[2];
		parent.getLocationOnScreen(location);
		popWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0]
				+ parent.getWidth(), location[1]);

	}

	private void showmyDialog(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		TimePickerDialog pickerDialog = new TimePickerDialog(getActivity(),
				new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker arg0, int hour, int min) {
						// TODO Auto-generated method stub
						SharedPreferences timeShare = getActivity().getPreferences(2);
						SharedPreferences.Editor editor = timeShare.edit();
						editor.putString("TIME2", hour + ":" + min + "");
						
						editor.commit();
						button_time1.setText(hour+":"+min);
					}
				}, hour, min, true);
		pickerDialog.show();

	}
	private void showmyDialog2(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		TimePickerDialog pickerDialog = new TimePickerDialog(getActivity(),
				new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker arg0, int hour, int min) {
						// TODO Auto-generated method stub
						SharedPreferences timeShare = getActivity().getPreferences(2);
						SharedPreferences.Editor editor = timeShare.edit();
						editor.putString("TIME3", hour + ":" + min + "");
						
						editor.commit();
						button_time2.setText(hour+":"+min);
					}
				}, hour, min, true);
		pickerDialog.show();

	}
}
