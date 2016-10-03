package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.simple.common.util.DateUtil;
import com.simple.common.util.MD5;
import com.simple.model.ClassRegister;

public class ClassRegisterForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String zhh;
	private String xxbh;
	private String xxmc;
	private String jsbh;
	private String jsmc;
	private String jsdz;
	private String sqzh;
	private String sqmm;
	private String jsbbh;
	private String cstatus;
	private String bz;
	private String b1Src;
	private String b2Src;
	private String b3Src;
	private String b4Src;
	private String b5Src;
	private String b6Src;
	private String b7Src;
	private String b8Src;
	private String b9Src;
	private String b10Src;
	private String b11Src;
	private String b12Src;
	private String tanentId;
	private String oldJsbh;
	private String xzxm ;
	private String glyxx ;
	private String jfrq;
	private String email;
	public String getZhh() {
		return zhh;
	}
	public void setZhh(String zhh) {
		this.zhh = zhh;
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
	public String getJsbh() {
		return jsbh;
	}
	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getJsdz() {
		return jsdz;
	}
	public void setJsdz(String jsdz) {
		this.jsdz = jsdz;
	}
	public String getSqzh() {
		return sqzh;
	}
	public void setSqzh(String sqzh) {
		this.sqzh = sqzh;
	}
	public String getSqmm() {
		return sqmm;
	}
	public void setSqmm(String sqmm) {
		this.sqmm = sqmm;
	}
	public String getJsbbh() {
		return jsbbh;
	}
	public void setJsbbh(String jsbbh) {
		this.jsbbh = jsbbh;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getB1Src() {
		return b1Src;
	}
	public void setB1Src(String b1Src) {
		this.b1Src = b1Src;
	}
	public String getB2Src() {
		return b2Src;
	}
	public void setB2Src(String b2Src) {
		this.b2Src = b2Src;
	}
	public String getB3Src() {
		return b3Src;
	}
	public void setB3Src(String b3Src) {
		this.b3Src = b3Src;
	}
	public String getB4Src() {
		return b4Src;
	}
	public void setB4Src(String b4Src) {
		this.b4Src = b4Src;
	}
	public String getB5Src() {
		return b5Src;
	}
	public void setB5Src(String b5Src) {
		this.b5Src = b5Src;
	}
	public String getB6Src() {
		return b6Src;
	}
	public void setB6Src(String b6Src) {
		this.b6Src = b6Src;
	}
	public String getB7Src() {
		return b7Src;
	}
	public void setB7Src(String b7Src) {
		this.b7Src = b7Src;
	}
	public String getB8Src() {
		return b8Src;
	}
	public void setB8Src(String b8Src) {
		this.b8Src = b8Src;
	}
	public String getB9Src() {
		return b9Src;
	}
	public void setB9Src(String b9Src) {
		this.b9Src = b9Src;
	}
	public String getB10Src() {
		return b10Src;
	}
	public void setB10Src(String b10Src) {
		this.b10Src = b10Src;
	}
	public String getB11Src() {
		return b11Src;
	}
	public void setB11Src(String b11Src) {
		this.b11Src = b11Src;
	}
	public String getB12Src() {
		return b12Src;
	}
	public void setB12Src(String b12Src) {
		this.b12Src = b12Src;
	}
	public String getTanentId() {
		return tanentId;
	}
	public void setTanentId(String tanentId) {
		this.tanentId = tanentId;
	}
	public String getOldJsbh() {
		return oldJsbh;
	}
	public void setOldJsbh(String oldJsbh) {
		this.oldJsbh = oldJsbh;
	}
	public String getXzxm() {
		return xzxm;
	}
	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}
	public String getGlyxx() {
		return glyxx;
	}
	public void setGlyxx(String glyxx) {
		this.glyxx = glyxx;
	}
	public String getJfrq() {
		return jfrq;
	}
	public void setJfrq(String jfrq) {
		this.jfrq = jfrq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ClassRegister castToRegister() {
		ClassRegister cr = new ClassRegister();
		cr.setTanentid(this.zhh);
		cr.setXxbh(this.xxbh);
		cr.setXxmc(StringUtils.trimToNull(this.xxmc));
		cr.setJsbh(this.jsbh);
		cr.setJsmc(StringUtils.trimToNull(this.jsmc));
		cr.setJsdz(StringUtils.trimToNull(this.jsdz));
		cr.setSqzh(this.sqzh);
		if ( null != this.sqmm) {
			cr.setSqmm(MD5.stringMD5(this.sqmm));
		}
		cr.setBz(StringUtils.trimToNull(this.bz));
		cr.setYxzt(this.cstatus);
		cr.setVersion(StringUtils.trimToNull(this.jsbbh));
		cr.setXzxm(this.xzxm);
		cr.setGlyxx(this.glyxx);
		if (!StringUtils.isEmpty(this.jfrq)) {
			cr.setJfrq(DateUtil.stringToDate(this.jfrq));
		}
		cr.setEmail(this.email);
		return cr;
	}
}
