package com.simple.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.dao.ClassRegisterDao;
import com.simple.dao.CourseImageDao;
import com.simple.dao.CourseXlImageDao;
import com.simple.model.CourseImage;
import com.simple.model.CourseXlImage;

@Service
public class DecorateService {
	
	
	@Autowired
	private CourseXlImageDao coursexlImageDao;
	@Autowired
	private CourseImageDao courseImageDao;
	@Autowired
	ClassRegisterDao classRegisterDao;
	
	public CourseXlImage queryCourseXlImage(String tanentId,String jsbh,String xlbh) {
		return coursexlImageDao.query(tanentId, jsbh, xlbh);
	}
	
	public CourseImage queryCourseImage(String tanentId,String jsbh,String kcbh) {
		return courseImageDao.query(tanentId, jsbh, kcbh);
	}
	
	public void setCourseXlImage(String tanentId,String jsbh,String xlbh,String xlmc,String image,String xxbh,String xxmc,String loginName) {
		CourseXlImage cxi = coursexlImageDao.query(tanentId, jsbh, xlbh);
		if ( null == cxi ) {
			CourseXlImage c = new CourseXlImage();
			c.setJsbh(jsbh);
			c.setTanentid(tanentId);
			c.setXxbh(xxbh);
			c.setXxmc(xxmc);
			c.setKcxlbh(xlbh);
			c.setKcxlmc(xlmc);
			c.setCjr(loginName);
			c.setCjsj(new Date());
			c.setKcxltp(image);
			coursexlImageDao.addCourseXlImage(c);
		}else {
			cxi.setXxbh(xxbh);
			cxi.setXxmc(xxmc);
			cxi.setKcxlmc(xlmc);
			cxi.setKcxltp(image);
			coursexlImageDao.updateCourseXlImage(cxi);
		}
	}
	
	public void setCourseImage(String tanentId,String jsbh,String kcbh,String kcmc,String image,String xxbh,String xxmc,String loginName) {
		CourseImage cxi = courseImageDao.query(tanentId, jsbh, kcbh);
		if ( null == cxi ) {
			CourseImage c = new CourseImage();
			c.setJsbh(jsbh);
			c.setTanentid(tanentId);
			c.setXxbh(xxbh);
			c.setXxmc(xxmc);
			c.setKcbh(kcbh);
			c.setKcmc(kcmc);
			c.setCjr(loginName);
			c.setCjsj(new Date());
			c.setKctp(image);
			courseImageDao.addCourseImage(c);
		}else {
			cxi.setXxbh(xxbh);
			cxi.setXxmc(xxmc);
			cxi.setKcmc(kcmc);
			cxi.setKctp(image);
			courseImageDao.updateCourseImage(cxi);
		}
	}
}
