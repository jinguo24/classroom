package com.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.JyjgUserDao;
import com.simple.model.JyjgUser;

@Service
public class JyjgUserService {

	@Autowired
	private JyjgUserDao jyjgUserDao;
	
	public JyjgUser queryUser(String account) {
		return jyjgUserDao.queryUser(account);
	}
	
	public void updatePassword(String account,String password) {
		jyjgUserDao.updatePassword(account, password);
	}
	
}
