package com.cxytiandi.elementui.vo;

public class UserVo {
	private int id;
	private String name;
	private String address;
	private String date;
	
	public UserVo(int id, String name, String address, String date) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
