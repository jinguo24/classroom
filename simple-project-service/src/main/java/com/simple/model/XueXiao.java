package com.simple.model;

import java.io.Serializable;

public class XueXiao implements Serializable{

	private static final long serialVersionUID = 1L;

	private String xxbh;
	private String xxmc;
	public String getXxbh() {
		return xxbh;
	}
	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
}
