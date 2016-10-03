package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.Dictionary;

@Repository
@DatabaseTemplate("st_all")
public class DictionaryDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public int addDitionary(Dictionary dictionary) {
		return this.sqlSession.insert("dictionary.insert", dictionary);
	}
	
	public List<Dictionary> queryRoot(String name,String code,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("code", code);
		param.put("name", name);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("dictionary.query",param);
	}
	
	public Dictionary queryById(int id) {
		return this.sqlSession.selectOne("dictionary.queryById",id);
	}
	
	public void updateDictionary(Dictionary dictionary) {
		this.sqlSession.update("dictionary.update", dictionary);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("dictionary.delete", id);
	}
	
	public Dictionary queryByCode(String code) {
		Map param = new HashMap();
		param.put("code", code);
		return this.sqlSession.selectOne("dictionary.queryByCode",param);
	}
	
	public List<Dictionary> queryByParentCode(String parentCode) {
		Map param = new HashMap();
		param.put("parentCode", parentCode);
		return this.sqlSession.selectList("dictionary.queryByParentCode",param);
	}
	
}
