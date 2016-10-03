package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.TeachingLog;
import com.simple.model.TeachingStudent;

@Repository
@DatabaseTemplate("st_all")
public class TeachingStudentDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addTeachingStudent(TeachingStudent teachingLog) {
		this.sqlSession.insert("teachingStudent.insert", teachingLog);
	}
	
	public List<TeachingStudent> queryBySKBH(String leaseholderId,String skbh,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId",leaseholderId);
		param.put("skbh", skbh);
		if (pageIndex <= 0 ) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("end", pageSize);
		return this.sqlSession.selectList("teachingStudent.queryByKCBH",param);
	}
	
	public int queryCount(String leaseholderId,String skbh) {
		Map param = new HashMap();
		param.put("leaseholderId",leaseholderId);
		param.put("skbh", skbh);
		return this.sqlSession.selectOne("teachingStudent.queryCount",param);
	}
}
