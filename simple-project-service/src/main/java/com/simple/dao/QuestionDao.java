package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Question;

@Repository
@DatabaseTemplate("st_all")
public class QuestionDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addQuestion(Question question) {
		this.sqlSession.insert("question.insert", question);
	}
	
	public List<Question> query(String leaseholderId,String code,String tempalteCode,String sectionCode,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("code", code);
		param.put("tempalteCode", tempalteCode);
		param.put("sectionCode", sectionCode);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("question.query",param);
	}
	
	public Question queryById(int id) {
		return this.sqlSession.selectOne("question.queryById",id);
	}
	
	public void updateQuestion(Question question) {
		this.sqlSession.update("question.update", question);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("question.delete", id);
	}
	
	public void deleteByTemplateCode(String templateCode) {
		this.sqlSession.delete("question.deleteByTemplateCode",templateCode);
	}
}
