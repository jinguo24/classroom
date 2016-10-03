package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class App implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineId;
	private String tanentId;
	private String xxbh;
	private String xxmc;
	private String jsbh;
	private String jsmc;
	private String bbh;
	private String appxzdz;
	private String dqyxzt;
	private String bz;
	private String cjr;
	private Date cjsj;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
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
	public String getJsbh() {
		return jsbh;
	}
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getBbh() {
		return bbh;
	}
	public void setBbh(String bbh) {
		this.bbh = bbh;
	}
	public String getAppxzdz() {
		return appxzdz;
	}
	public void setAppxzdz(String appxzdz) {
		this.appxzdz = appxzdz;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getDqyxzt() {
		return dqyxzt;
	}
	public void setDqyxzt(String dqyxzt) {
		this.dqyxzt = dqyxzt;
	}
}
