package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.QuestionItem;

@Repository
@DatabaseTemplate("st_all")
public class QuestionItemDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addQuestionItem(QuestionItem questionItem) {
		this.sqlSession.insert("questionItem.insert", questionItem);
	}
	
	public List<QuestionItem> query(String leaseholderId,String code,String questionCode,String tempalteCode,String sectionCode,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("code", code);
		param.put("questionCode", questionCode);
		param.put("tempalteCode", tempalteCode);
		param.put("sectionCode", sectionCode);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("questionItem.query",param);
	}
	
	public QuestionItem queryById(int id) {
		return this.sqlSession.selectOne("questionItem.queryById",id);
	}
	
	public void updateQuestion(QuestionItem questionItem) {
		this.sqlSession.update("questionItem.update", questionItem);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("questionItem.delete", id);
	}
	
	public void deleteByTemplateCode(String templateCode) {
		this.sqlSession.delete("questionItem.deleteByTemplateCode", templateCode);
	}
}
