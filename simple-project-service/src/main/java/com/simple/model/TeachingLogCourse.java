package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class TeachingLogCourse implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid          ;    
	private String tanentid        ;    
	private String skbh            ;    
	private String kcbh            ;    
	private String kcmc            ;    
	private String kcxlbh          ;    
	private String kcxlmc          ;    
	private String teacherWenjuan ;    
	private String studentWenjuan ;    
	private String otherWenjuan   ;    
	private Date kksj            ;    
	private int xn              ;    
	private String ktzyWenjuan    ;    
	private String khzyWenjuan    ;
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
	public String getTeacherWenjuan() {
		return teacherWenjuan;
	}
	public void setTeacherWenjuan(String teacherWenjuan) {
		this.teacherWenjuan = teacherWenjuan;
	}
	public String getStudentWenjuan() {
		return studentWenjuan;
	}
	public void setStudentWenjuan(String studentWenjuan) {
		this.studentWenjuan = studentWenjuan;
	}
	public String getOtherWenjuan() {
		return otherWenjuan;
	}
	public void setOtherWenjuan(String otherWenjuan) {
		this.otherWenjuan = otherWenjuan;
	}
	public Date getKksj() {
		return kksj;
	}
	public void setKksj(Date kksj) {
		this.kksj = kksj;
	}
	public int getXn() {
		return xn;
	}
	public void setXn(int xn) {
		this.xn = xn;
	}
	public String getKtzyWenjuan() {
		return ktzyWenjuan;
	}
	public void setKtzyWenjuan(String ktzyWenjuan) {
		this.ktzyWenjuan = ktzyWenjuan;
	}
	public String getKhzyWenjuan() {
		return khzyWenjuan;
	}
	public void setKhzyWenjuan(String khzyWenjuan) {
		this.khzyWenjuan = khzyWenjuan;
	}    
}
