package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class FormQuestionResult implements Serializable{

	private static final long serialVersionUID = 1L;

	private String code;
	private String title;
	private List<FormQuestionResultTms> tms;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<FormQuestionResultTms> getTms() {
		return tms;
	}
	public void setTms(List<FormQuestionResultTms> tms) {
		this.tms = tms;
	}
}
