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
import com.simple.model.QuestionResult;

@Repository
@DatabaseTemplate("st_all")
public class QuestionResultDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addQuestionResult(QuestionResult questionResult) {
		this.sqlSession.insert("questionResult.insert", questionResult);
	}
	
	public List<QuestionResult> query(QuestionResult result,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", result.getLeaseholderId());
		param.put("studentId", result.getStudentId());
		param.put("code", result.getCode());
		param.put("activityCode", result.getActivityCode());
		param.put("tempalteCode", result.getTempalteCode());
		param.put("sectionCode", result.getSectionCode());
		param.put("questionCode", result.getQuestionCode());
		param.put("questionItemCode", result.getQuestionItemCode());
		param.put("skbh", result.getSkbh());
		param.put("xxbh", result.getXxbh());
		param.put("xxmc", result.getXxmc());
		param.put("njbh", result.getNjbh());
		param.put("njmc", result.getNjmc());
		param.put("bjbh", result.getBjbh());
		param.put("bjmc", result.getBjmc());
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("questionResult.query",param);
	}
	
	public int queryCount(QuestionResult result) {
		Map param = new HashMap();
		param.put("leaseholderId", result.getLeaseholderId());
		param.put("studentId", result.getStudentId());
		param.put("code", result.getCode());
		param.put("activityCode", result.getActivityCode());
		param.put("tempalteCode", result.getTempalteCode());
		param.put("sectionCode", result.getSectionCode());
		param.put("questionCode", result.getQuestionCode());
		param.put("questionItemCode", result.getQuestionItemCode());
		param.put("skbh", result.getSkbh());
		param.put("xxbh", result.getXxbh());
		param.put("xxmc", result.getXxmc());
		param.put("njbh", result.getNjbh());
		param.put("njmc", result.getNjmc());
		param.put("bjbh", result.getBjbh());
		param.put("bjmc", result.getBjmc());
		return this.sqlSession.selectOne("questionResult.queryCount",param);
	}
	
	
	
	public QuestionResult queryById(int id) {
		return this.sqlSession.selectOne("questionResult.queryById",id);
	}
	
	public Map<String,Integer> queryResultStatistics(List<QuestionItem> items) {
		Map<String ,Integer> map = new HashMap<String,Integer>();
		for ( int i = 0 ; i < items.size() ;  i++) {
			QuestionItem qi = items.get(i);
			Integer count = this.sqlSession.selectOne("questionResult.itemCount", qi.getCode());
			map.put(qi.getCode(), count);
		}
		return map;
	}
	
	public List<String> queryTemplateCodes(QuestionResult result) {
		Map param = new HashMap();
		param.put("leaseholderId", result.getLeaseholderId());
		param.put("studentId", result.getStudentId());
		param.put("code", result.getCode());
		param.put("activityCode", result.getActivityCode());
		param.put("tempalteCode", result.getTempalteCode());
		param.put("sectionCode", result.getSectionCode());
		param.put("questionCode", result.getQuestionCode());
		param.put("questionItemCode", result.getQuestionItemCode());
		param.put("skbh", result.getSkbh());
		param.put("xxbh", result.getXxbh());
		param.put("xxmc", result.getXxmc());
		param.put("njbh", result.getNjbh());
		param.put("njmc", result.getNjmc());
		param.put("bjbh", result.getBjbh());
		param.put("bjmc", result.getBjmc());
		return this.sqlSession.selectList("questionResult.queryTemplateCodes",param);
	}
	
}
