package com.simple.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class CourseForm implements Serializable{

	private static final long serialVersionUID = 1L;

	private String proCate;//商品类别
	private String jfNum;//教辅编号
	private String syjsbbh;//适用教室版本号
	private String kcxlsNum;//课程系列
	private String kcxlmc;
	private String childKcxlNum;//字课程系列分类
	private String childKcxlmc;
	private String ddbbh;//当前版本号
	private String spbh;//商品编号
	private String ssmc;//商品名称
	private String proStatus;//商品状态
	private int spkc;//商品库存
	private String spjj;//商品简介
	private String spnr;//商品内容
	private String proImg;//商品缩略图
	private String keVideo;//课件视频
	private String tmpId1;//教师对课程评价问卷ID
	private String tmpName1;//教师对课程评价问卷名称
	private String tmpId2;//学生对课程评价问卷ID
	private String tmpName2;//学生对课程评价问卷名称
	private String tmpId3;//其他对课程评价问卷ID
	private String tmpName3;//其他对课程评价问卷名称
	private String tmpId4;//课堂作业问卷ID
	private String tmpName4;//课堂作业问卷名称
	private String tmpId5;//课后作业问卷ID
	private String tmpName5;//课后作业问卷名称
	private String kcbz;//课程标准
	private String jiaoan;//教案
	private String kcjc;//课程教材
	private String dmtkj;//多媒体课件
	private String jjsysmjqd;//教具使用说明及清单
	private String kcsc;//课程素材
	private String tiku;//题库
	private String code;
	private String from="森霖木";
	private String njbh;//年级编号
	public String getChildKcxlNum() {
		return childKcxlNum;
	}
	public void setChildKcxlNum(String childKcxlNum) {
		this.childKcxlNum = childKcxlNum;
	}
	public String getChildKcxlmc() {
		return childKcxlmc;
	}
	public void setChildKcxlmc(String childKcxlmc) {
		this.childKcxlmc = childKcxlmc;
	}
	public String getProCate() {
		return proCate;
	}
	public void setProCate(String proCate) {
		this.proCate = proCate;
	}
	public String getJfNum() {
		return jfNum;
	}
	public void setJfNum(String jfNum) {
		this.jfNum = jfNum;
	}
	public String getSyjsbbh() {
		return syjsbbh;
	}
	public void setSyjsbbh(String syjsbbh) {
		this.syjsbbh = syjsbbh;
	}
	public String getKcxlsNum() {
		return kcxlsNum;
	}
	public void setKcxlsNum(String kcxlsNum) {
		this.kcxlsNum = kcxlsNum;
	}
	public String getDdbbh() {
		return ddbbh;
	}
	public void setDdbbh(String ddbbh) {
		this.ddbbh = ddbbh;
	}
	public String getSpbh() {
		return spbh;
	}
	public void setSpbh(String spbh) {
		this.spbh = spbh;
	}
	public String getSsmc() {
		return ssmc;
	}
	public void setSsmc(String ssmc) {
		this.ssmc = ssmc;
	}
	public String getProStatus() {
		return proStatus;
	}
	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	public int getSpkc() {
		return spkc;
	}
	public void setSpkc(int spkc) {
		this.spkc = spkc;
	}
	public String getSpjj() {
		return spjj;
	}
	public void setSpjj(String spjj) {
		this.spjj = spjj;
	}
	public String getSpnr() {
		return spnr;
	}
	public void setSpnr(String spnr) {
		this.spnr = spnr;
	}
	public String getProImg() {
		return proImg;
	}
	public void setProImg(String proImg) {
		this.proImg = proImg;
	}
	public String getKeVideo() {
		return keVideo;
	}
	public void setKeVideo(String keVideo) {
		this.keVideo = keVideo;
	}
	public String getTmpId1() {
		return tmpId1;
	}
	public void setTmpId1(String tmpId1) {
		this.tmpId1 = tmpId1;
	}
	public String getTmpName1() {
		return tmpName1;
	}
	public void setTmpName1(String tmpName1) {
		this.tmpName1 = tmpName1;
	}
	public String getTmpId2() {
		return tmpId2;
	}
	public void setTmpId2(String tmpId2) {
		this.tmpId2 = tmpId2;
	}
	public String getTmpName2() {
		return tmpName2;
	}
	public void setTmpName2(String tmpName2) {
		this.tmpName2 = tmpName2;
	}
	public String getTmpId3() {
		return tmpId3;
	}
	public void setTmpId3(String tmpId3) {
		this.tmpId3 = tmpId3;
	}
	public String getTmpName3() {
		return tmpName3;
	}
	public void setTmpName3(String tmpName3) {
		this.tmpName3 = tmpName3;
	}
	public String getTmpId4() {
		return tmpId4;
	}
	public void setTmpId4(String tmpId4) {
		this.tmpId4 = tmpId4;
	}
	public String getTmpName4() {
		return tmpName4;
	}
	public void setTmpName4(String tmpName4) {
		this.tmpName4 = tmpName4;
	}
	public String getTmpId5() {
		return tmpId5;
	}
	public void setTmpId5(String tmpId5) {
		this.tmpId5 = tmpId5;
	}
	public String getTmpName5() {
		return tmpName5;
	}
	public void setTmpName5(String tmpName5) {
		this.tmpName5 = tmpName5;
	}
	public String getKcxlmc() {
		return kcxlmc;
	}
	public void setKcxlmc(String kcxlmc) {
		this.kcxlmc = kcxlmc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getKcbz() {
		return kcbz;
	}
	public void setKcbz(String kcbz) {
		this.kcbz = kcbz;
	}
	public String getJiaoan() {
		return jiaoan;
	}
	public void setJiaoan(String jiaoan) {
		this.jiaoan = jiaoan;
	}
	public String getKcjc() {
		return kcjc;
	}
	public void setKcjc(String kcjc) {
		this.kcjc = kcjc;
	}
	public String getDmtkj() {
		return dmtkj;
	}
	public void setDmtkj(String dmtkj) {
		this.dmtkj = dmtkj;
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
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getNjbh() {
		return njbh;
	}
	public void setNjbh(String njbh) {
		this.njbh = njbh;
	}
	public Course castToCourse() {
		Course c = new Course();
		c.setSplb(this.proCate);
		c.setCode(this.spbh);
		c.setName(StringUtils.trimToNull(this.ssmc));
		c.setKcxlbh(this.kcxlsNum);
		c.setKcxlmc(this.kcxlmc);
		c.setTeacherWenjuan(this.tmpId1);
		c.setStudentWenjuan(this.tmpId2);
		c.setOtherWenjuan(this.tmpId3);
		c.setKtzyWenjuan(this.tmpId4);
		c.setKhzyWenjuan(this.tmpId5);
		c.setVersion(this.ddbbh);
		c.setSyjsWersion(this.syjsbbh);
		c.setKcjj(this.spjj);
		c.setKcnr(this.spnr);
		c.setSpkc(this.spkc);
		c.setKctp(this.proImg);
		c.setKjdz(this.keVideo);
		c.setKcbz(this.kcbz);
		c.setKcjc(this.kcjc);
		c.setBz(null);
		c.setYxzt(this.proStatus);
		c.setDmtkzdz(this.dmtkj);
		c.setTiku(this.tiku);
		c.setKcsc(this.kcsc);
		c.setJjsysmjqd(this.jjsysmjqd);
		c.setJiaoan(this.jiaoan);
		c.setNjbh(this.njbh);
		c.setChildKcxlbh(this.childKcxlNum);
		c.setChildKcxlmc(this.childKcxlmc);
		return c;
	}
	
}
