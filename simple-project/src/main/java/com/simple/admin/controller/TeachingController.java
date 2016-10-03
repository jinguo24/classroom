package com.simple.admin.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.CourseImage;
import com.simple.model.CourseWenjuanTeacher;
import com.simple.model.CourseXlImage;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.TeachingLog;
import com.simple.model.api.KeCheng;
import com.simple.model.api.KeChengXiLie;
import com.simple.service.ClassRegistorService;
import com.simple.service.CourseService;
import com.simple.service.DecorateService;
import com.simple.service.TeachingService;
@Controller
@RequestMapping(value = "/teaching")
public class TeachingController {
	
	private static final Logger log = LoggerFactory.getLogger(TeachingController.class);

	@Autowired
	TeachingService teachingService;
	@Autowired
	ClassRegistorService registerService;
	@Autowired
	CourseService courseService;
	@Autowired
	DecorateService decorateService;
	
	@RequestMapping(value = "/log/query",method=RequestMethod.GET)
	@ResponseBody
	public String query(TeachingLog tclass,String xlbh,String kcbh,String begin,String end,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setTanentid(leaseholderId);
		List<String> skbhs = teachingService.querySKBH(leaseholderId, kcbh, xlbh);
		PageResult dictionarys = null;
		if ( null == skbhs || skbhs.size() == 0 ) {
			dictionarys = new PageResult(0,pageSize,pageIndex,null);
		}else {
			dictionarys = teachingService.queryCourseLog(tclass,begin,end, pageIndex, pageSize,skbhs);
		}
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "/log/download",method=RequestMethod.GET)
	@ResponseBody
	public String download(TeachingLog tclass,String xlbh,String kcbh,String begin,String end,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setTanentid(leaseholderId);
		List<String> skbhs = teachingService.querySKBH(leaseholderId, kcbh, xlbh);
		return JSONObject.toJSONString(teachingService.download(tclass, begin, end,skbhs));
	}
	
	@RequestMapping(value = "/log/student",method=RequestMethod.GET)
	@ResponseBody
	public String student(String skbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			PageResult skbhs = teachingService.getStudents(LoginUserUtil.getLeaseholderId(request), skbh,pageIndex,pageSize);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", skbhs);
		}catch(Exception e) {
			e.printStackTrace();
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), null);
		}
	}
	
	@RequestMapping(value = "teacherXilie",method=RequestMethod.POST)
	@ResponseBody
	public String teacherXilie(HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser user = LoginUserUtil.getCurrentUser(request);
			//根据租户查询授权课程列表id
			List<String> courseIds =  registerService.getAuthCourseIds(user.getLeaseholderId());
			//根据课程Id查询系列ids
			if ( null != courseIds && courseIds.size() > 0 ) {
				List<String> xilieIds = courseService.getXiLieBHList(courseIds);
				//根据系列ids查询
				if ( null != xilieIds && xilieIds.size() > 0 ) {
					List<KeChengXiLie> registers = courseService.queryKeChengXiLieList(xilieIds,1,1000);
					/**
					for (int i = 0 ; i < registers.size(); i++) {
						KeChengXiLie kc = registers.get(i);
						CourseXlImage  ci = decorateService.queryCourseXlImage(user.getLeaseholderId(), null, kc.getKcxlbh());
						if ( null != ci ) {
							kc.setKctp(ci.getKcxltp());
						}
					}*/
					return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：未查询到授权课程系列.", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "teacherCourse",method=RequestMethod.POST)
	@ResponseBody
	public String teacherCourse(String xlbh,String njbh,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser user = LoginUserUtil.getCurrentUser(request);
			//先从系列里面查询出课程列表
			List<String>  courseBhlist = registerService.getAuthCourseIds(user.getLeaseholderId());
			if ( null != courseBhlist && courseBhlist.size() > 0) {
				//根据租户和课程ids来查询课程
				List<KeCheng> registers = courseService.queryKeChengList(courseBhlist,xlbh,njbh,1,1000);
				if (null != registers && registers.size() > 0 ) {
					for (int i = 0 ; i < registers.size(); i++) {
						KeCheng kc = registers.get(i);
						CourseImage  ci = decorateService.queryCourseImage(user.getLeaseholderId(), null, kc.getCode());
						if ( null != ci ) {
							kc.setKctp(ci.getKctp());
						}
					}
					return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", registers);
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：未查询到授权的该系列课程", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
}
