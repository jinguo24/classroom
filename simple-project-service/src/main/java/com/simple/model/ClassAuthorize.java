package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class ClassAuthorize implements Serializable{

	private static final long serialVersionUID = 1L;
	private int lineid   ;
	private String tanentid ;
	private String jsbh     ;
	private String jsmc     ;
	private String sqzh     ;
	private String bz       ;
	private String cjr      ;
	private Date cjsj     ;
	private String shwoCjsj;
	private String kcbh     ;
	private String kcmc     ;
	private Date gmrq     ;
	private String showGmrq;
	private Date sqkssj   ;
	private String showSqkssj;
	private Date sqjssj   ;
	private String showSqjssj;
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
	public String getSqzh() {
		return sqzh;
	}
	public void setSqzh(String sqzh) {
		this.sqzh = sqzh;
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
	public Date getGmrq() {
		return gmrq;
	}
	public void setGmrq(Date gmrq) {
		this.gmrq = gmrq;
		if ( null != this.gmrq) {
			this.showGmrq = DateUtil.date2AllString(this.gmrq);
		}
	}
	public Date getSqkssj() {
		return sqkssj;
	}
	public void setSqkssj(Date sqkssj) {
		this.sqkssj = sqkssj;
		if ( null != this.sqkssj) {
			this.showSqkssj = DateUtil.date2AllString(this.sqkssj);
		}
	}
	public Date getSqjssj() {
		return sqjssj;
	}
	public void setSqjssj(Date sqjssj) {
		this.sqjssj = sqjssj;
		if ( null != this.sqjssj) {
			this.showSqjssj = DateUtil.date2AllString(this.sqjssj);
		}
	}
	public String getShwoCjsj() {
		return shwoCjsj;
	}
	public void setShwoCjsj(String shwoCjsj) {
		this.shwoCjsj = shwoCjsj;
	}
	public String getShowGmrq() {
		return showGmrq;
	}
	public void setShowGmrq(String showGmrq) {
		this.showGmrq = showGmrq;
	}
	public String getShowSqkssj() {
		return showSqkssj;
	}
	public void setShowSqkssj(String showSqkssj) {
		this.showSqkssj = showSqkssj;
	}
	public String getShowSqjssj() {
		return showSqjssj;
	}
	public void setShowSqjssj(String showSqjssj) {
		this.showSqjssj = showSqjssj;
	}
}
