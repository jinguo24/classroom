package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class TClassStudent implements Serializable{

	private static final long serialVersionUID = 1L;
	private int lineid;           
	private String tanentid;      
	private String xxhb;          
	private String xxmc;          
	private String njbh;          
	private String njmc;          
	private String bjbh;          
	private String bjmc;          
	private String cjr;           
	private Timestamp cjsj;  
	private String showTime;
	private String gh;            
	private String xm;            
	private int xn;
	private String bz;
	private int sign;
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getTanentid() {
		return tanentid;
	}
	public void setTanentid(String tanentid) {
		this.tanentid = tanentid;
	}
	public String getXxhb() {
		return xxhb;
	}
	public void setXxhb(String xxhb) {
		this.xxhb = xxhb;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}
	public String getNjmc() {
		return njmc;
	}
	public void setNjmc(String njmc) {
		this.njmc = njmc;
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
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
		if (null != this.cjsj) {
			this.showTime = DateUtil.date2AllString(this.cjsj);
		}
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
	public int getXn() {
		return xn;
	}
	public void setXn(int xn) {
		this.xn = xn;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	} 
}
