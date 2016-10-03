package com.simple.admin.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.model.PageResult;
import com.simple.model.QuestionResult;
import com.simple.service.QuestionResultService;
import com.simple.service.TemplateService;
@Controller
@RequestMapping(value = "/questionResult")
public class QuestionResultController {
	
	@Autowired
	QuestionResultService questionResultService;
	@Autowired
	TemplateService templateService;
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(QuestionResult tclass,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		//String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		//tclass.setTanentid(leaseholderId);
		PageResult dictionarys =  questionResultService.query(tclass,pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "queryTemplates",method=RequestMethod.GET)
	@ResponseBody
	public String queryTemplates(QuestionResult tclass,String begin,String end,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setLeaseholderId(leaseholderId);
		List<String> templateCodes  = questionResultService.queryTemplateCodes(tclass);
		PageResult dictionarys = templateService.query(tclass.getLeaseholderId(), null, 
				null, null, null, null, null, templateCodes, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "download",method=RequestMethod.GET)
	@ResponseBody
	public String download(QuestionResult tclass,String begin,String end,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setLeaseholderId(leaseholderId);
		List<String> templateCodes  = questionResultService.queryTemplateCodes(tclass);
		ResponseInfo dictionarys = templateService.download(tclass.getLeaseholderId(), null, 
				null, null, null, null, null, templateCodes);
		return JSONObject.toJSONString(dictionarys);
	}
}
