package com.simple.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.simple.common.util.DateUtil;
import com.simple.dao.DictionaryDao;
import com.simple.dao.SysUserDao;
import com.simple.model.Dictionary;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.UserForm;
@Service
public class SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	public SysUser querySysUser(String leaseholderId,String studentId) {
		return sysUserDao.querySysUser(leaseholderId,studentId);
	}
	
	public void updatePassword(String leaseholderId,String studentId,String passowrd) {
		sysUserDao.updatePassword(leaseholderId, studentId, passowrd);
	}
	
	public void addSysUser(UserForm uf,String leaseholderId,String loginName) throws Exception {
		SysUser user = uf.toSysUser();
		//如果是老师，验证帐号唯一
		if ("teacher".equals(user.getType())) {
			if (StringUtils.isEmpty(user.getAccount())) {
				throw new Exception("教师的帐号不能为空!");
			}
			SysUser teacher = sysUserDao.queryByAccount(user.getAccount());
			if ( null != teacher) {
				throw new Exception("教师的帐号不能重复!");
			}
		}
		user.setLeaseholderId(leaseholderId);
		user.setCreateTime(new Timestamp(new Date().getTime()));
		user.setCreator(loginName);
		if ( null == user.getBirthday()) {
			user.setBirthday(DateUtil.stringToDate("1900-01-01"));
		}
		SysUser ouser = querySysUser(leaseholderId, uf.getGh());
		if ( null != ouser) {
			throw new RuntimeException("工号["+uf.getGh()+"]已经存在");
		}
		sysUserDao.add(user);
	}
	
	public void updateSysUser(UserForm uf,String leaseholderId,String loginName) throws Exception {
		SysUser od = querySysUser(leaseholderId,uf.getStudentId());
		if ( null == od) {
			throw new Exception("用户不存在！");
		}
		SysUser user = uf.toSysUser();
		//如果是老师，验证帐号唯一
		if ("teacher".equals(user.getType())) {
			if (StringUtils.isEmpty(user.getAccount())) {
				throw new Exception("教师的帐号不能为空!");
			}
			SysUser teacher = sysUserDao.queryByAccount(user.getAccount());
			if (null != teacher && (!user.getStudentId().equals(teacher.getStudentId()))) {
				throw new Exception("教师的帐号不能重复!");
			}
		}
		user.setId(od.getId());
		user.setLeaseholderId(leaseholderId);
		user.setUpdateTime(new Timestamp(new Date().getTime()));
		user.setUpdator(loginName);
		if (null == user.getBirthday()) {
			if ( null == od.getBirthday()) {
				user.setBirthday(DateUtil.stringToDate("1900-01-01"));
			}else {
				user.setBirthday(od.getBirthday());
			}
		}
		user.setCreateTime(od.getCreateTime());//BeanUtils.copyProperties Date类型为空会报错
		BeanUtils.copyProperties(od, user);
		sysUserDao.update(od);
	}
	
	public PageResult query(SysUser uesr,String begin,String end,int pageIndex,int pageSize) {
		List<SysUser> users = sysUserDao.query(uesr,begin,end, pageIndex, pageSize);
		if (null != users && users.size() > 0 ) {
			for ( int i = 0 ; i < users.size() ; i ++) {
				SysUser su = users.get(i);
				if ( null != su.getType()) {
					Dictionary dictionary = dictionaryDao.queryByCode(su.getType());
					if ( null != dictionary) {
						su.setTypeName(dictionary.getName());
					}
				}
				if ( null != su.getPost()) {
					Dictionary dictionaryPost = dictionaryDao.queryByCode(su.getPost());
					if ( null != dictionaryPost) {
						su.setPostName(dictionaryPost.getName());
					}
				}
				
				if ( null != su.getSexCode()) {
					Dictionary dictionarySex = dictionaryDao.queryByCode(su.getSexCode());
					if ( null != dictionarySex) {
						su.setSexName(dictionarySex.getName());
					}
				}
			}
		}
		int count = sysUserDao.queryCount(uesr,begin,end);
		return new PageResult(count,pageSize,pageIndex,users);
	}
	
	public void delete(String leaseholderId,int id) {
		sysUserDao.delete(leaseholderId, id);
	}
	
	public SysUser queryByAccount(String account) {
		return sysUserDao.queryByAccount(account);
	}
	
	public List<SysUser> queryTeachers(String tanentId,List<String> studentId,int pageIndex,int pageSize) {
		return sysUserDao.queryTeachers(tanentId,studentId,pageIndex,pageSize);
	}
}
