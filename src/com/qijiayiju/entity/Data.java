package com.qijiayiju.entity;



import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;



@Table(name="Data")
public class Data {
	
	@Id(column="id")
	int id;
	@Column(column="date")
	private String date;
	@Column(column="co2")
	private String co2;
	@Column(column="wendu_shiwai")
	private String wendu_shiwai;
	@Column(column="shidu_shiwai")
	private String shidu_shiwai;
	@Column(column="wendu_shinei")
	private String wendu_shinei;
	@Column(column="shidu_shinei")
	private String shidu_shinei;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCo2() {
		return co2;
	}
	public void setCo2(String co2) {
		this.co2 = co2;
	}
	public String getWendu_shiwai() {
		return wendu_shiwai;
	}
	public void setWendu_shiwai(String wendu_shiwai) {
		this.wendu_shiwai = wendu_shiwai;
	}
	public String getShidu_shiwai() {
		return shidu_shiwai;
	}
	public void setShidu_shiwai(String shidu_shiwai) {
		this.shidu_shiwai = shidu_shiwai;
	}
	public String getWendu_shinei() {
		return wendu_shinei;
	}
	public void setWendu_shinei(String wendu_shinei) {
		this.wendu_shinei = wendu_shinei;
	}
	public String getShidu_shinei() {
		return shidu_shinei;
	}
	public void setShidu_shinei(String shidu_shinei) {
		this.shidu_shinei = shidu_shinei;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", date=" + date + ", time=" + co2
				+ ", wendu_shiwai=" + wendu_shiwai + ", shidu_shiwai="
				+ shidu_shiwai + ", wendu_shinei=" + wendu_shinei
				+ ", shidu_shinei=" + shidu_shinei + "]";
	}
	
	
	
	
	

}
