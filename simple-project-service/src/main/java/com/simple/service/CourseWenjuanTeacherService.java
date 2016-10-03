package com.simple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.CourseWenjuanTeacherDao;
import com.simple.model.CourseWenjuanTeacher;

@Service
public class CourseWenjuanTeacherService {

	@Autowired
	private CourseWenjuanTeacherDao dao;
	
	
	public CourseWenjuanTeacher query(String kcbh,String gh) {
		return dao.query(kcbh, gh);
	}
	
	public void add(CourseWenjuanTeacher courseWenjuan) {
		dao.add(courseWenjuan);
	}
	
	public void update(CourseWenjuanTeacher courseWenjuan) {
		dao.update(courseWenjuan);
	}
	
	public List<String> queryValidTeacher(String tanentId) {
		return dao.queryValidTeacher(tanentId);
	}
	
}
