package com.simple.model.api;

import java.io.Serializable;
import java.util.Date;

public class CheckVersion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String bbh;
	private Date cjsj;
	private String appxzdz;
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getAppxzdz() {
		return appxzdz;
	}
	public void setAppxzdz(String appxzdz) {
		this.appxzdz = appxzdz;
	}
}
