package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.TClass;
import com.simple.model.XueXiao;
import com.simple.model.api.BanJi;
import com.simple.model.api.NianJi;

@Repository
@DatabaseTemplate("st_all")
public class ClassDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addClass(TClass classentity) {
		this.sqlSession.insert("class.insert", classentity);
	}
	
	public TClass queryById(int id) {
		return this.sqlSession.selectOne("class.queryById", id);
	}
	
	public List<TClass> query(TClass classentity,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("lineid", classentity.getLineid());
		param.put("leaseholderId", classentity.getTanentid());
		param.put("xxhb", classentity.getXxhb());
		param.put("xxmc", classentity.getXxmc());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("class.query",param);
	}
	
	public int queryCount(TClass classentity) {
		Map param = new HashMap();
		param.put("lineid", classentity.getLineid());
		param.put("leaseholderId", classentity.getTanentid());
		param.put("xxhb", classentity.getXxhb());
		param.put("xxmc", classentity.getXxmc());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		return this.sqlSession.selectOne("class.queryCount",param);
	}
	
	public void updateClass(TClass classentity) {
		this.sqlSession.update("class.update", classentity);
	}
	
	public void deleteById(int  id) {
		this.sqlSession.delete("class.delete", id);
	}
	
	public List<NianJi> queryNjList(String tanentId,int xn,String xxbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("xn", xn);
		param.put("xxbh", xxbh);
		return this.sqlSession.selectList("class.queryNianJi",param);
	}
	
	public List<BanJi> queryBjList(String tanentId,int xn,String njbh,String xxbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("xn", xn);
		param.put("njbh", njbh);
		param.put("xxbh", xxbh);
		return this.sqlSession.selectList("class.queryBanJi",param);
	}
	
	public List<Integer> queryXn(String tanentId,String xxbh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("xxbh", xxbh);
		return this.sqlSession.selectList("class.queryXn",param);
	}
	
	public List<XueXiao> queryXueXiao(String tanentId) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		return this.sqlSession.selectList("class.queryXueXiao",param);
	}
}
