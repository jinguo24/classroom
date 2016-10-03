package com.simple.model;

import java.io.Serializable;
import java.util.List;

public class TemplateForm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String templateCategory;
	private String templateType;
	private String title;
	private String random;
	private String content;
	private String code;
	private String skbh;
	private List<TemplateData> data;
	public String getTemplateCategory() {
		return templateCategory;
	}
	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public List<TemplateData> getData() {
		return data;
	}
	public void setData(List<TemplateData> data) {
		this.data = data;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSkbh() {
		return skbh;
	}
	public void setSkbh(String skbh) {
		this.skbh = skbh;
	}
}
