package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class FormQuestion implements Serializable{

	private static final long serialVersionUID = 1L;

	private String cate;
	private boolean bida;
	private boolean wtjtt;
	private String name;
	private int scores;
	private int sort;
	private String code;
	private String value;
	private String bz;
	private List<FormQuestionItem> items;
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public boolean isBida() {
		return bida;
	}
	public void setBida(boolean bida) {
		this.bida = bida;
	}
	public boolean isWtjtt() {
		return wtjtt;
	}
	public void setWtjtt(boolean wtjtt) {
		this.wtjtt = wtjtt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FormQuestionItem> getItems() {
		return items;
	}
	public void setItems(List<FormQuestionItem> items) {
		this.items = items;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
}
