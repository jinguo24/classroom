package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Section;

@Repository
@DatabaseTemplate("st_all")
public class SectionDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addSection(Section section) {
		this.sqlSession.insert("section.insert", section);
	}
	
	public List<Section> query(String leaseholderId,String code,String templateCode,String name,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("code", code);
		param.put("templateCode", templateCode);
		param.put("name", name);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("section.query",param);
	}
	
	public Section queryById(int id) {
		return this.sqlSession.selectOne("section.queryById",id);
	}
	
	public void updateTemplate(Section section) {
		this.sqlSession.update("section.update", section);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("section.delete", id);
	}
	
	public void deleteByTemplateCode(String templateCode) {
		this.sqlSession.delete("section.deleteByTemplateCode", templateCode);
	}
}
