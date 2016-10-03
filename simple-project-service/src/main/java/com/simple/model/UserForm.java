package com.simple.model;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.simple.common.util.DateUtil;
import com.simple.common.util.MD5;

public class UserForm implements Serializable{

	private static final long serialVersionUID = -1960405894424681085L;
	private String gh;//工号
	private String cym;//曾用名
	private String xm;//姓名
	private String ywxm;//英文姓名
	private String xmpy;//姓名拼音
	private String sex;//性别
	private String csrq;//出生日期
	private String urylxNum;//人员类别
	private String ugwNum;//职业
	private String placeNum;//籍贯
	private String nationalityNum;//国籍
	private String nationNum;//民族
	private String provincesNum;//省
	private String citysNum;//市
	private String downsNum;//区
	private String idTypeNum;//证件类型
	private String zjhm;//证件号码
	private String maritalStatusNum;//婚姻状况
	private String macaoNum;//港澳台外
	private String zp;//照片
	private String studentId;//更新时接收参数
	private String account;//帐号
	private String password;//密码
	private String email;
	public String getGh() {
		return gh;
	}
	public void setGh(String gh) {
		this.gh = gh;
	}
	public String getCym() {
		return cym;
	}
	public void setCym(String cym) {
		this.cym = cym;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getYwxm() {
		return ywxm;
	}
	public void setYwxm(String ywxm) {
		this.ywxm = ywxm;
	}
	public String getXmpy() {
		return xmpy;
	}
	public void setXmpy(String xmpy) {
		this.xmpy = xmpy;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getPlaceNum() {
		return placeNum;
	}
	public void setPlaceNum(String placeNum) {
		this.placeNum = placeNum;
	}
	public String getNationalityNum() {
		return nationalityNum;
	}
	public void setNationalityNum(String nationalityNum) {
		this.nationalityNum = nationalityNum;
	}
	public String getNationNum() {
		return nationNum;
	}
	public void setNationNum(String nationNum) {
		this.nationNum = nationNum;
	}
	public String getProvincesNum() {
		return provincesNum;
	}
	public void setProvincesNum(String provincesNum) {
		this.provincesNum = provincesNum;
	}
	public String getCitysNum() {
		return citysNum;
	}
	public void setCitysNum(String citysNum) {
		this.citysNum = citysNum;
	}
	public String getDownsNum() {
		return downsNum;
	}
	public void setDownsNum(String downsNum) {
		this.downsNum = downsNum;
	}
	public String getIdTypeNum() {
		return idTypeNum;
	}
	public void setIdTypeNum(String idTypeNum) {
		this.idTypeNum = idTypeNum;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getMaritalStatusNum() {
		return maritalStatusNum;
	}
	public void setMaritalStatusNum(String maritalStatusNum) {
		this.maritalStatusNum = maritalStatusNum;
	}
	public String getMacaoNum() {
		return macaoNum;
	}
	public void setMacaoNum(String macaoNum) {
		this.macaoNum = macaoNum;
	}
	public String getZp() {
		return zp;
	}
	public void setZp(String zp) {
		this.zp = zp;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getUrylxNum() {
		return urylxNum;
	}
	public void setUrylxNum(String urylxNum) {
		this.urylxNum = urylxNum;
	}
	public String getUgwNum() {
		return ugwNum;
	}
	public void setUgwNum(String ugwNum) {
		this.ugwNum = ugwNum;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SysUser toSysUser() {
		SysUser su = new SysUser();
		su.setStudentId(this.gh);
		su.setEverName(this.cym);
		su.setName(this.xm);
		su.setEnglishName(this.ywxm);
		su.setPinyinName(this.xmpy);
		su.setSexCode(this.sex);
		try {
			su.setBirthday(DateUtil.stringToDate(this.csrq));
		}catch(Exception e) {
		}
		su.setHometownCode(this.placeNum);
		su.setCountryCode(this.nationalityNum);
		su.setNationCode(this.nationNum);
		su.setProvince(this.provincesNum);
		su.setCity(this.citysNum);
		su.setArea(this.downsNum);
		su.setIdCardTypeCode(this.idTypeNum);
		su.setIdCardNumber(this.zjhm);
		su.setMarrayCode(this.maritalStatusNum);
		su.setGotqwCode(this.macaoNum);
		su.setPhoto(this.zp);
		su.setType(this.urylxNum);
		su.setPost(this.ugwNum);
		su.setAccount(this.account);
		if (!StringUtils.isEmpty(this.password)) {
			su.setPassword(MD5.stringMD5(this.password));
		}
		su.setEmail(this.email);
		return su;
	}
	
}
