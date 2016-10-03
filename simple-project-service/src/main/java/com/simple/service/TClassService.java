package com.simple.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import com.simple.dao.ClassDao;
import com.simple.dao.ClassStudentDao;
import com.simple.dao.DictionaryDao;
import com.simple.dao.SysUserDao;
import com.simple.model.PageResult;
import com.simple.model.TClass;
import com.simple.model.XueXiao;
import com.simple.model.api.BanJi;
import com.simple.model.api.NianJi;

@Service
public class TClassService {

	@Autowired
	private ClassDao classDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private ClassStudentDao classStudentDao;
	@Autowired
	SysUserDao userDao;
	
	
	public ResponseInfo download(TClass tclass,int pageIndex,int pageSize) {
		List<TClass> templateList =  classDao.query(tclass, pageIndex, pageSize);
		String[] titles = new String[]{"学年","班级编号","班级名称","班主任工号","班主任姓名"};
		return DownLoadExcel.download(templateList, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				TClass log = (TClass) o;
				List<String> sl = new ArrayList<String>();
				sl.add(String.valueOf(log.getXn()));
				sl.add(log.getBjbh());
				sl.add(log.getBjmc());
				sl.add(log.getGh());
				sl.add(log.getXm());
				return sl;
			}
		});
	}
	
	
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
						StringBuffer errormsg = new StringBuffer();
						boolean ispass = true;
						TClass tclass = null;
						if ( null == xn && null == bjbh && null == bjmc && null == gh && null == xm ) {
							return null;
						}
						if ( null != xn && null != njbh && null != njmc && null != bjbh && null != bjmc && null != gh && null != xm) {
							tclass = new TClass();
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
							//boolean exists = isExists(leaseholderId,bjbh,Integer.parseInt(xn));
							//if (exists) {
							//	ispass = false;
							//	errormsg.append("该租户下的[班级+学年]已经存在;");
							//}
						}else {
							ispass = false;
							errormsg.append("请填写完全，不要留空白列;");
						}
						
						if (ispass) {
							return tclass;
						}else {
							throw new RuntimeException(errormsg.toString());
						}
				}
			},suffix);
	}
	
	public void addTClass(List<TClass> tclasslist,String leaseholderId,String xxbh,String xxmc,String loginName) throws IllegalAccessException, InvocationTargetException {
		if ( null != tclasslist ) {
			for ( int i = 0 ; i < tclasslist.size() ; i ++) {
				TClass tclass = tclasslist.get(i);
				tclass.setTanentid(leaseholderId);
				tclass.setXxhb(xxbh);
				tclass.setXxmc(xxmc);
				TClass o = queryByBJBHXn(leaseholderId,tclass.getBjbh(),tclass.getXn());
				if ( null != o ) {
					tclass.setLineid(o.getLineid());
					tclass.setCjr(o.getCjr());
					tclass.setCjsj(o.getCjsj());
					BeanUtils.copyProperties(o, tclass);
					classDao.updateClass(o);
				}else {
					tclass.setCjr(loginName);
					tclass.setCjsj(new Date());
					classDao.addClass(tclasslist.get(i));
				}
			}
		}
	}
	
	private TClass queryByBJBHXn(String leaseholderId,String bjbh,int xn) {
		TClass c = new TClass();
		c.setTanentid(leaseholderId);
		c.setBjbh(bjbh);
		c.setXn(xn);
		List<TClass> classes = classDao.query(c, 1, 1);
		if (null != classes && classes.size() > 0 ) {
			return classes.get(0);
		}
		return null;
	}
	
	public PageResult query(TClass tclass,int pageIndex,int pageSize) {
		List<TClass> templateList =  classDao.query(tclass, pageIndex, pageSize);
		int count = classDao.queryCount(tclass);
		return new PageResult(count,pageSize,pageIndex,templateList);
	}
	
	public void delete(int id) {
		TClass tclass = classDao.queryById(id);
		if ( null != tclass ) {
			//删除user
			List<String> ghs = classStudentDao.queryGhs(tclass.getTanentid(), tclass.getBjbh(), tclass.getXn());
			if ( null != ghs && ghs.size() > 0 ) {
				userDao.delete(tclass.getTanentid(), ghs);
			}
			//删除classstudent
			classStudentDao.delete(tclass.getTanentid(), tclass.getBjbh(), tclass.getXn());
		}
		//删除班级
		classDao.deleteById(id);
	}
	
	public List<NianJi> njList(String tanentId,int xn,String xxbh) {
		return classDao.queryNjList(tanentId,xn,xxbh);
	}
	
	public List<BanJi> bjList(String tanentId,int xn,String njbh,String xxbh) {
		return classDao.queryBjList(tanentId,xn,njbh,xxbh);
	}
	
	public List<Integer> queryXn(String tanentId,String xxbh) {
		return classDao.queryXn(tanentId,xxbh);
	}
	
	public List<XueXiao> queryXueXiao(String tanentId) {
		return classDao.queryXueXiao(tanentId);
	}
}
