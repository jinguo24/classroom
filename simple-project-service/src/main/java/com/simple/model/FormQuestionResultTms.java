package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class FormQuestionResultTms implements Serializable{

	private static final long serialVersionUID = 6376998314287593828L;
	private String code;
	private String cate;
	private int scores;
	private boolean bida;
	private String title;
	private List<FormQuestionResultItems> items;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public boolean isBida() {
		return bida;
	}
	public void setBida(boolean bida) {
		this.bida = bida;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<FormQuestionResultItems> getItems() {
		return items;
	}
	public void setItems(List<FormQuestionResultItems> items) {
		this.items = items;
	}
}
