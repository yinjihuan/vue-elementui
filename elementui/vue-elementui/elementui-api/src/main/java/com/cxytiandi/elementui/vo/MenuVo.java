package com.cxytiandi.elementui.vo;

import java.util.List;

public class MenuVo {
	private String icon;
	private String index;
	private String title;
	private List<MenuVo> subs;
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<MenuVo> getSubs() {
		return subs;
	}
	public void setSubs(List<MenuVo> subs) {
		this.subs = subs;
	}
	
}
