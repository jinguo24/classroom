package com.simple.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.AppLog;

@Repository
@DatabaseTemplate("st_all")
public class AppLogDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void createLog(AppLog log) {
		this.sqlSession.insert("appLog.insert",log);
	}
}
