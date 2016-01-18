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
							.setText("室内温度   23.5摄氏度  ，室外温度33摄氏度，温差较大。新浪云主机（Sina Elastic Cloud 简称SEC）是潜心打造的新一代简单、可靠的IaaS服务，为您提供基于安全可靠的基础网络的高性能易扩展的计算服务。激活邀请码  已申请公测，等待发放邀请码优势支持Docker生态及周边管理系统（即将上线，敬请期待）秒级资源计费、共享SAE、微博支付体");
					break;
				case 1:
					textView_detail.setText("室外温度   33.5摄氏度  ，高于室温，注意避暑。");
					break;
				case 2:
					textView_detail.setText("室空气湿度为   45Rh%  属于相对舒适环境。");
					break;
				case 3:
					textView_detail.setText("室内co2 含量为  223  ，当高于300是自动开启通风。");
					break;
				case 4:
					textView_detail.setText("室外风速  7 m。。。。。。。。。。");
					break;

				}

			}
		});
		return view;
	}

}
