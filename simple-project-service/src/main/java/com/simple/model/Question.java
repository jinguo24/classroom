package com.simple.model;

import java.io.Serializable;
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String leaseholderId;
	private String tempalteCode;
	private String sectionCode;
	private String code;
	private int sort;
	private String title;
	private String type;
	private int score;
	private int require;
	private int ignore;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLeaseholderId() {
		return leaseholderId;
	}
	public void setLeaseholderId(String leaseholderId) {
		this.leaseholderId = leaseholderId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTempalteCode() {
		return tempalteCode;
	}
	public void setTempalteCode(String tempalteCode) {
		this.tempalteCode = tempalteCode;
	}
	public String getSectionCode() {
		return sectionCode;
	}
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRequire() {
		return require;
	}
	public void setRequire(int require) {
		this.require = require;
	}
	public int getIgnore() {
		return ignore;
	}
	public void setIgnore(int ignore) {
		this.ignore = ignore;
	}
}
