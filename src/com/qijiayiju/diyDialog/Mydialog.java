package com.qijiayiju.diyDialog;

import com.qijiayiju.qijiayiju_v2.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class Mydialog extends Dialog {

	Context context;
	private View customView;

	public Mydialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	public Mydialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		customView = inflater.inflate(R.layout.dialog, null);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dialog);
	}

	@Override
	public View findViewById(int id) {
		// TODO Auto-generated method stub
		return super.findViewById(id);
	}

	public View getCustomView() {
		return customView;
	}
}