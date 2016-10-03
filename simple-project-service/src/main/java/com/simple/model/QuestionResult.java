package com.simple.model;

import java.io.Serializable;
public class QuestionResult implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String leaseholderId;
	private String studentId;
	private String code;
	private String activityCode;
	private String activityName;
	private String tempalteCode;
	private String templateName;
	private String sectionCode;
	private String questionCode;
	private String questionItemCode;
	private String content;
	private int score;
	private String skbh;
	private String xxbh;
	private String xxmc;
	private String njbh;
	private String njmc;
	private String bjbh;
	private String bjmc;
	private int xn;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getActivityCode() {
		return activityCode;
	}
	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getQuestionItemCode() {
		return questionItemCode;
	}
	public void setQuestionItemCode(String questionItemCode) {
		this.questionItemCode = questionItemCode;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getSkbh() {
		return skbh;
	}
	public void setSkbh(String skbh) {
		this.skbh = skbh;
	}
	public String getXxbh() {
		return xxbh;
	}
	public void setXxbh(String xxbh) {
		this.xxbh = xxbh;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}
	public String getNjmc() {
		return njmc;
	}
	public void setNjmc(String njmc) {
		this.njmc = njmc;
	}
	public String getBjbh() {
		return bjbh;
	}
	public void setBjbh(String bjbh) {
		this.bjbh = bjbh;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public int getXn() {
		return xn;
	}
	public void setXn(int xn) {
		this.xn = xn;
	}
}
