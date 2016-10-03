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
import com.simple.model.CourseWenjuanTeacher;

@Repository
@DatabaseTemplate("st_all")
public class CourseWenjuanTeacherDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public CourseWenjuanTeacher query(String kcbh,String gh) {
		Map param = new HashMap();
		param.put("gh", gh);
		param.put("kcbh", kcbh);
		return this.sqlSession.selectOne("courseWenjuanTearcher.query",param);
	}
	
	public void add(CourseWenjuanTeacher courseWenjuan) {
		this.sqlSession.insert("courseWenjuanTearcher.insert",courseWenjuan);
	}
	
	public void update(CourseWenjuanTeacher courseWenjuan) {
		this.sqlSession.update("courseWenjuanTearcher.update",courseWenjuan);
	}
	
	public List<String> queryValidTeacher(String tanentId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("time", new Date());
		return this.sqlSession.selectList("courseWenjuanTearcher.queryValidTeachers",param);
	}
}
