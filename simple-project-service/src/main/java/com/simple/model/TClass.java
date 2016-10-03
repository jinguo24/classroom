package com.simple.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校------班级
 * @author zhengfy1
 *
 */
public class TClass implements Serializable{

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
	private Date cjsj;            
	private String gh;            
	private String xm;            
	private int xn;
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
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
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
}
