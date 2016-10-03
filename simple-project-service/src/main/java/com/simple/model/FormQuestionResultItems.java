package com.simple.model;

import java.io.Serializable;

public class FormQuestionResultItems implements Serializable{

	private static final long serialVersionUID = -8776793258165447021L;

	private String chooseCode;
	private String title;
	public String getChooseCode() {
		return chooseCode;
	}
	public void setChooseCode(String chooseCode) {
		this.chooseCode = chooseCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
