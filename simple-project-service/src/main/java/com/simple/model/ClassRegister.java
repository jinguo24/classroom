package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class ClassRegister implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid   ;
	private String tanentid ;
	private String xxbh     ;
	private String xxmc     ;
	private String jsbh     ;
	private String jsmc     ;
	private String jsdz     ;
	private String sqzh     ;
	private String sqmm     ;
	private String bz       ;
	private String cjr      ;
	private Timestamp cjsj     ;
	private String showTime;
	private String yxzt     ;
	private String version  ;
	private String xzxm;
	private String glyxx;
	private Date jfrq;
	private String showJfrq="";
	private String email;
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getTanentid() {
		return tanentid;
	}
	public void setTanentid(String tanentid) {
		this.tanentid = tanentid;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getYxzt() {
		return yxzt;
	}
	public void setYxzt(String yxzt) {
		this.yxzt = yxzt;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Timestamp getCjsj() {
		return cjsj;
	}
	public void setCjsj(Timestamp cjsj) {
		this.cjsj = cjsj;
		if ( null != this.cjsj) {
			this.showTime = DateUtil.date2AllString(this.cjsj);
		}
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
	public Date getJfrq() {
		return jfrq;
	}
	public void setJfrq(Date jfrq) {
		this.jfrq = jfrq;
		if ( null != this.jfrq) {
			this.showJfrq = DateUtil.date2AllString(this.jfrq);
		}
	}
	public String getShowJfrq() {
		return showJfrq;
	}
	public void setShowJfrq(String showJfrq) {
		this.showJfrq = showJfrq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ClassRegisterForm castToForm() {
		ClassRegisterForm crf = new ClassRegisterForm();
		crf.setZhh(this.tanentid);
		crf.setXxbh(this.xxbh);
		crf.setXxmc(this.xxmc);
		crf.setJsbh(this.jsbh);
		crf.setJsmc(this.jsmc);
		crf.setJsdz(this.jsdz);
		crf.setSqzh(this.sqzh);
		crf.setBz(this.bz);
		crf.setCstatus(this.yxzt);
		crf.setJsbbh(this.version);
		crf.setXzxm(this.xzxm);
		crf.setGlyxx(this.glyxx);
		if (null != this.jfrq) {
			crf.setJfrq(DateUtil.date2AllString(this.jfrq));
		}
		crf.setEmail(this.email);
		return crf;
	}
}
