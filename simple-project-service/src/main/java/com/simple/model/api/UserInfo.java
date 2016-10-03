package com.simple.model.api;

import java.io.Serializable;

public class UserInfo implements Serializable{

	public UserInfo(String xxmc, String xxbh, String tanentId) {
		super();
		this.xxmc = xxmc;
		this.xxbh = xxbh;
		this.tanentId = tanentId;
	}
	private static final long serialVersionUID = 1L;
	private String xxmc="";
	private String xxbh="";
	private String tanentId="";
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getXxbh() {
		return xxbh;
	}
	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
}
