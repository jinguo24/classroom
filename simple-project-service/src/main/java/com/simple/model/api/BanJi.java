package com.simple.model.api;

import java.io.Serializable;

public class BanJi implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bjmc = "";
	private String bjbh = "";
	private String xm = "";
	private String gh = "";
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getBjbh() {
		return bjbh;
	}
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
}
