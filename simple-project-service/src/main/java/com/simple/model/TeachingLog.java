package com.simple.model;

import java.io.Serializable;
import java.util.Date;
import com.simple.common.util.DateUtil;
public class TeachingLog implements Serializable{

	private static final long serialVersionUID = 1L;
	private int lineid                ; 
	private String tanentid              ; 
	private String skbh                  ; 
	private Integer xn                    ; 
	private String njbh                  ; 
	private String njmc                  ; 
	private String bjbh                  ; 
	private String bjmc                  ; 
	private String xm                    ; 
	private Date sksj                  ; 
	private String showTime;
	private String xxbh                   ; 
	private String xxmc                  ; 
	private String gh                    ;
	private int stduentCount;
	private String kcxlbh;
	private String kcxlmc;
	private String kcbh;
	private String kcmc;
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
	public String getSkbh() {
		return skbh;
	}
	public void setSkbh(String skbh) {
		this.skbh = skbh;
	}
	public Integer getXn() {
		return xn;
	}
	public void setXn(Integer xn) {
		this.xn = xn;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public Date getSksj() {
		return sksj;
	}
	public void setSksj(Date sksj) {
		this.sksj = sksj;
		if ( null != sksj) {
			this.showTime = DateUtil.date2AllString(sksj);
		}
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
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public int getStduentCount() {
		return stduentCount;
	}
	public void setStduentCount(int stduentCount) {
		this.stduentCount = stduentCount;
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
}
