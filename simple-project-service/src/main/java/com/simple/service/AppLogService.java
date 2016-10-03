package com.simple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.AppLogDao;
import com.simple.model.AppLog;

@Service
public class AppLogService {

	@Autowired
	private AppLogDao appLogDao;
	
	
	public void addAppLog(AppLog log) {
		appLogDao.createLog(log);
	}
	
}
