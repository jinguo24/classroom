package com.simple.model;

import java.io.Serializable;

public class FormQuestionItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;//名称
	private boolean bz;//是否
	private int sort;//排序
	private int number;
	private String title;
	private String code;
	private String percent;//百分比
	private int pcount;//答题人数
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isBz() {
		return bz;
	}
	public void setBz(boolean bz) {
		this.bz = bz;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public int getPcount() {
		return pcount;
	}
	public void setPcount(int pcount) {
		this.pcount = pcount;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
