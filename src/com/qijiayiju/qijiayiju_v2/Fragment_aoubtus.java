package com.qijiayiju.qijiayiju_v2;

import java.util.ArrayList;
import java.util.List;

import com.qijiayiju.adapter.MyAboutAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class Fragment_aoubtus extends Fragment {
	WebView mWebView;
	ListView listView_gongsi;
	MyAboutAdapter aboutAdapter;
	List<Qijia> list;
	Context context;
	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_about, container, false);
		listView_gongsi = (ListView) view.findViewById(R.id.listView1);
		list = new ArrayList<Qijia>();
		textView = (TextView) view.findViewById(R.id.textView_detail);
		Qijia jianjie1 = new Qijia(
				"��˾���",
				"����������ӿƼ����޹�˾�����¼��������ӣ���һ���������ṩ΢�ܺģ����ܺģ������ʶȽ����������������ĸ߿Ƽ���ҵ��������Ӽ�ϵͳ�з������ܽ������̹��ʡ�ϵͳ��ơ����ܺĽ���ϵͳʩ����Ͷ��ʹ�ú���Ӫά����ְ����һ�塣�������������Ϊ�ͻ������ֵ���ó��Ӽ������ýǶȽ��з������ҳ���Ŀ����ϵͳͶ��Ĺؼ��㣬��ЧΪ�ͻ����ͳɱ������Ч�棬����֤��Ŀ�ȴ�ͳ����ӵ�и���������ʶȡ������ԺͰ�ȫ�Եȷ������ܡ� �������ӵ���Ƚ���������ר����������ȷ�ķ�չ�滮��ͬʱ��¹�ά�����������������������˵ȶ�ҹ�����֪����ҵ������Ŀǰ��˳�����¡�����Ӫ�����ҹ�˾��ʵ����Ŀ��������������ܺĸ����ʶȽ���������������ҵǰ�У�Ŀǰ��ӵ��18�����ר�������ǽ���5��ʱ���������ӽ���һ��ȫ�����ȵ����ܺ��˾ӻ���ϵͳ��Ӧ�̡�һ��վ��ʱ����ͷ����ҵû�����ɲ�ӵ��һ�����˲ţ�������Ի�����ҵ�г�ߵ����飬�������Ŀǰ��ס�Ľ��������ж��صĿ�������ô��������ڴ����ļ��룡");
		Qijia jianjie2 = new Qijia(
				"��˾����",
				"��ҵ���������ܹ���������ͬ�ʻ��Ĳ�Ʒ��ͬһ���������ϵ�������Ʒ��ȫ������������û�����𣻶�����ҵ��������ֹ�����������ô��ͬ����֮�䣬ͬһ�������ڲ�ͬʱ����ɵĹ���������в��졣�ڴ�ͳ����ҵ��ʩ���淶���������ģ���Ϊ���ʦ�����ʱ�Ѿ�������Щ�����ˣ�������ղ�Ʒ��Ȼ���Ǻϸ�ġ����������ܺĸ����ʶȽ����Ľ�������У��Ժܶ�ؼ�������������ȴ�ȴ�ͳ����Ҫ�ϸ�ö࣬������Ϊһ��ĳ�����ڳ������⣬����ϵͳ�ʹﲻ��Ԥ��Ч����");
		Qijia jianjie3 = new Qijia("��ϵ����", "                  �绰��010-61429395"
				+ "                  ��ַ��������˳�����Ϸ�����Կո�����C��423"
				+ "                  ΢��ƽ̨�� �������");
		list.add(jianjie1);
		list.add(jianjie2);
		list.add(jianjie3);
		aboutAdapter = new MyAboutAdapter(context, list);
		listView_gongsi.setAdapter(aboutAdapter);
		listView_gongsi.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				textView.setText(list.get(arg2).getDetail());

			}
		});

		return view;
	}

}
