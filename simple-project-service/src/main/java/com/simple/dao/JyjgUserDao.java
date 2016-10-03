package com.simple.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.JyjgUser;

@Repository
@DatabaseTemplate("st_all")
public class JyjgUserDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public JyjgUser queryUser(String account) {
		return this.sqlSession.selectOne("jyjgUser.queryByAccount",account);
	}
	
	public void updatePassword(String account,String password) {
		Map param = new HashMap();
		param.put("password", password);
		param.put("account", account);
		this.sqlSession.update("jyjgUser.updatePassword",param);
	}
}
