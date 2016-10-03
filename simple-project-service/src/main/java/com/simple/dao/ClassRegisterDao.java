package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.ClassRegister;
import com.simple.model.RegisterImage;
import com.simple.model.api.NianJi;

@Repository
@DatabaseTemplate("st_all")
public class ClassRegisterDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addClassRegister(ClassRegister register) {
		this.sqlSession.insert("classRegister.insert", register);
	}
	
	public List<ClassRegister> query(ClassRegister course,String begin,String end,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("lineid", course.getLineid());
		param.put("leaseholderId", course.getTanentid());
		param.put("xxbh", StringUtils.trimToNull(course.getXxbh()));
		param.put("xxmc", StringUtils.trimToNull(course.getXxmc()));
		param.put("jsbh", StringUtils.trimToNull(course.getJsbh()));
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("classRegister.query",param);
	}
	
	public int queryCount(ClassRegister course,String begin,String end) {
		Map param = new HashMap();
		param.put("lineid", course.getLineid());
		param.put("leaseholderId", course.getTanentid());
		param.put("xxbh", StringUtils.trimToNull(course.getXxbh()));
		param.put("xxmc", StringUtils.trimToNull(course.getXxmc()));
		param.put("jsbh", StringUtils.trimToNull(course.getJsbh()));
		param.put("beginTime", begin);
		param.put("endTime", end);
		return this.sqlSession.selectOne("classRegister.queryCount",param);
	}
	
	public void updateClassRegister(ClassRegister course) {
		this.sqlSession.update("classRegister.update", course);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("classRegister.delete", id);
	}
	
	public void addImages(RegisterImage image) {
		this.sqlSession.insert("classRegister.insertImages",image);
	}
	
	public void deleteImages(String tanentId,String jsbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("jsbh", jsbh);
		this.sqlSession.delete("classRegister.deleteImages",param);
	}
	
	public List<RegisterImage> queryImages(String tanentId,String jsbh,String type) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("jsbh", jsbh);
		param.put("imageType", type);
		return this.sqlSession.selectList("classRegister.queryImages",param);
	}
	
	public ClassRegister queryClassRegister(String sqzh) {
		return this.sqlSession.selectOne("classRegister.queryBySQZH",sqzh);
	}
	
	public void updatePassword(String sqzh,String password) {
		Map param = new HashMap();
		param.put("password", password);
		param.put("sqzh", sqzh);
		this.sqlSession.update("classRegister.updatePassword", param);
	}
	
}
