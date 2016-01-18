package com.qijiayiju.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.qijiayiju.activity.Fragment_fansa;
import com.qijiayiju.entity.Data;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class MySeriailPort {
	private static SerialPort serialPort;
	DbUtils dbUtils;
	static Context context;
	static byte[] modbusOrder;
	static String modbusOrderString;

	static Handler nhandler = new Handler();
	static Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 要做的事情
			serialPort.sendData("021000810001025aa5566a", "HEX");

			nhandler.postDelayed(this, 5000);
		}
	};

	public static void initSeriailPort(Context context) {
		serialPort = new SerialPort("S0", 115200, 8, 1, 'n');
		MySeriailPort.context = context;
		serialPort.isOpen = true;
		nhandler.postDelayed(runnable, 8000);

	}

	public static void startThread() {

	}

	public static SerialPort getSeriail() {
		return serialPort;

	}

	public static void sendOrders(Boolean boolean1, int i) {
		String suborder = "0000";
		String s1;
		String s2;
		String s3 = null;
		String s4;
		String s6;
		suborder = Instruction.order.substring(14);
		s1 = CRC16M.hexString2binaryString(suborder);
		if (boolean1) {
			switch (i) {
			case 0:
				Fragment_fansa.fan1.setLevel(0);
				s2 = CRC16M.hexString2binaryString("fff8");
				s3 = CRC16M.yuBybit(s1, s2);
				s2 = CRC16M.hexString2binaryString("0000");
				Log.i("在界面二----------------------------22", "打开了主风机0档");
				s3 = CRC16M.HuoBybit(s3, s2);
				Log.i("-----------------", s3);
				break;
			case 1:
				Fragment_fansa.fan1.setLevel(1);
				s2 = CRC16M.hexString2binaryString("fff8");
				s3 = CRC16M.yuBybit(s1, s2);
				s2 = CRC16M.hexString2binaryString("0001");
				s3 = CRC16M.HuoBybit(s3, s2);
				Log.i("-----------------", s3);
				Log.i("在界面二----------------------------11", "打开了主风机低档");

				break;
			case 2:
				Fragment_fansa.fan1.setLevel(2);
				s2 = CRC16M.hexString2binaryString("fff8");
				s3 = CRC16M.yuBybit(s1, s2);
				s2 = CRC16M.hexString2binaryString("0002");
				s3 = CRC16M.HuoBybit(s3, s2);
				Log.i("在界面二----------------------------11", "打开了主风机高档");
				break;
			}

		}
		if (!boolean1) {
			switch (i) {

			case 0:
				Fragment_fansa.fan2.setLevel(0);
				s2 = CRC16M.hexString2binaryString("ff8f");
				s3 = CRC16M.yuBybit(s1, s2);
				Log.i("在界面二----------------------------22", "打开了卫生间风机0档");
				s2 = CRC16M.hexString2binaryString("0000");
				s3 = CRC16M.HuoBybit(s3, s2);
				break;
			case 1:
				Fragment_fansa.fan2.setLevel(1);
				s2 = CRC16M.hexString2binaryString("ff8f");
				Log.i("在界面二----------------------------22", "打开了卫生间风机低档");
				s3 = CRC16M.yuBybit(s1, s2);
				s2 = CRC16M.hexString2binaryString("0010");
				s3 = CRC16M.HuoBybit(s3, s2);
				break;
			case 2:
				Fragment_fansa.fan2.setLevel(2);
				s2 = CRC16M.hexString2binaryString("ff8f");
				s3 = CRC16M.yuBybit(s1, s2);
				s2 = CRC16M.hexString2binaryString("0020");
				s3 = CRC16M.HuoBybit(s3, s2);
				Log.i("在界面二----------------------------22", "打开了卫生间风机GAO档");
				break;
			}
		}

		s4 = CRC16M.binaryString2hexString(s3);
		Log.i("指令为------------------------》》》", s4);
		Instruction.order = Instruction.orderadd.concat(s4);
		modbusOrder = CRC16M.getSendBuf(Instruction.order);
		modbusOrderString = CRC16M.getBufHexStr(modbusOrder);
		MySeriailPort.getSeriail().sendData(modbusOrderString, "HEX");
	}

	public static void sendOrders(Boolean boolean1) {
		String suborder = "0000";
		String s1;
		String s2;
		String s3 = null;
		String s4;
		String s6;

		suborder = Instruction.order.substring(14);
		s1 = CRC16M.hexString2binaryString(suborder);
		if (boolean1) {
			s2 = CRC16M.hexString2binaryString("fff7");
			s3 = CRC16M.yuBybit(s1, s2);
		} else {
			s2 = CRC16M.hexString2binaryString("0008");
			s3 = CRC16M.HuoBybit(s1, s2);
		}

		s4 = CRC16M.binaryString2hexString(s3);
		Instruction.order = Instruction.orderadd.concat(s4);
		modbusOrder = CRC16M.getSendBuf(Instruction.order);
		modbusOrderString = CRC16M.getBufHexStr(modbusOrder);
		MySeriailPort.getSeriail().sendData(modbusOrderString, "HEX");
	}

	public static void sendOrdersHeat(Boolean boolean1) {
		String suborder = "0000";
		String s1;
		String s2;
		String s3 = null;
		String s4;
		String s6;

		suborder = Instruction.order.substring(14);
		s1 = CRC16M.hexString2binaryString(suborder);
		if (boolean1) {

			s2 = CRC16M.hexString2binaryString("ff7f");
			s3 = CRC16M.yuBybit(s1, s2);
			s2 = CRC16M.hexString2binaryString("0080");
			s3 = CRC16M.HuoBybit(s3, s2);
		} else {
			s2 = CRC16M.hexString2binaryString("ff7f");
			s3 = CRC16M.yuBybit(s1, s2);
		}

		s4 = CRC16M.binaryString2hexString(s3);
		Instruction.order = Instruction.orderadd.concat(s4);
		modbusOrder = CRC16M.getSendBuf(Instruction.order);
		modbusOrderString = CRC16M.getBufHexStr(modbusOrder);
		MySeriailPort.getSeriail().sendData(modbusOrderString, "HEX");
	}

}
