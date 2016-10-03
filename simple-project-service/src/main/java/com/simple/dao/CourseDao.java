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
import com.simple.model.Course;
import com.simple.model.api.KeCheng;
import com.simple.model.api.KeChengInfo;

@Repository
@DatabaseTemplate("st_all")
public class CourseDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addCourse(Course course) {
		this.sqlSession.insert("course.insert", course);
	}
	
	public List<Course> query(Course course,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("lineid", course.getLineid());
		param.put("code", course.getCode());
		param.put("name", StringUtils.trimToNull(course.getName()));
		param.put("kcxlbh", course.getKcxlbh());
		param.put("syjsWersion", StringUtils.trimToNull(course.getSyjsWersion()));
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("course.query",param);
	}
	
	public int queryCount(Course course) {
		Map param = new HashMap();
		param.put("lineid", course.getLineid());
		param.put("code", course.getCode());
		param.put("name", course.getName());
		return this.sqlSession.selectOne("course.queryCount",param);
	}
	
	public void updateCourse(Course course) {
		this.sqlSession.update("course.update", course);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("course.delete", id);
	}
	
	public List<KeCheng> queryKeCheng(List<String> courseBhList,String xlbh,String njbh,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("courseBhlist", courseBhList);
		param.put("kcxlbh", xlbh);
		param.put("njbh", njbh);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("course.queryKeCheng",param);
	}
	
	public int queryKeChengCount(List<String> courseBhList,String xlbh,String njbh) {
		Map param = new HashMap();
		param.put("courseBhlist", courseBhList);
		param.put("kcxlbh", xlbh);
		param.put("njbh", njbh);
		return this.sqlSession.selectOne("course.queryKeChengCount",param);
	}
	
	public KeChengInfo queryKeChengInfo(String code) {
		return this.sqlSession.selectOne("course.queryKeChengInfo",code);
	}
	
	public List<String> getXiLieBHList(List<String> courseBhList) {
		return this.sqlSession.selectList("course.queryXiLieBhList",courseBhList);
	}
	
	public List<String> getCourseIds(String xlbh) {
		return this.sqlSession.selectList("course.queryCouserBhList",xlbh);
	}
}
