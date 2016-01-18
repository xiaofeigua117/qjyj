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
				"公司简介",
				"北京齐家怡居科技有限公司（以下简称齐家怡居）是一家致力于提供微能耗（零能耗）高舒适度建筑的整体解决方案的高科技企业。齐家怡居集系统研发、节能建筑工程顾问、系统设计、零能耗建筑系统施工和投入使用后运营维护等职责于一体。齐家怡居致力于为客户创造价值，擅长从技术经济角度进行分析，找出项目节能系统投入的关键点，有效为客户降低成本、提高效益，并保证项目比传统建筑拥有更优异的舒适度、健康性和安全性等方面性能。 齐家怡居拥有先进的设计理念、专利技术和明确的发展规划，同时与德国维卡、北京新立基、环都拓扑等多家国内外知名企业合作。目前在顺义马坡、高丽营都有我公司的实验项目。齐家怡居在零能耗高舒适度建筑领域已走在行业前列，目前已拥有18项国家专利。我们将用5年时间把齐家怡居建成一家全球领先的零能耗人居环境系统供应商。一个站在时代潮头的企业没有理由不拥有一流的人才，如果您对环保事业有崇高的热情，如果您对目前居住的建筑环境有独特的看法，那么齐家怡居期待您的加入！");
		Qijia jianjie2 = new Qijia(
				"公司理念",
				"工业化大生产能够批量生产同质化的产品，同一条生产线上的两个产品完全可以做到几乎没有区别；而建筑业更多地由手工来操作，那么不同工人之间，同一个工人在不同时间完成的工序，难免会有差异。在传统建筑业，施工规范是允许差异的，因为设计师在设计时已经考虑那些差异了，因此最终产品仍然都是合格的。但是在零能耗高舒适度建筑的建造过程中，对很多关键工序的误差允许却比传统建筑要严格得多，这是因为一旦某个环节出了问题，整个系统就达不到预期效果。");
		Qijia jianjie3 = new Qijia("联系我们", "                  电话：010-61429395"
				+ "                  地址：北京市顺义区南法信旭辉空港中心C座423"
				+ "                  微信平台： 齐家怡居");
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
