package com.simple.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.enums.DictionaryEnum;
import com.simple.model.Dictionary;
import com.simple.model.PageResult;
import com.simple.model.SheetForm;
import com.simple.model.TemplateForm;
import com.simple.service.DictionaryService;
import com.simple.service.TemplateService;
@Controller
@RequestMapping(value = "/template")
public class TemplateController {
	
	private static final Logger log = LoggerFactory.getLogger(TemplateController.class);

	@Autowired
	TemplateService templateService;
	@Autowired
	DictionaryService dictionaryService;
	
	@RequestMapping(value = "queryModelTypes",method=RequestMethod.GET)
	@ResponseBody
	public String queryModelTypes(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dictionary> dictionarys = dictionaryService.getByParentCode(DictionaryEnum.TEMPLATE_TYPE.getKey());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryModelCategorys",method=RequestMethod.GET)
	@ResponseBody
	public String queryModelCategorys(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dictionary> dictionarys = dictionaryService.getByParentCode(DictionaryEnum.TEMPLATE_CATEGORY.getKey());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping(value = "add",method=RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = templateService.addTemplate(JSON.parseObject(AjaxWebUtil.getRequestPayload(request), TemplateForm.class),LoginUserUtil.getLeaseholderId(request),LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", code);
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryByCode",method=RequestMethod.GET)
	@ResponseBody
	public String queryByCode(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			TemplateForm form = templateService.queryByCode(LoginUserUtil.getLeaseholderId(request), code, false,false);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", form);
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		try {
			String code = templateService.update(JSON.parseObject(AjaxWebUtil.getRequestPayload(request), TemplateForm.class), leaseholderId, LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"修改成功", code);
		}catch(Exception e) {
			log.error("修改模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(String code,String category,String type,String name,String begin,String end,int pageIndex,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		//if (!AjaxWebUtil.isAjaxRequest(request)) {// 不是ajax请求
		//	return new ModelAndView("redirect:/login/login");
		PageResult dictionarys =  templateService.query(leaseholderId, code,begin,end, type,name,category,null, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable int id,HttpServletRequest request, HttpServletResponse response) {
		try {
			templateService.deleteById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"删除成功", null);
		}catch(Exception e) {
			log.error("删除字典失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"删除字典失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "statistics",method=RequestMethod.GET)
	@ResponseBody
	public String statistics(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			TemplateForm form = templateService.queryByCode(null, code, true,false);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", form);
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "downloadStatistics",method=RequestMethod.POST)
	@ResponseBody
	public String downloadStatistics(String data,HttpServletRequest request, HttpServletResponse response) {
		try {
			templateService.download(JSON.parseObject(data, SheetForm.class),response,"调查问卷评价分析");
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", null);
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "resultInfo",method=RequestMethod.GET)
	@ResponseBody
	public String resultInfo(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			TemplateForm form = templateService.queryByCode(null, code, true,true);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", form);
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
}
