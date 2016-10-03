package com.simple.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.simple.common.mybatis.annotation.DatabaseTemplate;
import com.simple.common.mybatis.dao.BaseIbatisDao;
import com.simple.model.TClass;
import com.simple.model.TeachingLog;

@Repository
@DatabaseTemplate("st_all")
public class TeachingLogDao extends BaseIbatisDao {

	Logger logger = LoggerFactory.getLogger(getClass());

	public void addTeachingLog(TeachingLog teachingLog) {
		this.sqlSession.insert("teachingLog.insert", teachingLog);
	}
	
	public List<TeachingLog> query(TeachingLog classentity,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("skbh", classentity.getSkbh());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		if ( null == skbhs || skbhs.size() == 0 ) {
			skbhs = null;
		}
		param.put("skbhs", skbhs);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("teachingLog.query",param);
	}
	
	public int queryCount(TeachingLog classentity,String beginTime,String endTime,List<String> skbhs) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("skbh", classentity.getSkbh());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		if ( null == skbhs || skbhs.size() == 0 ) {
			skbhs = null;
		}
		param.put("skbhs", skbhs);
		return this.sqlSession.selectOne("teachingLog.queryCount",param);
	}
	
	public List<TeachingLog> queryCourseLog(TeachingLog classentity,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("skbh", classentity.getSkbh());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		param.put("kcxlbh", classentity.getKcxlbh());
		param.put("kcxlmc", classentity.getKcxlmc());
		param.put("kcbh", classentity.getKcbh());
		param.put("kcmc", classentity.getKcmc());
		if ( null == skbhs || skbhs.size() == 0 ) {
			skbhs = null;
		}
		param.put("skbhs", skbhs);
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		param.put("begin", (pageIndex-1)*pageSize);
		param.put("size", pageSize);
		return this.sqlSession.selectList("teachingLog.queryCourseLog",param);
	}
	
	public int queryCourseLogCount(TeachingLog classentity,String beginTime,String endTime,List<String> skbhs) {
		Map param = new HashMap();
		param.put("leaseholderId", classentity.getTanentid());
		param.put("njbh", classentity.getNjbh());
		param.put("njmc", classentity.getNjmc());
		param.put("bjbh", classentity.getBjbh());
		param.put("bjmc", classentity.getBjmc());
		param.put("skbh", classentity.getSkbh());
		param.put("beginTime", beginTime);
		param.put("endTime", endTime);
		param.put("gh", classentity.getGh());
		param.put("xm", classentity.getXm());
		param.put("xn", classentity.getXn());
		param.put("kcxlbh", classentity.getKcxlbh());
		param.put("kcxlmc", classentity.getKcxlmc());
		param.put("kcbh", classentity.getKcbh());
		param.put("kcmc", classentity.getKcmc());
		if ( null == skbhs || skbhs.size() == 0 ) {
			skbhs = null;
		}
		param.put("skbhs", skbhs);
		return this.sqlSession.selectOne("teachingLog.queryCourseLogCount",param);
	}
	
	public TeachingLog queryBySkbh(String skbh) {
		return this.sqlSession.selectOne("teachingLog.queryBySkbh",skbh);
	}
	
	public List<String> getTeacherSkbhs(String tanentId,String gh) {
		Map param = new HashMap();
		param.put("tanentId", tanentId);
		param.put("gh", gh);
		return this.sqlSession.selectList("teachingLog.queryTeacherSkbhs",param);
	}
}
