package com.simple.model;

import java.io.Serializable;
import java.util.List;
import com.alibaba.fastjson.JSON;
public class TemplateData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String title;
	private String code;
	private int sort;
	private List<FormQuestion> tms;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FormQuestion> getTms() {
		return tms;
	}

	public void setTms(List<FormQuestion> tms) {
		this.tms = tms;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public static void main(String[] args) {
		String s = "{tag:1,sort:1,content:\"第一章节的描述信息\"}";
		System.out.println(JSON.parseObject(s,TemplateData.class).getTitle());
	}
	
}
