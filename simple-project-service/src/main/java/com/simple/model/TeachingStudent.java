package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class TeachingStudent implements Serializable{

	private static final long serialVersionUID = 1L;
	private int lineid   ;
	private String tanentid ;
	private String skbh     ;
	private String bjbh     ;
	private String bjmc     ;
	private String gh       ;
	private String xm       ;
	private String cjr      ;
	private Date cjsj     ;
	private String bz       ;
	private int xn       ;
	private String njmc;
	private String njbh;
	
	public String getTanentid() {
		return tanentid;
	}
	public void setTanentid(String tanentid) {
		this.tanentid = tanentid;
	}
	public String getSkbh() {
		return skbh;
	}
	public void setSkbh(String skbh) {
		this.skbh = skbh;
	}
	public String getBjbh() {
		return bjbh;
	}
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public int getXn() {
		return xn;
	}
	public void setXn(int xn) {
		this.xn = xn;
	}
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
