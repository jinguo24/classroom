package com.simple.admin.controller;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.DateUtil;
import com.simple.model.Course;
import com.simple.model.CourseForm;
import com.simple.model.CourseImage;
import com.simple.model.CourseWenjuanTeacher;
import com.simple.model.CourseXl;
import com.simple.model.CourseXlImage;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.Template;
import com.simple.model.TemplateForm;
import com.simple.model.api.KeCheng;
import com.simple.model.api.KeChengInfo;
import com.simple.model.api.KeChengXiLie;
import com.simple.service.ClassRegistorService;
import com.simple.service.CourseService;
import com.simple.service.CourseWenjuanTeacherService;
import com.simple.service.DecorateService;
import com.simple.service.TemplateService;
@Controller
@RequestMapping(value = "/course")
public class CourseController {
	
	private static final Logger log = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	CourseService courseService;
	@Autowired
	TemplateService templateService;
	@Autowired
	ClassRegistorService registerService;
	@Autowired
	DecorateService decorateService;
	@Autowired
	CourseWenjuanTeacherService courseWenjuanTeacherService;
	
	
	@RequestMapping(value = "queryRootTemplate",method=RequestMethod.GET)
	@ResponseBody
	public String queryRootTemplate(String code,String category,String type,String name,String begin,String end,int pageIndex,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		PageResult dictionarys =  templateService.query(LoginUserUtil.getLeaseholderId(request), code,begin,end, type,name,category,null, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(Course tclass,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		PageResult dictionarys =  courseService.query(tclass,pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "add",method=RequestMethod.POST)
	@ResponseBody
	public String addCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			CourseForm cf = JSON.parseObject(AjaxWebUtil.getRequestPayload(request), CourseForm.class);
			courseService.addCourse(cf,LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", null);
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "detail",method=RequestMethod.GET)
	@ResponseBody
	public String detail(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			Course cq = new Course();
			cq.setCode(code);
			List<Course> cs =  courseService.queryList(cq, 1, 1);
			CourseForm cf = null;
			if ( null != cs && cs.size() > 0 ) {
				Course c  = cs.get(0);
				cf = c.castToForm();
				if (!StringUtils.isEmpty(cf.getTmpId1())) {
					Template t1 = queryTemplate(cf.getTmpId1());
					if ( null != t1) {
						cf.setTmpName1(t1.getName());
					}
				}
				if (!StringUtils.isEmpty(cf.getTmpId2())) {
					Template t2 = queryTemplate(cf.getTmpId2());
					if ( null != t2) {
						cf.setTmpName2(t2.getName());
					}
				}
				
				if (!StringUtils.isEmpty(cf.getTmpId3())) {
					Template t3 = queryTemplate(cf.getTmpId3());
					if ( null != t3) {
						cf.setTmpName3(t3.getName());
					}
				}
				
				if (!StringUtils.isEmpty(cf.getTmpId4())) {
					Template t4 = queryTemplate(cf.getTmpId4());
					if ( null != t4) {
						cf.setTmpName4(t4.getName());
					}
				}
				
				if (!StringUtils.isEmpty(cf.getTmpId5())) {
					Template t5 = queryTemplate(cf.getTmpId5());
					if ( null != t5) {
						cf.setTmpName5(t5.getName());
					}
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", cf);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping(value = "view",method=RequestMethod.GET)
	@ResponseBody
	public String view(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			KeChengInfo registers = courseService.queryKeChengInfo(code);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	
	private Template queryTemplate(String templateCode) {
		List<Template> templateList = templateService.queryList(null, templateCode, null, null, null, null, null,null, 1, 1);
		if ( null != templateList && templateList.size() > 0) {
			return templateList.get(0);
		}
		return null;
	}
	
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		try {
			CourseForm cf = JSON.parseObject(AjaxWebUtil.getRequestPayload(request), CourseForm.class);
			Course cq = new Course();
			cq.setCode(cf.getCode());
			List<Course> cs =  courseService.queryList(cq, 1, 1);
			if ( null == cs || cs.size() == 0 ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败：未查询到记录", null);
			}
			courseService.update(cs.get(0), cf);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "xilie",method=RequestMethod.GET)
	@ResponseBody
	public String xilie(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CourseXl> xlie=  courseService.queryXl(null, null);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", xlie);
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "xilieChildren",method=RequestMethod.GET)
	@ResponseBody
	public String xilie(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<CourseXl> xlie=  courseService.queryXlByParentCode(code);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", xlie);
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	private String authXilieByTanentId(String tanentId,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			//根据租户查询授权课程列表id
			List<String> courseIds =  registerService.getAuthCourseIds(tanentId);
			//根据课程Id查询系列ids
			if ( null != courseIds && courseIds.size() > 0 ) {
				List<String> xilieIds = courseService.getXiLieBHList(courseIds);
				//根据系列ids查询
				if ( null != xilieIds && xilieIds.size() > 0 ) {
					PageResult xiliePage = courseService.queryKeChengXiLie(xilieIds,pageIndex,pageSize);
					List<KeChengXiLie> registers = xiliePage.getDatas();
					for (int i = 0 ; i < registers.size(); i++) {
						KeChengXiLie kc = registers.get(i);
						CourseXlImage  ci = decorateService.queryCourseXlImage(tanentId, null, kc.getKcxlbh());
						if ( null != ci ) {
							kc.setKctp(ci.getKcxltp());
						}
					}
					return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", xiliePage);
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", new PageResult(0,pageSize,pageIndex,null));
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "authXilie",method=RequestMethod.GET)
	@ResponseBody
	public String authXilie(String tanentId,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		return this.authXilieByTanentId(tanentId, pageIndex, pageSize, request, response);
	}
	
	@RequestMapping(value = "currentAuthXilie",method=RequestMethod.GET)
	@ResponseBody
	public String currentAuthXilie(int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		return this.authXilieByTanentId(LoginUserUtil.getLeaseholderId(request), pageIndex, pageSize, request, response);
	}
	
	
	private String authKeChengByTanentId(String tanentId,String gh,String xlbh,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			//先从系列里面查询出课程列表
			List<String>  courseBhlist = registerService.getAuthCourseIds(tanentId);
			if ( null != courseBhlist && courseBhlist.size() > 0) {
				//根据租户和课程ids来查询课程
				PageResult registerPage = courseService.queryKeCheng(courseBhlist,xlbh,njbh,pageIndex,pageSize);
				List<KeCheng> registers = registerPage.getDatas();
				if (null != registers && registers.size() > 0 ) {
					for (int i = 0 ; i < registers.size(); i++) {
						KeCheng kc = registers.get(i);
						CourseImage  ci = decorateService.queryCourseImage(tanentId, null, kc.getCode());
						if ( null != ci ) {
							kc.setKctp(ci.getKctp());
						}
						if (!StringUtils.isEmpty(gh)) {
							CourseWenjuanTeacher courseTeacher = courseWenjuanTeacherService.query(kc.getCode(), gh);
							if ( null != courseTeacher ) {
								if ( null != courseTeacher.getBegin()) {
									kc.setBegin(DateUtil.date2AllString(courseTeacher.getBegin()));
								}
								if ( null != courseTeacher.getEnd()) {
									kc.setEnd(DateUtil.date2AllString(courseTeacher.getEnd()));
								}
							}
						}
					}
				}
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registerPage);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", new PageResult(0,pageSize,pageIndex,null));
		}catch(Exception e) {
			log.error("更新失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "authKeCheng",method=RequestMethod.GET)
	@ResponseBody
	public String authKeCheng(String tanentId,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		return this.authKeChengByTanentId(tanentId,null,null,njbh, pageIndex, pageSize, request, response);
	}
	
	@RequestMapping(value = "currentAuthKeCheng",method=RequestMethod.GET)
	@ResponseBody
	public String currentAuthKeCheng(String xlbh,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		return this.authKeChengByTanentId(LoginUserUtil.getLeaseholderId(request),null,xlbh,njbh, pageIndex, pageSize, request, response);
	}
	
	@RequestMapping(value = "currentTeacherAuthKeCheng",method=RequestMethod.GET)
	@ResponseBody
	public String currentTeacherAuthKeCheng(String xlbh,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		return this.authKeChengByTanentId(LoginUserUtil.getLeaseholderId(request),LoginUserUtil.getCurrentUser(request).getStudentId(),xlbh,njbh, pageIndex, pageSize, request, response);
	}
	
	private void addCourseWenjuan(SysUser user,String kcbh,String teacherWenjuan,String studentWenjuan,String ktzyWenjuan,String khzyWenjuan
			,String otherWenjuan,String begin,String end) {
		CourseWenjuanTeacher courseWenjuan = new CourseWenjuanTeacher();
		courseWenjuan.setGh(user.getStudentId());
		courseWenjuan.setXm(user.getName());
		courseWenjuan.setTeacherWenjuan(teacherWenjuan);
		courseWenjuan.setStudentWenjuan(studentWenjuan);
		courseWenjuan.setOtherWenjuan(otherWenjuan);
		courseWenjuan.setKtzyWenjuan(ktzyWenjuan);
		courseWenjuan.setKhzyWenjuan(khzyWenjuan);
		courseWenjuan.setKcbh(kcbh);
		courseWenjuan.setTanentId(user.getLeaseholderId());
		if (!StringUtils.isEmpty(begin)) {
			courseWenjuan.setBegin(DateUtil.stringToDate(begin));
		}else {
			courseWenjuan.setBegin(new Date());
		}
		if (!StringUtils.isEmpty(end)) {
			courseWenjuan.setEnd(new Timestamp(DateUtil.stringToDate(end).getTime()));
		}else {
			courseWenjuan.setEnd(new Timestamp(DateUtil.stringToDate("2300-12-10").getTime()));
		}
		courseWenjuanTeacherService.add(courseWenjuan);
	}
	
	private void updateCourseWenjuan(SysUser user,String kcbh,String teacherWenjuan,String studentWenjuan,String ktzyWenjuan,String khzyWenjuan
			,String otherWenjuan,String begin,String end) {
		CourseWenjuanTeacher courseWenjuan = new CourseWenjuanTeacher();
		courseWenjuan.setGh(user.getStudentId());
		courseWenjuan.setXm(user.getName());
		courseWenjuan.setTeacherWenjuan(teacherWenjuan);
		courseWenjuan.setStudentWenjuan(studentWenjuan);
		courseWenjuan.setOtherWenjuan(otherWenjuan);
		courseWenjuan.setKtzyWenjuan(ktzyWenjuan);
		courseWenjuan.setKhzyWenjuan(khzyWenjuan);
		courseWenjuan.setKcbh(kcbh);
		courseWenjuan.setTanentId(user.getLeaseholderId());
		if (!StringUtils.isEmpty(begin)) {
			courseWenjuan.setBegin(DateUtil.stringToDate(begin));
		}else {
			courseWenjuan.setBegin(new Date());
		}
		if (!StringUtils.isEmpty(end)) {
			courseWenjuan.setEnd(new Timestamp(DateUtil.stringToDate(end).getTime()));
		}else {
			courseWenjuan.setEnd(new Timestamp(DateUtil.stringToDate("2100-10-01").getTime()));
		}
		courseWenjuanTeacherService.update(courseWenjuan);
	}
	
	@RequestMapping(value = "updateCourseWenjuan",method=RequestMethod.POST)
	@ResponseBody
	public String updateCourseWenjuan(String kcbh,String teacherWenjuan,String studentWenjuan,String ktzyWenjuan,String khzyWenjuan
			,String otherWenjuan,String begin,String end,HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser user = LoginUserUtil.getCurrentUser(request);
			if (user.getStudentId()==null) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败:请用教师登录!", null);
			}
			CourseWenjuanTeacher courseWenjuan = courseWenjuanTeacherService.query(kcbh, user.getStudentId());
			if ( null == courseWenjuan) {
				addCourseWenjuan(user, kcbh, teacherWenjuan, studentWenjuan, ktzyWenjuan, khzyWenjuan, otherWenjuan, begin, end);
			}else {
				updateCourseWenjuan(user, kcbh, teacherWenjuan, studentWenjuan, ktzyWenjuan, khzyWenjuan, otherWenjuan, begin, end);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"设置成功", null);
		}catch(Exception e) {
			log.error("设置失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryCourseWenjuan",method=RequestMethod.GET)
	@ResponseBody
	public String queryCourseWenjuan(String kcbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser user = LoginUserUtil.getCurrentUser(request);
			CourseWenjuanTeacher courseWenjuan = courseWenjuanTeacherService.query(kcbh, user.getStudentId());
			if ( null == courseWenjuan) {
				Course cq = new Course();
				cq.setCode(kcbh);
				List<Course> cs =  courseService.queryList(cq, 1, 1);
				if ( null != cs && cs.size() > 0 ) {
					Course c = cs.get(0);
					courseWenjuan = new CourseWenjuanTeacher();
					courseWenjuan.setTeacherWenjuan(c.getTeacherWenjuan());
					courseWenjuan.setStudentWenjuan(c.getStudentWenjuan());
					courseWenjuan.setOtherWenjuan(c.getOtherWenjuan());
					courseWenjuan.setKtzyWenjuan(c.getKtzyWenjuan());
					courseWenjuan.setKhzyWenjuan(c.getKhzyWenjuan());
				}
			}
			if (!StringUtils.isEmpty(courseWenjuan.getTeacherWenjuan())) {
				TemplateForm tfTeacher = templateService.queryByCode(null, courseWenjuan.getTeacherWenjuan(), false, false);
				if ( null != tfTeacher) {
					courseWenjuan.setTeacherWenjuanName(tfTeacher.getTitle());
				}
			}
			
			if (!StringUtils.isEmpty(courseWenjuan.getStudentWenjuan())) {
				TemplateForm tfStudent = templateService.queryByCode(null, courseWenjuan.getStudentWenjuan(), false, false);
				if ( null != tfStudent) {
					courseWenjuan.setStudentWenjuanName(tfStudent.getTitle());
				}
			}
			
			if (!StringUtils.isEmpty(courseWenjuan.getKtzyWenjuan())) {
				TemplateForm tfKtzy = templateService.queryByCode(null, courseWenjuan.getKtzyWenjuan(), false, false);
				if ( null != tfKtzy) {
					courseWenjuan.setKtzyWenjuanName(tfKtzy.getTitle());
				}
			}
			
			if (!StringUtils.isEmpty(courseWenjuan.getKhzyWenjuan())) {
				TemplateForm tfKhzy = templateService.queryByCode(null, courseWenjuan.getKhzyWenjuan(), false, false);
				if ( null != tfKhzy) {
					courseWenjuan.setKhzyWenjuanName(tfKhzy.getTitle());
				}
			}
			
			if (!StringUtils.isEmpty(courseWenjuan.getOtherWenjuan())) {
				TemplateForm tfOther = templateService.queryByCode(null, courseWenjuan.getOtherWenjuan(), false, false);
				if ( null != tfOther) {
					courseWenjuan.setOtherWenjuanName(tfOther.getTitle());
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", courseWenjuan);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
}
