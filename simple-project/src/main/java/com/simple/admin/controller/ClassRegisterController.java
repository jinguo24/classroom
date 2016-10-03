package com.simple.admin.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.ClassAuthForm;
import com.simple.model.ClassRegister;
import com.simple.model.ClassRegisterForm;
import com.simple.model.PageResult;
import com.simple.model.XueXiao;
import com.simple.service.ClassRegistorService;
@Controller
@RequestMapping(value = "/tclassRegister")
public class ClassRegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(ClassRegisterController.class);

	@Autowired
	ClassRegistorService classRegistorService;
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(ClassRegister tclass,String begin,String end,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		PageResult dictionarys =  classRegistorService.query(tclass,begin,end, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "register",method=RequestMethod.POST)
	@ResponseBody
	public String uploadStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegisterForm cf = JSON.parseObject(AjaxWebUtil.getRequestPayload(request), ClassRegisterForm.class);
			if ( null != cf ) {
				int count = classRegistorService.queryCount(cf.getZhh(), null);
				if (count > 0 ) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败：教室编号重复.", "创建失败：教室编号重复.");
				}
				classRegistorService.addClassRegister(cf,LoginUserUtil.getCurrentUser(request).getName());;
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", null);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:格式解析错误", "格式解析错误");
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "xx",method=RequestMethod.GET)
	@ResponseBody
	public String xx(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ClassRegister> registers = classRegistorService.queryList(new ClassRegister(), null, null, 1, 100);
			List<XueXiao> xuexiaos = null;
			if ( null != registers && registers.size() > 0 ) {
				xuexiaos = new ArrayList<XueXiao>();
				for (int i = 0 ; i < registers.size() ; i ++) {
					ClassRegister cr = registers.get(i);
					XueXiao xx = new XueXiao();
					xx.setXxbh(cr.getXxbh());
					xx.setXxmc(cr.getXxmc());
					xuexiaos.add(xx);
				}
			}
			PageResult pr = new PageResult(0,1,1,xuexiaos);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "detail",method=RequestMethod.GET)
	@ResponseBody
	public String detail(String tanentId,String jsbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegister cr = classRegistorService.getClassRegister(tanentId, jsbh);
			if (null == cr ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败：未查询到数据.", "查询失败：未查询到数据.");
			}
			ClassRegisterForm cf = cr.castToForm();
			classRegistorService.setImages(cr, cf);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", cf);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegisterForm cf = JSON.parseObject(AjaxWebUtil.getRequestPayload(request), ClassRegisterForm.class);
			ClassRegister crq = new ClassRegister();
			crq.setTanentid(cf.getTanentId());
			crq.setJsbh(cf.getOldJsbh());
			List<ClassRegister> registers = classRegistorService.queryList(crq, null, null, 1, 1);
			if ( null == registers || registers.size() == 0 ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败：未查询到数据.", null);
			}
			ClassRegister cr = registers.get(0);
			classRegistorService.update(cr, cf);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"更新失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "auth",method=RequestMethod.POST)
	@ResponseBody
	public String auth(HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassAuthForm cf = JSON.parseObject(AjaxWebUtil.getRequestPayload(request), ClassAuthForm.class);
			if ( null != cf ) {
				classRegistorService.auth(cf,LoginUserUtil.getCurrentUser(request).getName());;
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"授权成功", null);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"授权失败:格式解析错误", "格式解析错误");
		}catch(Exception e) {
			log.error("授权失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"授权失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
}
