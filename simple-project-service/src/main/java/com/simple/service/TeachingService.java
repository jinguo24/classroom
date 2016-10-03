package com.simple.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.common.excel.DownLoadExcel;
import com.simple.common.excel.DownLoadExcutor;
import com.simple.common.util.DateUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.dao.CourseDao;
import com.simple.dao.SysUserDao;
import com.simple.dao.TeachingLogCourseDao;
import com.simple.dao.TeachingLogDao;
import com.simple.dao.TeachingStudentDao;
import com.simple.model.Course;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.TeachingLog;
import com.simple.model.TeachingLogCourse;
import com.simple.model.TeachingStudent;
import com.simple.model.api.KaiKe;
import com.simple.model.api.KeChengXiLie;

@Service
public class TeachingService {

	@Autowired
	private TeachingLogDao teachingLogDao;
	@Autowired
	private TeachingStudentDao teachingStudentDao;
	@Autowired
	private TeachingLogCourseDao teachingLogCourseDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private SysUserDao sysUserDao;
	
	public TeachingLog queryBySkbh(String skbh) {
		return teachingLogDao.queryBySkbh(skbh);
	}
	
	public String addTeachingLog(KaiKe kaike){
		TeachingLog tl = new TeachingLog();
		String skbh = PrimaryKeyUtil.getId();
		tl.setSkbh(skbh);
		tl.setTanentid(kaike.getTanentId());
		tl.setXxbh(kaike.getXxbh());
		tl.setXxmc(kaike.getXxmc());
		tl.setNjbh(kaike.getNjbh());
		tl.setNjmc(kaike.getNjmc());
		tl.setBjbh(kaike.getBjbh());
		tl.setBjmc(kaike.getBjmc());
		tl.setXn(DateUtil.getXueNian());
		tl.setSksj(new Date());
		tl.setGh(kaike.getGh());
		tl.setXm(kaike.getXm());
		teachingLogDao.addTeachingLog(tl);
		return skbh;
	}
	
	public void addTeachingCourse(String tanentId,String skbh,String kcbh) {
		TeachingLogCourse tc = new TeachingLogCourse();
		tc.setTanentid(tanentId);
		tc.setSkbh(skbh);
		tc.setKcbh(kcbh);
		
		Course cq = new Course();
		cq.setCode(kcbh);
		List<Course> courses = courseDao.query(cq,1,1);
		if ( null != courses && courses.size() > 0  ) {
			Course c = courses.get(0);
			tc.setKcmc(c.getName());
			tc.setKcxlbh(c.getKcxlbh());
			tc.setKcxlmc(c.getKcxlmc());
			tc.setTeacherWenjuan(c.getTeacherWenjuan());
			tc.setStudentWenjuan(c.getStudentWenjuan());
			tc.setOtherWenjuan(c.getOtherWenjuan());
			tc.setKtzyWenjuan(c.getKtzyWenjuan());
			tc.setKhzyWenjuan(c.getKhzyWenjuan());
			tc.setKksj(new Date());
			tc.setXn(DateUtil.getXueNian());
			teachingLogCourseDao.addTeachingLogCourse(tc);
		}
	}
	
	public void addTeachingStudent(String tanentId,String skbh,String[] gh) {
		TeachingLog teachingLogQuery = new TeachingLog();
		teachingLogQuery.setTanentid(tanentId);
		teachingLogQuery.setSkbh(skbh);
		String bjbh = "";
		String bjmc = "";
		String njbh = "";
		String njmc = "";
		List<TeachingLog> teachingLogs =  teachingLogDao.query(teachingLogQuery, null, null, 1, 1,null);
		if ( null != teachingLogs && teachingLogs.size() > 0 ) {
			TeachingLog tlog = teachingLogs.get(0);
			bjbh = tlog.getBjbh();
			bjmc = tlog.getBjmc();
			njbh = tlog.getNjbh();
			njmc = tlog.getNjmc();
		}
		if (null != gh) {
			for (int i = 0 ; i < gh.length ; i ++) {
				TeachingStudent ts = new TeachingStudent();
				ts.setTanentid(tanentId);
				ts.setSkbh(skbh);
				ts.setGh(gh[i]);
				//ts.setXm(name[i]);
				ts.setCjsj(new Date());
				ts.setBjbh(bjbh);
				ts.setBjmc(bjmc);
				ts.setXn(DateUtil.getXueNian());
				ts.setNjbh(njbh);
				ts.setNjmc(njmc);
				//ts.setCjr(loginName);
				teachingStudentDao.addTeachingStudent(ts);
			}
		}
	}
	
	public PageResult query(TeachingLog tclass,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		List<TeachingLog> logs = queryLogs(tclass, beginTime, endTime, pageIndex, pageSize,skbhs);
		int count = teachingLogDao.queryCount(tclass,beginTime,endTime,skbhs);
		return new PageResult(count,pageSize,pageIndex,logs);
	}
	
	private List<TeachingLog> queryLogs(TeachingLog tclass,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		List<TeachingLog> logs =  teachingLogDao.query(tclass,beginTime,endTime, pageIndex, pageSize,skbhs);
		if ( null != logs) {
			for ( int i = 0 ; i < logs.size() ; i ++) {
				TeachingLog tl = logs.get(i);
				int count = teachingStudentDao.queryCount(tl.getTanentid(),tl.getSkbh());
				tl.setStduentCount(count);
			}
		}
		return logs;
	}
	
	public PageResult queryCourseLog(TeachingLog tclass,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		List<TeachingLog> logs = this.queryCourseLogList(tclass, beginTime, endTime, pageIndex, pageSize, skbhs);
		int count = teachingLogDao.queryCourseLogCount(tclass,beginTime,endTime,skbhs);
		return new PageResult(count,pageSize,pageIndex,logs);
	}
	
	private List<TeachingLog> queryCourseLogList(TeachingLog tclass,String beginTime,String endTime,int pageIndex,int pageSize,List<String> skbhs) {
		List<TeachingLog> logs = teachingLogDao.queryCourseLog(tclass, beginTime, endTime, pageIndex, pageSize, skbhs);
		if ( null != logs) {
			for ( int i = 0 ; i < logs.size() ; i ++) {
				TeachingLog tl = logs.get(i);
				TeachingLog tsearh = new TeachingLog();
				tsearh.setTanentid(tl.getTanentid());
				tsearh.setSkbh(tl.getSkbh());
				int count = teachingStudentDao.queryCount(tl.getTanentid(),tl.getSkbh());
				tl.setStduentCount(count);
			}
		}
		return logs;
	}
	
	public PageResult getStudents(String leaseholderId,String skbh,int pageIndex,int pageSize) {
		List<TeachingStudent> studetns =  teachingStudentDao.queryBySKBH(leaseholderId, skbh,pageIndex,pageSize);
		if ( null != studetns) {
			for ( int i = 0 ; i < studetns.size() ; i ++ ) {
				TeachingStudent ts = studetns.get(i);
				SysUser user = sysUserDao.querySysUser(ts.getTanentid(), ts.getGh());
				if ( null != user ) {
					ts.setXm(user.getName());
				}
			}
		}
		int count = teachingStudentDao.queryCount(leaseholderId, skbh);
		return new PageResult(count,pageSize,pageIndex,studetns);
	}
	
	public ResponseInfo download(TeachingLog tclass,String beginTime,String endTime,List<String> skbhs) {
		List<TeachingLog> logs = queryCourseLogList(tclass, beginTime, endTime, 1, 10000,skbhs);
		String[] titles = new String[]{"授课编号","学年","年级","班级","班主任","课程系列","课程名称","上课学生人数","授课时间"};
		return DownLoadExcel.download(logs, Arrays.asList(titles), new DownLoadExcutor() {
			@Override
			public List<String> getCellValues(Object o) {
				TeachingLog log = (TeachingLog) o;
				List<String> sl = new ArrayList<String>();
				sl.add(log.getSkbh());
				sl.add(log.getXn().toString());
				sl.add(log.getNjmc());
				sl.add(log.getBjmc());
				sl.add(log.getXm()+"["+log.getGh()+"]");
				sl.add(log.getKcxlmc());
				sl.add(log.getKcmc());
				sl.add(String.valueOf(log.getStduentCount()));
				sl.add(DateUtil.date2AllString(log.getSksj()));
				return sl;
			}
		});
	}
	
	public List<String> querySKBH(String tanentId,String kcbh,String kcxlbh) {
		return teachingLogCourseDao.querySKBH(tanentId, kcbh, kcxlbh);
	}
	
	public List<String> getTeacherSkbhs(String tanentId,String gh) {
		return teachingLogDao.getTeacherSkbhs(tanentId, gh);
	}
	
	public List<KeChengXiLie> queryXiLie(String tanentId,List<String> skbhs) {
		return teachingLogCourseDao.queryXiLie(tanentId,skbhs);
	}
}
