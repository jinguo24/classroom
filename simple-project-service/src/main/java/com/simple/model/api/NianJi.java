package com.simple.model.api;

import java.io.Serializable;

public class NianJi implements Serializable{

	private static final long serialVersionUID = 1L;
	private String njmc ="" ;
	private String njbh ="";
	public String getNjmc() {
		return njmc;
	}
	public void setNjmc(String njmc) {
		this.njmc = njmc;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}
}
