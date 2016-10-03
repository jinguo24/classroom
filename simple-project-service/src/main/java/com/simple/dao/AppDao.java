package com.simple.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.api.CheckVersion;

@Repository
@DatabaseTemplate("st_all")
public class AppDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public CheckVersion queryValidApp(String tanentId) {
		return this.sqlSession.selectOne("app.queryValid",tanentId);
	}
}
