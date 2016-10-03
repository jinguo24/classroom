package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class CourseImage implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid   ;
	private String tanentid ;
	private String xxbh     ;
	private String xxmc     ;
	private String jsbh     ;
	private String jsmc     ;
	private String kcbh     ;
	private String kcmc     ;
	private String kctp     ;
	private String cjr      ;
	private Date cjsj     ;
	public String getTanentid() {
		return tanentid;
	}
	public void setTanentid(String tanentid) {
		this.tanentid = tanentid;
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
	public String getKcbh() {
		return kcbh;
	}
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		this.kctp = kctp;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
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
}
