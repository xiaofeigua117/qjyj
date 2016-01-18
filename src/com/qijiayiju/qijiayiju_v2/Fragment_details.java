package com.qijiayiju.qijiayiju_v2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class Fragment_details extends Fragment {
	private GridView gridView;
	ArrayAdapter<String> adapter;
	TextView textView_detail;
	String[] a;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.datadetail, container, false);
		// getActivity().get
		gridView = (GridView) view.findViewById(R.id.gridView1);
		textView_detail = (TextView) view.findViewById(R.id.detail_content);
		a = getResources().getStringArray(R.array.name);
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, a);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int id,
					long arg3) {
				// TODO Auto-generated method stub
				switch (id) {
				case 0:
					textView_detail
							.setText("�����¶�   23.5���϶�  �������¶�33���϶ȣ��²�ϴ�������������Sina Elastic Cloud ���SEC����Ǳ�Ĵ������һ���򵥡��ɿ���IaaS����Ϊ���ṩ���ڰ�ȫ�ɿ��Ļ�������ĸ���������չ�ļ�����񡣼���������  �����빫�⣬�ȴ���������������֧��Docker��̬���ܱ߹���ϵͳ���������ߣ������ڴ����뼶��Դ�Ʒѡ�����SAE��΢��֧����");
					break;
				case 1:
					textView_detail.setText("�����¶�   33.5���϶�  ���������£�ע����");
					break;
				case 2:
					textView_detail.setText("�ҿ���ʪ��Ϊ   45Rh%  ����������ʻ�����");
					break;
				case 3:
					textView_detail.setText("����co2 ����Ϊ  223  ��������300���Զ�����ͨ�硣");
					break;
				case 4:
					textView_detail.setText("�������  7 m��������������������");
					break;

				}

			}
		});
		return view;
	}

}
