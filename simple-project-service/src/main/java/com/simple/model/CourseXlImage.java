package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class CourseXlImage implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid   ;
	private String tanentid ;
	private String xxbh     ;
	private String xxmc     ;
	private String jsbh     ;
	private String jsmc     ;
	private String kcxlbh   ;
	private String kcxlmc   ;
	private String kcxltp   ;
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
	public String getKcxlbh() {
		return kcxlbh;
	}
	public void setKcxlbh(String kcxlbh) {
		this.kcxlbh = kcxlbh;
	}
	public String getKcxlmc() {
		return kcxlmc;
	}
	public void setKcxlmc(String kcxlmc) {
		this.kcxlmc = kcxlmc;
	}
	public String getKcxltp() {
		return kcxltp;
	}
	public void setKcxltp(String kcxltp) {
		this.kcxltp = kcxltp;
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
