package com.simple.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.Dictionary;
import com.simple.service.DictionaryService;
@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController {
	
	private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

	@Autowired
	DictionaryService dictionaryService;
	
	@RequestMapping(value = "queryByCode",method=RequestMethod.GET)
	@ResponseBody
	public String queryByCode(String code,HttpServletRequest request, HttpServletResponse response) {
		try {
			Dictionary dictionary = dictionaryService.getByCode(code);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionary);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryByParentCode",method=RequestMethod.GET)
	@ResponseBody
	public String queryByParentCode(String parentCode,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dictionary> dictionarys =  dictionaryService.getByParentCode(parentCode);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "add",method=RequestMethod.GET)
	@ResponseBody
	public String add(String code,String parentCode,String name,HttpServletRequest request, HttpServletResponse response) {
		try {
			//Dictionary dictionary = JSON.parseObject(AjaxWebUtil.getRequestPayload(request),Dictionary.class);
			code = StringUtils.trimToNull(code);
			name = StringUtils.trimToNull(name);
			if ( null != code && null != name ) {
				Dictionary d = dictionaryService.getByCode(code);
				if ( null != d ) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"code重复", "code重复");
				}
				Dictionary dictionary = new Dictionary();
				dictionary.setCode(code);
				if (StringUtils.isEmpty(parentCode)) {
					parentCode = "ROOT";
				}
				dictionary.setParentCode(parentCode);
				dictionary.setName(name);
				dictionary.setType("1");
				int id = dictionaryService.addDitionary(dictionary);
				dictionary.setId(id);
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", dictionary);
			}else {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"参数不能为空", "参数不能为空");
			}
		}catch(Exception e) {
			log.error("创建字典失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "update",method=RequestMethod.GET)
	@ResponseBody
	public String update(int id,String code,String parentCode,String name,HttpServletRequest request, HttpServletResponse response) {
		//Dictionary dictionary = JSON.parseObject(AjaxWebUtil.getRequestPayload(request),Dictionary.class);
		Dictionary dictionarysource = dictionaryService.queryById(id);
		try {
			code = StringUtils.trimToNull(code);
			name = StringUtils.trimToNull(name);
			if ( null != code && null != name && null != parentCode) {
				if (dictionarysource.getCode().equals(code)) {
					//BeanUtils.copyProperties(dictionarysource, dictionary);
					dictionarysource.setParentCode(parentCode);
					dictionarysource.setName(name);
					dictionaryService.updateDictionary(dictionarysource);
				}else {
					Dictionary dictionarys =  dictionaryService.getByCode(code);
					if (null != dictionarys) {
						return AjaxWebUtil.sendAjaxResponse(request, response, false,"字典code["+code+"]重复"
								, "字典code["+code+"]重复");
					}else {
						//BeanUtils.copyProperties(dictionarysource, dictionary);
						dictionarysource.setCode(code);
						if (StringUtils.isEmpty(parentCode)) {
							parentCode = "ROOT";
						}
						dictionarysource.setParentCode(parentCode);
						dictionarysource.setName(name);
						dictionaryService.updateDictionary(dictionarysource);
					}
				}
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"修改成功", null);
			}else {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"参数不能为空", "参数不能为空");
			}
			
		}catch(Exception e) {
			log.error("修改字典失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryRoot",method=RequestMethod.GET)
	@ResponseBody
	public String queryRoot(String code,String name,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		List<Dictionary> dictionarys =  dictionaryService.queryRoot(name,code,pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable int id,HttpServletRequest request, HttpServletResponse response) {
		try {
			dictionaryService.deleteById(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"删除成功", null);
		}catch(Exception e) {
			log.error("删除字典失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"删除字典失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
}
