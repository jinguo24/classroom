package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class CourseWenjuanTeacher implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineId;
	private String gh;
	private String xm;
	private String teacherWenjuan;
	private String teacherWenjuanName;
	private String studentWenjuan;
	private String studentWenjuanName;
	private String ktzyWenjuan;
	private String ktzyWenjuanName;
	private String khzyWenjuan;
	private String khzyWenjuanName;
	private String otherWenjuan;
	private String otherWenjuanName;
	private String kcbh;
	private Date begin;
	private String showBegin;
	private Date end;
	private String showEnd;
	private String tanentId;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
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
	public String getOtherWenjuan() {
		return otherWenjuan;
	}
	public void setOtherWenjuan(String otherWenjuan) {
		this.otherWenjuan = otherWenjuan;
	}
	public String getKcbh() {
		return kcbh;
	}
	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
		if ( null != this.begin) {
			this.showBegin = DateUtil.date2String(this.begin);
		}
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
		if ( null != this.end) {
			this.showEnd = DateUtil.date2String(this.end);
		}
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public String getShowBegin() {
		return showBegin;
	}
	public void setShowBegin(String showBegin) {
		this.showBegin = showBegin;
	}
	public String getShowEnd() {
		return showEnd;
	}
	public void setShowEnd(String showEnd) {
		this.showEnd = showEnd;
	}
	public String getTeacherWenjuanName() {
		return teacherWenjuanName;
	}
	public void setTeacherWenjuanName(String teacherWenjuanName) {
		this.teacherWenjuanName = teacherWenjuanName;
	}
	public String getStudentWenjuanName() {
		return studentWenjuanName;
	}
	public void setStudentWenjuanName(String studentWenjuanName) {
		this.studentWenjuanName = studentWenjuanName;
	}
	public String getKtzyWenjuanName() {
		return ktzyWenjuanName;
	}
	public void setKtzyWenjuanName(String ktzyWenjuanName) {
		this.ktzyWenjuanName = ktzyWenjuanName;
	}
	public String getKhzyWenjuanName() {
		return khzyWenjuanName;
	}
	public void setKhzyWenjuanName(String khzyWenjuanName) {
		this.khzyWenjuanName = khzyWenjuanName;
	}
	public String getOtherWenjuanName() {
		return otherWenjuanName;
	}
	public void setOtherWenjuanName(String otherWenjuanName) {
		this.otherWenjuanName = otherWenjuanName;
	}
}
