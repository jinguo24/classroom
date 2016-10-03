package com.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.AppDao;
import com.simple.model.api.CheckVersion;

@Service
public class AppService {

	@Autowired
	private AppDao appDao;
	
	
	public CheckVersion queryValidApp(String tanentId) {
		return appDao.queryValidApp(tanentId);
	}
	
}
