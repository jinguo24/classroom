package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class QuestionResultForm implements Serializable{

	private static final long serialVersionUID = 6035538459902073611L;
	private String code;
	private String title;
	private List<ResultForm> tms;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ResultForm> getTms() {
		return tms;
	}
	public void setTms(List<ResultForm> tms) {
		this.tms = tms;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public class ResultForm {
		private String code;
		private String cate;
		private int scores;
		private boolean bida;
		private String title;
		private List<QuestionItemResultForm> items;
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public List<QuestionItemResultForm> getItems() {
			return items;
		}
		public void setItems(List<QuestionItemResultForm> items) {
			this.items = items;
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
	}
	
	public class QuestionItemResultForm {
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
}
