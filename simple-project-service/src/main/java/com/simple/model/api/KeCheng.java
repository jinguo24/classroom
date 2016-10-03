package com.simple.model.api;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.simple.common.config.EnvPropertiesConfiger;

public class KeCheng implements Serializable{

	private static final long serialVersionUID = 1L;

	private String kctp="";
	private String name="";
	private String kcjj="";
	private String code="";
	private String kcxlbh="";
	private String kcxlmc = "";
	private String splb = "";
	private String begin="";
	private String end="";
	private String childKcxlbh = "";
	private String childKcxlmc = "";
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		if (!StringUtils.isEmpty(kctp)) {
			this.kctp = EnvPropertiesConfiger.getValue("fileDomain")+kctp;
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKcjj() {
		return kcjj;
	}
	public void setKcjj(String kcjj) {
		this.kcjj = kcjj;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKcxlbh() {
		return kcxlbh;
	}
	public void setKcxlbh(String kcxlbh) {
		this.kcxlbh = kcxlbh;
	}
	public String getKcxlmc() {
		return kcxlmc;
	}
	public void setKcxlmc(String kcxlmc) {
		this.kcxlmc = kcxlmc;
	}
	public String getSplb() {
		return splb;
	}
	public void setSplb(String splb) {
		this.splb = splb;
	}
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getChildKcxlbh() {
		return childKcxlbh;
	}
	public void setChildKcxlbh(String childKcxlbh) {
		this.childKcxlbh = childKcxlbh;
	}
	public String getChildKcxlmc() {
		return childKcxlmc;
	}
	public void setChildKcxlmc(String childKcxlmc) {
		this.childKcxlmc = childKcxlmc;
	}
}
