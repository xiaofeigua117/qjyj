package com.qijiayiju.utils;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

public class DBHelper {
	private static DbUtils utils;

	public static void initUtils(Context context) {
		utils = DbUtils.create(context, "qijiayiju.db");
		// debug开关
		utils.configDebug(true);
		// 事物开关
		utils.configAllowTransaction(true);
	}

	public static DbUtils getUtils() {
		return utils;
	}
}
