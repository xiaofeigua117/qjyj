package com.qijiayiju.qijiayiju_v2;

import java.util.Calendar;

import com.qijiayiju.entity.Tongfengentity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class TongfengAutoActivity extends Activity {
	private Button buttona;
	private Button buttonb;
	private Button buttond;
	private Button buttonc;
	EditText editText;
	Button button_return;
	public static int set_time = 0;
	public static Boolean box1_tatus = false;
	public static Boolean box2_tatus = false;
	public static Boolean fengji_choose1 = false;
	public static Boolean fengji_choose2 = false;
	public static Boolean fengji_choose3 = false;
	public static Boolean fengji_choose4;
	public static String input_time1;
	CheckBox box1;
	CheckBox box2;
	CheckBox box3;
	CheckBox box4;
	Context context;
	EditText editText_1;
	TextView textView_time1;
	private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h

	private TimePickerDialog.OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker arg0, int hour, int min) {
			// TODO Auto-generated method stub
			setReminder(true, hour, min, fengji_choose1, fengji_choose2,
					input_time1);
			set_time = Integer.parseInt(editText_1.getText().toString());
			fengji_choose1 = box1_tatus;
			fengji_choose2 = box2_tatus;
			SharedPreferences time3Share = getPreferences(2);
			SharedPreferences.Editor editor = time3Share.edit();
			editor.putString("TIME1", hour + ":" + min + "");
			editor.putString("TIME0", set_time + "");
			editor.commit();
			Log.i("-----------timepicker", hour + min + "");
			Toast.makeText(TongfengAutoActivity.this,
					"已设定通风时间为 " + hour + ":" + min, Toast.LENGTH_SHORT).show();
			textView_time1.setText(hour + ":" + min);
		}
	};
	private String time1String;
	private String defalutString = "目前无设置";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_tongfeng_auto);

		context = this;
		SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
		time1String = settings.getString("TIME1", defalutString);
		buttona = (Button) findViewById(R.id.button_a);
		buttonb = (Button) findViewById(R.id.button_b);
		buttonc = (Button) findViewById(R.id.button_c);
		buttond = (Button) findViewById(R.id.button_d);
		box1 = (CheckBox) findViewById(R.id.checkBox1);
		box2 = (CheckBox) findViewById(R.id.checkBox2);

		editText_1 = (EditText) findViewById(R.id.editText_time);
		textView_time1 = (TextView) findViewById(R.id.textView_time1);
		textView_time1.setText(time1String);
		if (fengji_choose1) {
			box1.setChecked(true);
		}
		if (fengji_choose2) {
			box2.setChecked(true);
		}
		textView_time1.setText(settings.getString("TIME0", defalutString));

		box1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub

				// fengji_choose1=true;
				box1_tatus = arg1;

			}
		});
		box2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				box2_tatus = arg1;

			}
		});
		buttonb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PendingIntent sender = PendingIntent
						.getBroadcast(TongfengAutoActivity.this, 0, new Intent(
								TongfengAutoActivity.this, MyReceiver.class), 0);
				AlarmManager am;
				am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(sender);
				Toast.makeText(TongfengAutoActivity.this, "闹钟时间删除",
						Toast.LENGTH_SHORT).show();
				textView_time1.setText("目前无设置");
				// SharedPreferences保存数据，并提交
				SharedPreferences time3Share = getPreferences(2);
				SharedPreferences.Editor editor = time3Share.edit();
				editor.putString("TIME1", "目前无设置");
				editor.commit();
			}
		});
		buttond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PendingIntent sender = PendingIntent
						.getBroadcast(TongfengAutoActivity.this, 1, new Intent(
								TongfengAutoActivity.this, MyReceiver.class), 0);
				AlarmManager am;
				am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(sender);
				Toast.makeText(TongfengAutoActivity.this, "闹钟时间删除",
						Toast.LENGTH_SHORT).show();
				textView_time1.setText("目前无设置");
				// SharedPreferences保存数据，并提交
				SharedPreferences time3Share = getPreferences(2);
				SharedPreferences.Editor editor = time3Share.edit();
				editor.putString("TIME1", "目前无设置");
				editor.commit();
			}
		});
		buttonc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showmyDialog(1);

			}
		});

		buttona.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				showmyDialog(0);

			}
		});
	}

	private void setReminder(boolean b, int hour, int min, Boolean b1,
			Boolean b2, String time) {

		// get the AlarmManager instance
		AlarmManager am = (AlarmManager) getSystemService("alarm");
		// create a PendingIntent that will perform a broadcast
		Intent intent = new Intent(this, MyReceiver.class);

		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
		if (b) {
			// Date futureDate = new Date(new Date().getTime() + 86400000);
			// Random generator = new Random();
			// futureDate.setHours(0);
			// futureDate.setMinutes(generator.nextInt(10));
			// futureDate.setSeconds(0);
			// just use current time as the Alarm time.

			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, min);
			c.set(Calendar.SECOND, 10);
			c.set(Calendar.MILLISECOND, 0);
			am.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
					INTERVAL, pi);
		}

	}

	public void EditClick(View v) {
		switch (v.getId()) {
		case R.id.editText_time:
			new AlertDialog.Builder(context)
					.setPositiveButton("确定", null)
					.setSingleChoiceItems(
							new String[] { "30分钟", "50分钟", "60分钟" }, 0,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									// TODO Auto-generated method stub
									switch (arg1) {
									case 0:
										Tongfengentity.setTime_auto(30);
										editText_1.setText("30分钟");
										break;
									case 1:
										Tongfengentity.setTime_auto(40);
										editText_1.setText("40分钟");
										break;
									case 2:
										Tongfengentity.setTime_auto(60);
										editText_1.setText("60分钟");
										break;

									}
								}
							}).show();

			break;

		}
	}

	public void showmyDialog(int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		TimePickerDialog pickerDialog = new TimePickerDialog(this,
				onTimeSetListener, hour, min, true);
		pickerDialog.show();

	}
}
