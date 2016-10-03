package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.TClassStudent;
import com.simple.model.api.XueYuan;

@Repository
@DatabaseTemplate("st_all")
public class ClassStudentDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addClassStudent(TClassStudent classentity) {
		this.sqlSession.insert("classStudent.insert", classentity);
	}
	
	public List<TClassStudent> query(TClassStudent classentity,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("xxhb", classentity.getXxhb());
		param.put("xxmc", classentity.getXxmc());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("classStudent.query",param);
	}
	
	public int queryCount(TClassStudent classentity) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("xxhb", classentity.getXxhb());
		param.put("xxmc", classentity.getXxmc());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		return this.sqlSession.selectOne("classStudent.queryCount",param);
	}
	
	public void updateClassStudent(TClassStudent classentity) {
		this.sqlSession.update("classStudent.update", classentity);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("classStudent.delete", id);
	}
	
	public List<XueYuan> queryXueYuan(String tanentId,String xxbh,String njbh,String bjbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("xxbh", xxbh);
		param.put("njbh", njbh);
		param.put("bjbh", bjbh);
		return this.sqlSession.selectList("classStudent.queryXueYuan",param);
	}
	
	public List<String> queryGhs(String tanentId,String bjbh,int xn) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("bjbh", bjbh);
		param.put("xn", xn);
		return this.sqlSession.selectList("classStudent.queryGhs",param);
	}
	
	public void delete(String tanentId,String bjbh,int xn) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("bjbh", bjbh);
		param.put("xn", xn);
		this.sqlSession.delete("classStudent.deleteBatchs", param);
	}
}
