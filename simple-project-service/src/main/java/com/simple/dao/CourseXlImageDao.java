package com.simple.dao;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.CourseXlImage;

@Repository
@DatabaseTemplate("st_all")
public class CourseXlImageDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addCourseXlImage(CourseXlImage classentity) {
		this.sqlSession.insert("coursexlImage.insert", classentity);
	}
	
	public CourseXlImage query(String tanentId,String jsbh,String xlbh) {
		Map param = new HashMap();
		param.put("tanentid",tanentId);
		param.put("jsbh", jsbh);
		param.put("kcxlbh", xlbh);
		return this.sqlSession.selectOne("coursexlImage.query",param);
	}
	
	public void updateCourseXlImage(CourseXlImage classentity) {
		this.sqlSession.update("coursexlImage.update", classentity);
	}
	
}
