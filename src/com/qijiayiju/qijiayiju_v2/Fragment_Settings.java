package com.qijiayiju.qijiayiju_v2;

import java.util.Calendar;
import java.util.List;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.adapter.DataAdapter;
import com.qijiayiju.entity.Data;
import com.qijiayiju.entity.Tongfengentity;
import com.qijiayiju.utils.CreatExcels;
import com.qijiayiju.utils.DBHelper;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Fragment_Settings extends Fragment {
	private Button button;
	private DbUtils dbUtils;
	List<Data> list;
	Button button_tongfeng;
	Button button_shijian;
	Button button_naozhong;
	Context context;
	private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	private TimePickerDialog.OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker arg0, int hour, int min) {
			// TODO Auto-generated method stub
			Toast.makeText(context, hour + "  " + min + "", 3000).show();
			setReminder(true, hour, min);

		}
	};

	private void setReminder(boolean b, int hour, int min) {

		// get the AlarmManager instance
		AlarmManager am = (AlarmManager) getActivity()
				.getSystemService("alarm");
		// create a PendingIntent that will perform a broadcast
		PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0,
				new Intent(getActivity(), MyReceiver.class), 0);

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
		} else {
			// cancel current alarm
			am.cancel(pi);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_moresettings, container,
				false);
		context = getActivity();
		button = (Button) view.findViewById(R.id.button_delete);
		button_shijian = (Button) view.findViewById(R.id.button_shijian);
		button_tongfeng = (Button) view.findViewById(R.id.button_tongfeng);
		button_naozhong = (Button) view.findViewById(R.id.button_naozhong);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "正在删除", Toast.LENGTH_LONG).show();
				try {
					dbUtils = DBHelper.getUtils();
					// list =
					// dbUtils.findAll(Selector.from(Data.class).where("id"
					// ,"<", 800));
					list = dbUtils.findAll(Selector.from(Data.class));
					// dbUtils.delete(stus.get(0));
					dbUtils.deleteAll(list);
					Toast.makeText(getActivity(), "删除完毕", Toast.LENGTH_LONG)
							.show();
					// dbUtils.deleteById(Data.class, WhereBuilder.b("date",
					// "==", "02 10 00 81 00 01 51 D2"));
					// dbUtils.dropTable(Data.class);
					// dbUtils.dropDb();
					// dbUtils=DBHelper.getUtils();
					// dbUtils.delete(Data.class);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button_tongfeng.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(getActivity())
						.setTitle("请选择开启时的通风时间")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setPositiveButton("确定", null)
						.setSingleChoiceItems(
								new String[] { "10分钟", "20分钟", "40分钟" }, 0,
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface arg0,
											int arg1) {
										// TODO Auto-generated method stub
										switch (arg1) {
										case 0:
											Tongfengentity.setTime_tongfeng(10);
											break;
										case 1:
											Tongfengentity.setTime_tongfeng(20);
											break;
										case 2:
											Tongfengentity.setTime_tongfeng(40);
											break;

										}
									}
								}).show();

			}
		});
		button_shijian.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				getActivity()
						.startActivity(
								new Intent(
										android.provider.Settings.ACTION_DATE_SETTINGS));
			}
		});
		button_naozhong.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub、
				Intent intent1 = new Intent(getActivity(),
						TongfengAutoActivity.class);
				startActivity(intent1);
				// showDialog();
			}
		});

		return view;
	}

	public void showDialog() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		TimePickerDialog pickerDialog = new TimePickerDialog(context,
				onTimeSetListener, hour, min, true);
		pickerDialog.show();

	}

}
