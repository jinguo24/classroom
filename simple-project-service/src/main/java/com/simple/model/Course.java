package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class Course implements Serializable{

	private static final long serialVersionUID = 1L;

	private int lineid         ;
	private String code           ;
	private String name           ;
	private String njbh;
	private String kcxlbh         ;
	private String kcxlmc         ;
	private String childKcxlbh    ;
	private String childKcxlmc    ;
	private String kcjj           ;
	private String kcnr           ;
	private String kctp           ;
	private String kjdz           ;
	private String teacherWenjuan ;
	private String studentWenjuan ;
	private String otherWenjuan   ;
	private String kcbz           ;
	private String kcjc           ;
	private String ktzyWenjuan    ;
	private String khzyWenjuan    ;
	private String version        ;
	private String syjsWersion    ;
	private String bz             ;
	private String cjr            ;
	private Date cjsj           ;
	private String showTime;
	private String yxzt           ;
	private String splb           ;
	private int spkc           ;
	private String dmtkzdz;
	private String jiaoan;
	private String jjsysmjqd;
	private String kcsc;
	private String tiku;
	public int getLineid() {
		return lineid;
	}
	public void setLineid(int lineid) {
		this.lineid = lineid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getKcjj() {
		return kcjj;
	}
	public void setKcjj(String kcjj) {
		this.kcjj = kcjj;
	}
	public String getKcnr() {
		return kcnr;
	}
	public void setKcnr(String kcnr) {
		this.kcnr = kcnr;
	}
	public String getKctp() {
		return kctp;
	}
	public void setKctp(String kctp) {
		this.kctp = kctp;
	}
	public String getKjdz() {
		return kjdz;
	}
	public void setKjdz(String kjdz) {
		this.kjdz = kjdz;
	}
	public String getTeacherWenjuan() {
		return teacherWenjuan;
	}
	public void setTeacherWenjuan(String teacherWenjuan) {
		this.teacherWenjuan = teacherWenjuan;
	}
	public String getStudentWenjuan() {
		return studentWenjuan;
	}
	public void setStudentWenjuan(String studentWenjuan) {
		this.studentWenjuan = studentWenjuan;
	}
	public String getOtherWenjuan() {
		return otherWenjuan;
	}
	public void setOtherWenjuan(String otherWenjuan) {
		this.otherWenjuan = otherWenjuan;
	}
	public String getKcbz() {
		return kcbz;
	}
	public void setKcbz(String kcbz) {
		this.kcbz = kcbz;
	}
	public String getKcjc() {
		return kcjc;
	}
	public void setKcjc(String kcjc) {
		this.kcjc = kcjc;
	}
	public String getKtzyWenjuan() {
		return ktzyWenjuan;
	}
	public void setKtzyWenjuan(String ktzyWenjuan) {
		this.ktzyWenjuan = ktzyWenjuan;
	}
	public String getKhzyWenjuan() {
		return khzyWenjuan;
	}
	public void setKhzyWenjuan(String khzyWenjuan) {
		this.khzyWenjuan = khzyWenjuan;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSyjsWersion() {
		return syjsWersion;
	}
	public void setSyjsWersion(String syjsWersion) {
		this.syjsWersion = syjsWersion;
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
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
		if ( null != cjsj) {
			this.showTime = DateUtil.date2AllString(this.cjsj);
		}
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
	public String getSplb() {
		return splb;
	}
	public void setSplb(String splb) {
		this.splb = splb;
	}
	public int getSpkc() {
		return spkc;
	}
	public void setSpkc(int spkc) {
		this.spkc = spkc;
	}
	public String getDmtkzdz() {
		return dmtkzdz;
	}
	public void setDmtkzdz(String dmtkzdz) {
		this.dmtkzdz = dmtkzdz;
	}
	public String getJiaoan() {
		return jiaoan;
	}
	public void setJiaoan(String jiaoan) {
		this.jiaoan = jiaoan;
	}
	public String getJjsysmjqd() {
		return jjsysmjqd;
	}
	public void setJjsysmjqd(String jjsysmjqd) {
		this.jjsysmjqd = jjsysmjqd;
	}
	public String getKcsc() {
		return kcsc;
	}
	public void setKcsc(String kcsc) {
		this.kcsc = kcsc;
	}
	public String getTiku() {
		return tiku;
	}
	public void setTiku(String tiku) {
		this.tiku = tiku;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
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
	public CourseForm castToForm() {
		CourseForm cf = new CourseForm();
		cf.setCode(this.code);
		cf.setProCate(this.splb);
		//cf.setJfNum(this.);
		cf.setSyjsbbh(this.syjsWersion);
		cf.setKcxlsNum(this.kcxlbh);
		cf.setKcxlmc(this.kcxlmc);
		cf.setDdbbh(this.version);
		cf.setSpbh(this.code);
		cf.setSsmc(this.name);
		cf.setProStatus(this.yxzt);
		cf.setSpkc(this.spkc);
		cf.setSpjj(this.kcjj);
		cf.setSpnr(this.kcnr);
		cf.setProImg(this.kctp);
		cf.setKeVideo(this.kjdz);
		cf.setTmpId1(this.teacherWenjuan);
		cf.setTmpId2(this.studentWenjuan);
		cf.setTmpId3(this.otherWenjuan);
		cf.setTmpId4(this.ktzyWenjuan);
		cf.setTmpId5(this.khzyWenjuan);
		cf.setKcbz(this.kcbz);
		cf.setJiaoan(this.jiaoan);
		cf.setKcjc(this.kcjc);
		cf.setDmtkj(this.dmtkzdz);
		cf.setJjsysmjqd(this.jjsysmjqd);
		cf.setKcsc(this.kcsc);
		cf.setTiku(this.tiku);
		cf.setNjbh(this.njbh);
		cf.setChildKcxlNum(this.childKcxlbh);
		cf.setChildKcxlmc(this.childKcxlmc);
		return cf;
	}
}
