package com.qijiayiju.qijiayiju_v2;

import com.qijiayiju.activity.Fragment_fansa;
import com.qijiayiju.utils.Instruction;
import com.qijiayiju.utils.MySeriailPort;
import com.qijiayiju.utils.SerialPort;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;

/**
 * Receive the broadcast and start the activity that will show the alarm
 */
public class MyReceiver extends BroadcastReceiver {

	/**
	 * called when the BroadcastReceiver is receiving an Intent broadcast.
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Boolean b1 = (Boolean) bundle.get("b1");
		Boolean b2 = (Boolean) bundle.get("b2");
		String time = bundle.getString("time");
		Log.i("b1----------------bbbbb", b1 + "");
		Log.i("b2----------------bbbbb", b2 + "");
		Log.i("time-----------", time + " ");
		/* start another activity - MyAlarm to display the alarm */
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// intent.setClass(context, MyAlarm.class);
		// context.startActivity(intent);

		if (TongfengAutoActivity.fengji_choose1) {
			MySeriailPort.sendOrders(true, 1);

			Fragment_fansa.onResume2();
			Fragment_fansa.delay(true, TongfengAutoActivity.set_time);
		}
		if (TongfengAutoActivity.fengji_choose2) {
			MySeriailPort.sendOrders(false, 1);
			Fragment_fansa.onResume2();
			Fragment_fansa.delay(true, TongfengAutoActivity.set_time);

		}

	}

}
