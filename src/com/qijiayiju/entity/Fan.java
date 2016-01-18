package com.qijiayiju.entity;

public class Fan {
	int chooseID;

	public Fan(int chooseID, String name, boolean status, int level) {
		super();
		this.chooseID = chooseID;
		this.name = name;
		this.status = status;
		this.level = level;
	}

	String name;
	boolean status;

	public int getChooseID() {
		return chooseID;
	}

	public void setChooseID(int chooseID) {
		this.chooseID = chooseID;
	}

	int level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Fan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fan(String name, boolean status, int level) {
		super();
		this.name = name;
		this.status = status;
		this.level = level;
	}

}
