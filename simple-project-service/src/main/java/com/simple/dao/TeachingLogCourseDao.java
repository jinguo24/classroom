package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.TeachingLogCourse;
import com.simple.model.api.KeChengXiLie;

@Repository
@DatabaseTemplate("st_all")
public class TeachingLogCourseDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addTeachingLogCourse(TeachingLogCourse teachingLogCourse) {
		this.sqlSession.insert("teachingLogCourse.insert", teachingLogCourse);
	}
	
	public List<String> querySKBH(String tanentId,String kcbh,String kcxlbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("kcbh", kcbh);
		param.put("kcxlbh", kcxlbh);
		return this.sqlSession.selectList("teachingLogCourse.querySKBH",param);
	}
	
	public List<KeChengXiLie> queryXiLie(String tanentId,List<String> kcbhs) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("skbhlist", kcbhs);
		return this.sqlSession.selectList("teachingLogCourse.queryXiLie",param);
	}
}
