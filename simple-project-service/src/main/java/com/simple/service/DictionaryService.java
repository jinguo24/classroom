package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.DictionaryDao;
import com.simple.model.Dictionary;
@Service
public class DictionaryService {

	@Autowired
	private DictionaryDao dictionaryDao;
	
	public int addDitionary(Dictionary dictionary) {
		return dictionaryDao.addDitionary(dictionary);
	}
	
	public List<Dictionary> queryRoot(String name,String code,int pageIndex,int pageSize) {
		return dictionaryDao.queryRoot( name,code,pageIndex,pageSize);
	}
	
	public Dictionary queryById(int id) {
		return dictionaryDao.queryById(id);
	}
	
	public void updateDictionary(Dictionary dictionary) {
		dictionaryDao.updateDictionary(dictionary);
	}
	
	public void deleteById(int  id) {
		dictionaryDao.deleteById(id);
	}
	
	public Dictionary getByCode(String code) {
		return dictionaryDao.queryByCode(code);
	}
	
	public List<Dictionary> getByParentCode(String parentCode) {
		return dictionaryDao.queryByParentCode(parentCode);
	}
}
