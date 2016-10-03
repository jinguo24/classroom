package com.simple.service;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.simple.common.util.DateUtil;
import com.simple.dao.ClassAuthorizeDao;
import com.simple.dao.ClassRegisterDao;
import com.simple.dao.CourseDao;
import com.simple.dao.CourseXlDao;
import com.simple.enums.TClassImagesEnum;
import com.simple.model.ClassAuthForm;
import com.simple.model.ClassAuthorize;
import com.simple.model.ClassRegister;
import com.simple.model.ClassRegisterForm;
import com.simple.model.PageResult;
import com.simple.model.RegisterImage;
import com.simple.model.api.KeChengInfo;

@Service
public class ClassRegistorService {

	@Autowired
	private ClassRegisterDao classRegisterDao;
	@Autowired
	private ClassAuthorizeDao classAuthDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseXlDao courseXlDao;
	
	public void addClassRegister(ClassRegisterForm form,String loginName){
		if ( null != form ) {
			ClassRegister register = form.castToRegister();
			register.setCjr(loginName);
			register.setCjsj(new Timestamp(new Date().getTime()));
			if (register.getJfrq() == null) {
				register.setJfrq(new Date());
			}
			classRegisterDao.addClassRegister(register);
			setRegisterImages(form);
		}
	}
	
	private void setRegisterImages(ClassRegisterForm form) {
		//添加图片
		Class c = form.getClass();
		Field[] fields = c.getDeclaredFields();
		if ( null != fields && fields.length > 0 ) {
			for (Field field : fields) {
				String imageType = TClassImagesEnum.getCodeByParam(field.getName());
				if ( null != imageType ) {
					String imageUrl = null;
					try {
						Method method=c.getMethod("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
						imageUrl = (String) method.invoke(form);
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					if ( null != imageUrl) {
						String[] images = imageUrl.split(",");
						for (String ima: images ) {
							if (!StringUtils.isEmpty(ima)) {
								RegisterImage ri = new RegisterImage();
								ri.setImageType(imageType);
								ri.setImageUrl(ima);
								ri.setTanentId(form.getZhh());
								ri.setJsbh(form.getJsbh());
								classRegisterDao.addImages(ri);
							}
						}
					}
				}
			}
		}
	}
	
	public void update(ClassRegister ri ,ClassRegisterForm form) throws Exception {
		ClassRegister register = form.castToRegister();
		register.setCjsj(ri.getCjsj());
		register.setTanentid(ri.getTanentid());
		if (null == ri.getJfrq()) {
			register.setJfrq(new Date());
		}else {
			register.setJfrq(ri.getJfrq());
		}
		BeanUtils.copyProperties(ri, register);
		classRegisterDao.updateClassRegister(ri);
		classRegisterDao.deleteImages(ri.getTanentid(), null);
		form.setZhh(ri.getTanentid());
		setRegisterImages(form);
	}
	
	public List<String> getAuthCourseIds(String tanentId) {
		return classAuthDao.getAuthCourseIds(tanentId);
	}
	
	public PageResult query(ClassRegister register,String begin,String end,int pageIndex,int pageSize) {
		List<ClassRegister> registers = queryList(register, begin, end, pageIndex, pageSize);
		int count = classRegisterDao.queryCount(register, begin, end);
		return new PageResult(count,pageSize,pageIndex,registers);
	}
	
	public List<ClassRegister> queryList(ClassRegister register,String begin,String end,int pageIndex,int pageSize) {
		return classRegisterDao.query(register, begin, end, pageIndex, pageSize);
	}
	
	public int queryCount(String tanentid,String jsbh) {
		ClassRegister register = new ClassRegister();
		register.setTanentid(tanentid);
		register.setJsbh(jsbh);
		return classRegisterDao.queryCount(register, null, null);
	}
	
	public ClassRegister getClassRegister(String tanentId,String jsbh) {
		ClassRegister register = new ClassRegister();
		register.setTanentid(tanentId);
		register.setJsbh(jsbh);
		List<ClassRegister> results = classRegisterDao.query(register, null, null, 1, 1);
		if (null != results && results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
	
	public void setImages(ClassRegister register,ClassRegisterForm cf) {
		List<RegisterImage> images = classRegisterDao.queryImages(register.getTanentid(),register.getJsbh(), null);
		if ( null != images && images.size() > 0 ) {
			Map<String,List<RegisterImage>> imageMap = new HashMap<String,List<RegisterImage>>();
			for (int i = 0 ; i < images.size() ; i++) {
				RegisterImage ri = images.get(i);
				if (imageMap.containsKey(ri.getImageType())) {
					List<RegisterImage> imagelist = imageMap.get(ri.getImageType());
					if ( null == imagelist) {
						imagelist = new ArrayList<RegisterImage>();
					}
					imagelist.add(ri);
				}else {
					List<RegisterImage> imagelist = new ArrayList<RegisterImage>();
					imagelist.add(ri);
					imageMap.put(ri.getImageType(), imagelist);
				}
			}
			Class cfc = cf.getClass();
			Field[] fields = cfc.getDeclaredFields();
			if ( null != fields && fields.length > 0 ) {
				for (Field field : fields) {
					String type = TClassImagesEnum.getCodeByParam(field.getName());
					if ( null != type) {
						List<RegisterImage> imagelist = imageMap.get(type);
						if ( null != imagelist && imagelist.size() > 0 ) {
							try {
								Method method=cfc.getMethod("set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1),String.class);
								//method.setAccessible(true);
								method.invoke(cf,imagelist.get(0).getImageUrl());
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	public void auth(ClassAuthForm cf,String loginName) {
		ClassRegister crq = new ClassRegister();
		crq.setTanentid(cf.getTanentId());
		List<ClassRegister> tclass =  classRegisterDao.query(crq, null,null,1, 1);
		if ( null != tclass && tclass.size() > 0  ) {
			ClassRegister cr = tclass.get(0);
			String jsmc = cr.getJsmc();
			String jsbh = cr.getJsbh();
			String tanentId = cr.getTanentid();
			
			//先删除关联关系
			//classAuthDao.deleteClassAuthorize(tanentId, jsbh);
			//添加关联关系 
			String proIds = cf.getProIds();
			if ( null != proIds) {
				String[] courseIds  = proIds.split(",");
				if ( null != courseIds && courseIds.length > 0 ) {
					for ( int i = 0 ;i < courseIds.length ; i ++) {
						String courseId = courseIds[i];
						if (!StringUtils.isEmpty(courseId)) {
							ClassAuthorize ca = new ClassAuthorize();
							ca.setTanentid(tanentId);
							ca.setJsbh(jsbh);
							ca.setJsmc(jsmc);
							ca.setSqzh(cr.getSqzh());
							ca.setBz(null);
							ca.setCjr(loginName);
							ca.setCjsj(new Date());
							ca.setKcbh(courseId);
							KeChengInfo kechenginfo = courseDao.queryKeChengInfo(courseId);
							if ( null != kechenginfo ) {
								ca.setKcmc(kechenginfo.getName());
							}
							ca.setGmrq(new Date());
							ca.setSqkssj(DateUtil.getDateStart(cf.getBegin()));
							ca.setSqjssj(DateUtil.getDateEnd(cf.getEnd()));
							classAuthDao.addClassAuthorize(ca);
						}
					}
				}
			}
		}
		
	}
	
	public boolean isValid(String tanentId,String jsbh,String kcbh) {
		return classAuthDao.isValid(tanentId, jsbh, kcbh);
	}
	
	public ClassRegister queryClassRegister(String sqzh) {
		return classRegisterDao.queryClassRegister(sqzh);
	}
	
	public List<RegisterImage> queryImages(String tanentId,String jsbh,String type) {
		return classRegisterDao.queryImages(tanentId,jsbh, type);
	}
	
	public void updatePassword(String sqzh,String password) {
		
	}
}
