package com.simple.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.simple.common.util.DateUtil;

public class SysUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String leaseholderId;
	private String studentId;
	private String name;
	private String englishName;
	private String pinyinName;
	private String everName;
	private String sexCode;
	private String sexName;
	private Date birthday;
	private String bornAddressCode;
	private String hometownCode;
	private String nationCode;
	private String countryCode;
	private String idCardTypeCode;
	private String idCardNumber;
	private String marrayCode;
	private String gotqwCode;
	private String policyCode;
	private String healthCode;
	private String faithCode;
	private String bloodCode;
	private String photo;
	private int idCardValidity;
	private String singleCode;
	private String type;
	private String typeName;
	private String zipCode;
	private String address;
	private String phone;
	private String mobile;
	private String fax;
	private String email;
	private String internetAddress;
	private String chatNo;
	private String weixin;
	private String qq;
	private Timestamp createTime;
	private String showCreateTime;
	private String openId;
	private String post;
	private String postName;
	private String password;
	private String province;
	private String city;
	private String area;
	private String creator;
	private Timestamp updateTime;
	private String showUpdateTime;
	private String updator;
	private String account;
	private String xxbh = "";//租户登录的时候查询用
	private String xxmc = "";//租户登录的时候查询用
	private String loginType = "";
	private String loginAccount= "";//登录帐号
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLeaseholderId() {
		return leaseholderId;
	}
	public void setLeaseholderId(String leaseholderId) {
		this.leaseholderId = leaseholderId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getPinyinName() {
		return pinyinName;
	}
	public void setPinyinName(String pinyinName) {
		this.pinyinName = pinyinName;
	}
	public String getEverName() {
		return everName;
	}
	public void setEverName(String everName) {
		this.everName = everName;
	}
	public String getSexCode() {
		return sexCode;
	}
	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}
	public String getBornAddressCode() {
		return bornAddressCode;
	}
	public void setBornAddressCode(String bornAddressCode) {
		this.bornAddressCode = bornAddressCode;
	}
	public String getHometownCode() {
		return hometownCode;
	}
	public void setHometownCode(String hometownCode) {
		this.hometownCode = hometownCode;
	}
	public String getNationCode() {
		return nationCode;
	}
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getIdCardTypeCode() {
		return idCardTypeCode;
	}
	public void setIdCardTypeCode(String idCardTypeCode) {
		this.idCardTypeCode = idCardTypeCode;
	}
	public String getIdCardNumber() {
		return idCardNumber;
	}
	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}
	public String getMarrayCode() {
		return marrayCode;
	}
	public void setMarrayCode(String marrayCode) {
		this.marrayCode = marrayCode;
	}
	public String getGotqwCode() {
		return gotqwCode;
	}
	public void setGotqwCode(String gotqwCode) {
		this.gotqwCode = gotqwCode;
	}
	public String getPolicyCode() {
		return policyCode;
	}
	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	public String getHealthCode() {
		return healthCode;
	}
	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}
	public String getFaithCode() {
		return faithCode;
	}
	public void setFaithCode(String faithCode) {
		this.faithCode = faithCode;
	}
	public String getBloodCode() {
		return bloodCode;
	}
	public void setBloodCode(String bloodCode) {
		this.bloodCode = bloodCode;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getIdCardValidity() {
		return idCardValidity;
	}
	public void setIdCardValidity(int idCardValidity) {
		this.idCardValidity = idCardValidity;
	}
	public String getSingleCode() {
		return singleCode;
	}
	public void setSingleCode(String singleCode) {
		this.singleCode = singleCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInternetAddress() {
		return internetAddress;
	}
	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
	public String getChatNo() {
		return chatNo;
	}
	public void setChatNo(String chatNo) {
		this.chatNo = chatNo;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
		if (null != this.createTime) {
			this.showCreateTime = DateUtil.date2AllString(this.createTime);
		}
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
		if ( null != updateTime ) {
			this.showUpdateTime = DateUtil.date2AllString(this.updateTime);
		}
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public String getShowCreateTime() {
		return showCreateTime;
	}
	public void setShowCreateTime(String showCreateTime) {
		this.showCreateTime = showCreateTime;
	}
	public String getShowUpdateTime() {
		return showUpdateTime;
	}
	public void setShowUpdateTime(String showUpdateTime) {
		this.showUpdateTime = showUpdateTime;
	}
	public UserForm toUserForm() {
		UserForm uf = new UserForm();
		uf.setCitysNum(this.city);
		try {
			uf.setCsrq(DateUtil.date2String(this.birthday));
		}catch(Exception e) {
		}
		uf.setCym(this.everName);
		uf.setDownsNum(this.area);
		uf.setGh(this.studentId);
		uf.setIdTypeNum(this.idCardTypeCode);
		uf.setMacaoNum(this.gotqwCode);
		uf.setMaritalStatusNum(this.marrayCode);
		uf.setNationalityNum(this.countryCode);
		uf.setNationNum(this.nationCode);
		uf.setPlaceNum(this.hometownCode);
		uf.setProvincesNum(this.province);
		uf.setSex(this.sexCode);
		uf.setXm(this.name);
		uf.setXmpy(this.pinyinName);
		uf.setYwxm(this.englishName);
		uf.setZjhm(this.idCardNumber);
		uf.setZp(this.photo);
		uf.setUgwNum(this.post);
		uf.setUrylxNum(this.type);
		uf.setAccount(this.account);
		uf.setEmail(this.email);
		return uf;
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
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
}
