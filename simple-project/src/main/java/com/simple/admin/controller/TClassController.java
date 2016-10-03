package com.simple.admin.controller;

import java.io.File;
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

import com.alibaba.fastjson.JSONObject;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.excel.FileUploadUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.ResponseInfo;
import com.simple.constant.Constant;
import com.simple.model.ClassRegister;
import com.simple.model.PageResult;
import com.simple.model.TClass;
import com.simple.model.TClassStudent;
import com.simple.model.XueXiao;
import com.simple.model.api.BanJi;
import com.simple.model.api.NianJi;
import com.simple.service.ClassRegistorService;
import com.simple.service.TClassService;
import com.simple.service.TClassStudentService;
@Controller
@RequestMapping(value = "/tclass")
public class TClassController {
	
	private static final Logger log = LoggerFactory.getLogger(TClassController.class);

	@Autowired
	TClassService tclassService;
	@Autowired
	TClassStudentService tclassStudentService;
	@Autowired
	ClassRegistorService classRegistorService;
	
	@RequestMapping(value = "upload",method=RequestMethod.POST)
	@ResponseBody
	public String upload(String njbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			String imageData = (String) AjaxWebUtil.getRequestPayloadParamer(request, "data");
			File file = FileUploadUtil.getFileByHex(imageData, "xlsx");
			if ( null != file ) {
				String tanentId = LoginUserUtil.getLeaseholderId(request);
				ResponseInfo ri  = tclassService.validateFile(file,tanentId,njbh);
				if ( null != ri && ri.getStatus().getState()) {
					ClassRegister cr = classRegistorService.getClassRegister(tanentId, null);
					tclassService.addTClass((List<TClass>)ri.getData(),LoginUserUtil.getLeaseholderId(request),
							cr.getXxbh(),cr.getXxmc(),LoginUserUtil.getCurrentUser(request).getName());
				}else if ( null != ri && (!ri.getStatus().getState())) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,ri.getStatus().getCode(),"创建失败", ri.getData());
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", null);
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "download",method=RequestMethod.GET)
	@ResponseBody
	public String download(TClass tclass,HttpServletRequest request, HttpServletResponse response) {
		try {
			return JSONObject.toJSONString(tclassService.download(tclass, 1, 10000));
		}catch(Exception e) {
			log.error("下载失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"下载失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(TClass tclass,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setTanentid(leaseholderId);
		PageResult dictionarys =  tclassService.query(tclass, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "uploadStudent",method=RequestMethod.POST)
	@ResponseBody
	public String uploadStudent(String njbh,HttpServletRequest request, HttpServletResponse response) {
		try {
			String imageData = (String) AjaxWebUtil.getRequestPayloadParamer(request, "data");
			File file = FileUploadUtil.getFileByHex(imageData, "xlsx");
			if ( null != file ) {
				String tanentId = LoginUserUtil.getLeaseholderId(request);
				ResponseInfo ri  = tclassStudentService.validateFile(file,tanentId,njbh);
				if ( null != ri && ri.getStatus().getState()) {
					ClassRegister cr = classRegistorService.getClassRegister(tanentId, null);
					tclassStudentService.addTClass((List<TClassStudent>)ri.getData(),tanentId,
							cr.getXxbh(),cr.getXxmc(),LoginUserUtil.getCurrentUser(request).getName());
				}else if ( null != ri && (!ri.getStatus().getState())) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,ri.getStatus().getCode(),"创建失败", ri.getData());
				}
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", null);
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "downloadStudent",method=RequestMethod.GET)
	@ResponseBody
	public String downloadStudent(TClassStudent tclass,HttpServletRequest request, HttpServletResponse response) {
		try {
			return JSONObject.toJSONString(tclassStudentService.download(tclass, 1, 10000));
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(int id,HttpServletRequest request, HttpServletResponse response) {
		try {
			tclassService.delete(id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"删除成功", "删除成功");
		}catch(Exception e) {
			log.error("删除失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"删除失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryStudent",method=RequestMethod.GET)
	@ResponseBody
	public String queryStudent(TClassStudent tclass,int pageIndex,int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		tclass.setTanentid(leaseholderId);
		PageResult dictionarys =  tclassStudentService.query(tclass, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "xx",method=RequestMethod.GET)
	@ResponseBody
	public String xx(HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		if ( Constant.TANENT_ROOT.equals(leaseholderId)) {
			leaseholderId = null;
		}
		List<XueXiao> dictionarys =  tclassService.queryXueXiao(leaseholderId);
		PageResult pr = new PageResult(0,1,1,dictionarys);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
	}
	
	@RequestMapping(value = "xn",method=RequestMethod.GET)
	@ResponseBody
	public String xn(String xxbh,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		List<Integer> dictionarys =  tclassService.queryXn(leaseholderId,xxbh);
		PageResult pr = new PageResult(0,1,1,dictionarys);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", pr);
	}
	
	@RequestMapping(value = "nj",method=RequestMethod.GET)
	@ResponseBody
	public String nj(String xxbh,Integer xn,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		if (null == xn) {
			xn = 0;
		}
		List<NianJi> dictionarys =  tclassService.njList(leaseholderId, xn,xxbh);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
	
	@RequestMapping(value = "bj",method=RequestMethod.GET)
	@ResponseBody
	public String bj(String xxbh,Integer xn,String njbh,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		if (null == xn) {
			xn = 0;
		}
		List<BanJi> dictionarys =  tclassService.bjList(leaseholderId, xn, njbh,xxbh);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", dictionarys);
	}
}
