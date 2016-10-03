package com.simple.model;

import java.io.Serializable;
import java.util.Date;

public class AppLog implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineId;
	private String tanentId;
	private String logContent;
	private Date createTime;
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
