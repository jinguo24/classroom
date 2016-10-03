package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.SysUser;

@Repository
@DatabaseTemplate("st_all")
public class SysUserDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public SysUser querySysUser(String leaseholderId,String studentId) {
		Map map = new HashMap();
		map.put("leaseholderId", leaseholderId);
		map.put("studentId", studentId);
		return this.sqlSession.selectOne("sysUser.queryByStudentId", map);
	}
	
	public void add(SysUser user) {
		this.sqlSession.insert("sysUser.insert",user);
	}
	
	public void update(SysUser user) {
		this.sqlSession.update("sysUser.update",user);
	}
	
	public void updatePassword(String leaseholderId,String studentId,String passowrd) {
		Map param = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("password", passowrd);
		param.put("studentId", studentId);
		this.sqlSession.update("sysUser.updatePassword",param);
	}
	
	public List<SysUser> query(SysUser user,String begin,String end,int pageIndex,int pageSize) {
		Map param  = new HashMap();
		param.put("leaseholderId", user.getLeaseholderId());
		param.put("name", user.getName());
		param.put("studentId", user.getStudentId());
		param.put("type", user.getType());
		param.put("post", user.getPost());
		param.put("beginTime", begin);
		param.put("endTime", end);
		if (pageIndex < 1) {
			pageIndex =1 ;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("end", pageSize);
		return this.sqlSession.selectList("sysUser.queryList",param);
	}
	
	public int queryCount(SysUser user, String begin, String end) {
		Map param  = new HashMap();
		param.put("leaseholderId", user.getLeaseholderId());
		param.put("name", user.getName());
		param.put("studentId", user.getStudentId());
		param.put("type", user.getType());
		param.put("post", user.getPost());
		param.put("beginTime", begin);
		param.put("endTime", end);
		return this.sqlSession.selectOne("sysUser.queryCount",param);
	}
	
	public void delete(String leaseholderId,int id){
		Map param  = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("id", id);
		this.sqlSession.delete("sysUser.delete",param);
	}
	
	public void delete(String leaseholderId,List<String> ghs) {
		Map param  = new HashMap();
		param.put("leaseholderId", leaseholderId);
		param.put("ghs", ghs);
		this.sqlSession.delete("sysUser.deleteBatchs",param);
	}
	
	
	public SysUser queryByAccount(String account) {
		return this.sqlSession.selectOne("sysUser.queryByAccount",account);
	}
	
	public List<SysUser> queryTeachers(String tanentId,List<String> studentId,int pageIndex,int pageSize) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		if( null == studentId || studentId.size() == 0) {
			studentId = null;
		}
		param.put("ghs", studentId);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("end", pageSize);
		return this.sqlSession.selectList("sysUser.queryTeachers",param);
	}
	
}
