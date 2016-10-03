package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class ClassAuthForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date begin;
	private Date end;
	private String tanentId;
	private String proIds;
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getProIds() {
		return proIds;
	}
	public void setProIds(String proIds) {
		this.proIds = proIds;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
}
