package com.simple.model;

import java.io.Serializable;
public class QuestionItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String leaseholderId;
	private String tempalteCode;
	private String sectionCode;
	private String questionCode;
	private String code;
	private int sort;
	private String mark;
	private String description;
	private int score;
	private String content;
	private String image;
	private String tip;
	private int isAnswer;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(int isAnswer) {
		this.isAnswer = isAnswer;
	}
}
