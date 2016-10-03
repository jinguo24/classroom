package com.simple.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.excel.ObjectExcutor;
import com.simple.common.excel.ReadExcel;
import com.simple.common.util.ResponseInfo;
import com.simple.dao.ClassStudentDao;
import com.simple.dao.DictionaryDao;
import com.simple.dao.SysUserDao;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.TClass;
import com.simple.model.TClassStudent;
import com.simple.model.api.XueYuan;

@Service
public class TClassStudentService {

	@Autowired
	private ClassStudentDao classStudentDao;
	@Autowired
	SysUserDao userDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	public ResponseInfo validateFile(File file,final String leaseholderId,final String njbh) throws IOException {
		String suffix = file.getName().substring(file.getName().lastIndexOf(".")+1);
		return ReadExcel.read(new FileInputStream(file),new ObjectExcutor(){
				@Override
				public Object getObject(List<String> cellValues) {
					String xn = null;
					if (cellValues.size() > 0 ) {
							xn = StringUtils.trimToNull(cellValues.get(0));
					}
					String njmc = dictionaryDao.queryByCode(njbh).getName();
					String bjbh = null;
					if (cellValues.size() > 1) {
							bjbh = StringUtils.trimToNull(cellValues.get(1));
					}
					String bjmc = null;
					if (cellValues.size() > 2) {
							bjmc = StringUtils.trimToNull(cellValues.get(2));
					}
					
					String gh = null;
					if (cellValues.size() > 3) {
							gh = StringUtils.trimToNull(cellValues.get(3));
					}
					String xm = null;
					if (cellValues.size() > 4) {
							xm = StringUtils.trimToNull(cellValues.get(4));
					}
					String bz = null;
					if (cellValues.size() > 5) {
							bz = StringUtils.trimToNull(cellValues.get(5));
					}
					StringBuffer errormsg = new StringBuffer();
					boolean ispass = true;
					TClassStudent tclass = null;
					if ( null == xn && null == bjbh && null == bjmc && null == gh && null == xm && null == bz) {
						return null;
					}
					if ( null != xn && null != njbh && null != njmc && null != bjbh && null != bjmc && null != gh && null != xm) {
						tclass = new TClassStudent();
						try {
							tclass.setXn(Integer.parseInt(xn));
						}catch(Exception e) {
							errormsg.append("列[1]：请填写整数;");
							ispass = false;
						}
						tclass.setNjbh(njbh);
						tclass.setNjmc(njmc);
						tclass.setBjbh(bjbh);
						tclass.setBjmc(bjmc);
						tclass.setGh(gh);
						tclass.setXm(xm);
						tclass.setBz(bz);
						//boolean exists = isExists(leaseholderId,bjbh,Integer.parseInt(xn));
						//if (exists) {
						//	ispass = false;
						//	errormsg.append("该租户下的[班级+学年]已经存在;");
						//}
					}else {
						ispass = false;
						errormsg.append("请不要填写完全，不要留空白列;");
					}
					
					if (ispass) {
						return tclass;
					}else {
						throw new RuntimeException(errormsg.toString());
					}
				}
			},suffix);
	}
	
	public void addTClass(List<TClassStudent> tclasslist,String leaseholderId,String xxbh,String xxmc,String loginName) throws IllegalAccessException, InvocationTargetException {
		if ( null != tclasslist ) {
			for ( int i = 0 ; i < tclasslist.size() ; i ++) {
				TClassStudent tclass = tclasslist.get(i);
				tclass.setTanentid(leaseholderId);
				tclass.setXxhb(xxbh);
				tclass.setXxmc(xxmc);
				TClassStudent o = queryByBJBHXn(leaseholderId,tclass.getBjbh(),tclass.getXn(),tclass.getNjbh(),tclass.getGh());
				if ( null != o ) {
					tclass.setLineid(o.getLineid());
					tclass.setCjr(o.getCjr());
					tclass.setCjsj(o.getCjsj());
					BeanUtils.copyProperties(o, tclass);
					classStudentDao.updateClassStudent(o);
				}else {
					tclass.setCjr(loginName);
					tclass.setCjsj(new Timestamp(new Date().getTime()));
					classStudentDao.addClassStudent(tclasslist.get(i));
				}
				
				//更新user
				SysUser user = userDao.querySysUser(leaseholderId, tclass.getGh());
				if ( null == user ) {
					user = new SysUser();
					user.setStudentId(tclass.getGh());
					user.setName(tclass.getXm());
					user.setCreateTime(new Timestamp(new Date().getTime()));
					user.setType("student");
					user.setLeaseholderId(leaseholderId);
					userDao.add(user);
				}else {
					user.setName(tclass.getXm());
					userDao.update(user);
				}
			}
		}
	}
	
	public ResponseInfo download(TClassStudent tclass,int pageIndex,int pageSize) {
		List<TClassStudent> templateList =  classStudentDao.query(tclass, pageIndex, pageSize);
		String[] titles = new String[]{"学年","班级编号","班级名称","学生工号","学生姓名","备注"};
		return DownLoadExcel.download(templateList, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				TClassStudent log = (TClassStudent) o;
				List<String> sl = new ArrayList<String>();
				sl.add(String.valueOf(log.getXn()));
				sl.add(log.getBjbh());
				sl.add(log.getBjmc());
				sl.add(log.getGh());
				sl.add(log.getXm());
				sl.add(log.getBz());
				return sl;
			}
		});
	}
	
	private TClassStudent queryByBJBHXn(String leaseholderId,String bjbh,int xn,String njbh,String gh) {
		TClassStudent c = new TClassStudent();
		c.setTanentid(leaseholderId);
		c.setBjbh(bjbh);
		c.setXn(xn);
		c.setNjbh(njbh);
		c.setGh(gh);
		List<TClassStudent> classes = classStudentDao.query(c, 1, 1);
		if (null != classes && classes.size() > 0 ) {
			return classes.get(0);
		}
		return null;
	}
	
	public PageResult query(TClassStudent tclass,int pageIndex,int pageSize) {
		List<TClassStudent> templateList =  classStudentDao.query(tclass, pageIndex, pageSize);
		int count = classStudentDao.queryCount(tclass);
		return new PageResult(count,pageSize,pageIndex,templateList);
	}
	
	public List<XueYuan> queryXueYuan(String tanentId,String xxbh,String njbh,String bjbh) {
		return classStudentDao.queryXueYuan(tanentId, xxbh, njbh, bjbh);
	}
}
