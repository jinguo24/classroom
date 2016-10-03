package com.simple.model;

import java.io.Serializable;
import java.util.Date;
public class CourseXl implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid         ;
	private String kcxlbh         ;
	private String kcxlmc         ;
	private String kctp           ;
	private String bz           ;
	private String cjr;
	private Date cjsj;
	private String parentbh;
	
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
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
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		this.kctp = kctp;
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
	public String getParentbh() {
		return parentbh;
	}
	public void setParentbh(String parentbh) {
		this.parentbh = parentbh;
	}
}
