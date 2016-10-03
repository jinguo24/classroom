package com.simple.model.api;

import java.io.Serializable;

public class XueYuan implements Serializable{

	private static final long serialVersionUID = 1L;

	private String xm="";
	private String gh="";
	private int sign;
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
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
}
