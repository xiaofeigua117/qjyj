package com.qijiayiju.utils;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

public class DBHelper {
	private static DbUtils utils;

	public static void initUtils(Context context) {
		utils = DbUtils.create(context, "qijiayiju.db");
		// debug����
		utils.configDebug(true);
		// ���￪��
		utils.configAllowTransaction(true);
	}

	public static DbUtils getUtils() {
		return utils;
	}
}
