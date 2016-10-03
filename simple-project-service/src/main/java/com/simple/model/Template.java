package com.simple.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.simple.common.util.DateUtil;

public class Template implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String leaseholderId;//租户
	private String code;
	private String templateType;
	private String templateTypeName;
	private String name;
	private String remark;//备注
	private String creator;
	private Date createTime;
	private String showTime;
	private String random;//是否随机
	private String category;//模版分类
	private String categoryName;
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
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		if (null != this.createTime) {
			this.showTime = DateUtil.date2AllString(createTime);
		}
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getTemplateTypeName() {
		return templateTypeName;
	}
	public void setTemplateTypeName(String templateTypeName) {
		this.templateTypeName = templateTypeName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "{id:"+this.id+",leaseholderId:\""+this.leaseholderId+"\",code:\""+this.code+"\",templateType:\""+StringUtils.trimToEmpty(this.templateType)+"\","
				+ "name:\""+StringUtils.trimToEmpty(this.name)+"\",remark:\""+StringUtils.trimToEmpty(this.remark)+"\",creator:\""+StringUtils.trimToEmpty(this.creator)+"\",createTime:\""+DateUtil.date2String(this.createTime)+"\","
						+ "random:\""+StringUtils.trimToEmpty(this.random)+"\",category:\""+StringUtils.trimToEmpty(this.category)+"\"}";
	}
}
