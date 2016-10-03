package com.simple.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.ClassAuthorize;

@Repository
@DatabaseTemplate("st_all")
public class ClassAuthorizeDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addClassAuthorize(ClassAuthorize classentity) {
		this.sqlSession.insert("classAuthorize.insert", classentity);
	}
	
	public List<ClassAuthorize> query(ClassAuthorize classentity,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("jsbh", classentity.getJsbh());
		param.put("sqzh", classentity.getSqzh());
		param.put("jsmc", classentity.getJsmc());
		param.put("kcbh", classentity.getKcbh());
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("classAuthorize.query",param);
	}
	
	public int queryCount(ClassAuthorize classentity) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("jsbh", classentity.getJsbh());
		param.put("sqzh", classentity.getSqzh());
		param.put("jsmc", classentity.getJsmc());
		param.put("kcbh", classentity.getKcbh());
		return this.sqlSession.selectOne("classAuthorize.queryCount",param);
	}
	
	public void updateClassAuthorize(ClassAuthorize classentity) {
		this.sqlSession.update("classAuthorize.update", classentity);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("classAuthorize.delete", id);
	}
	
	public void deleteClassAuthorize(String tanentId,String jsbh) {
		Map param = new HashMap();
		param.put("leaseholderId", tanentId);
		param.put("jsbh", jsbh);
		this.sqlSession.delete("classAuthorize.deleteByJsbh",param);
	}
	
	public boolean isValid(String tanentId,String jsbh,String kcbh) {
		Map parm = new HashMap();
		parm.put("leaseholderId", tanentId);
		parm.put("jsbh", jsbh);
		parm.put("kcbh", kcbh);
		Date maxDate = this.sqlSession.selectOne("classAuthorize.queryMaxJSSJ");
		Date minDate = this.sqlSession.selectOne("classAuthorize.queryMinJSSJ");
		Date now = new Date();
		if (now.after(minDate) && now.before(maxDate)) {
			return true;
		}
		return false;
	}
	
	public List<String> getAuthCourseIds(String tanentId) {
		return this.sqlSession.selectList("classAuthorize.queryAuthCourseIds",tanentId);
	}
}
