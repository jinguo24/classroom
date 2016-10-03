package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.model.Template;

@Repository
@DatabaseTemplate("st_all")
public class TemplateDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addTemplate(Template template) {
		this.sqlSession.insert("template.insert", template);
	}
	
	public List<Template> query(String leaseholderId,String code,String begin,String end,String type,String name,
			String category, List<String> codes,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("code", code);
		param.put("beginTime", begin);
		param.put("endTime", end);
		param.put("type", type);
		param.put("name", name);
		param.put("category", category);
		param.put("codes", codes);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("template.query",param);
	}
	
	public int queryCount(String leaseholderId,String code,String begin,String end,String type,String name,
			String category , List<String> codes) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("code", code);
		param.put("beginTime", begin);
		param.put("endTime", end);
		param.put("type", type);
		param.put("name", name);
		param.put("category", category);
		param.put("codes", codes);
		return this.sqlSession.selectOne("template.queryCount",param);
	}
	
	public Template queryById(int id) {
		return this.sqlSession.selectOne("template.queryById",id);
	}
	
	public void updateTemplate(Template template) {
		this.sqlSession.update("template.update", template);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("template.delete", id);
	}
	
	public static void main(String[] args) {
		System.out.println(PrimaryKeyUtil.getUUID());
	}
}
