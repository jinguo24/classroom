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

import com.alibaba.fastjson.JSON;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.constant.Constant;
import com.simple.model.FormQuestionResult;
import com.simple.model.TeachingLog;
import com.simple.model.Template;
import com.simple.model.TemplateForm;
import com.simple.service.QuestionResultService;
import com.simple.service.TeachingService;
import com.simple.service.TemplateService;
@Controller
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {
	
	private static final Logger log = LoggerFactory.getLogger(QuestionnaireController.class);

	@Autowired
	QuestionResultService questionResultService;
	@Autowired
	TemplateService templateService;
	@Autowired
	TeachingService teachingService;
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String queryByCode(String code,String skbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isEmpty(code)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:code为空", null);
			}
			TemplateForm form = templateService.queryByCode(null, code,false,false);
			if (StringUtils.isEmpty(skbh)) {
				skbh = "system";
			}
			form.setSkbh(skbh);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", form);
		}catch(Exception e) {
			log.error("查询题目失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败", e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "answer",method=RequestMethod.POST)
	@ResponseBody
	public String update(String code,String content,String skbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			String tanentId = Constant.TANENT_ROOT;
			if (!StringUtils.isEmpty(skbh)) {
				TeachingLog log = teachingService.queryBySkbh(skbh);
				tanentId = log.getTanentid();
			}
			questionResultService.addResult(code,JSON.parseObject(content,FormQuestionResult[].class), tanentId, PrimaryKeyUtil.getUUID(),skbh);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"答题成功", null);
		}catch(Exception e) {
			log.error("答题失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"答题失败", e.getLocalizedMessage());
		}
	}
	
	
}
