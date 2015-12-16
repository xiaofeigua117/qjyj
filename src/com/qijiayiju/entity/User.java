package com.qijiayiju.entity;


import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;



@Table(name="Chapter")
public class User {
	
	@Id(column="id")
	int id;
	
	@Column(column="name")
	private String name;
	@Column(column="password")
	private String password;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ "]";
	}
}
