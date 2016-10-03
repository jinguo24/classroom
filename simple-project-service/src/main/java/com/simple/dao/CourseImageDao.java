package com.simple.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.CourseImage;

@Repository
@DatabaseTemplate("st_all")
public class CourseImageDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addCourseImage(CourseImage classentity) {
		this.sqlSession.insert("courseImage.insert", classentity);
	}
	
	public CourseImage query(String tanentId,String jsbh,String kcbh) {
		Map param = new HashMap();
		param.put("tanentid",tanentId);
		param.put("jsbh", jsbh);
		param.put("kcbh", kcbh);
		return this.sqlSession.selectOne("courseImage.query",param);
	}
	
	public void updateCourseImage(CourseImage classentity) {
		this.sqlSession.update("courseImage.update", classentity);
	}
	
}
