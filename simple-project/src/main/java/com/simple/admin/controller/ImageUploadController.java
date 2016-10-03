package com.simple.admin.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.simple.common.config.EnvPropertiesConfiger;
import com.simple.common.excel.FileUploadUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.service.ClassRegistorService;
@Controller
@RequestMapping(value = "/image")
public class ImageUploadController {
	
	private static final Logger log = LoggerFactory.getLogger(ImageUploadController.class);

	@Autowired
	ClassRegistorService classRegistorService;
	
	@RequestMapping(value="/uploadFile",method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {	
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if (StringUtils.isEmpty(suffix)) {
				suffix = request.getParameter("suffix");
			}
			File f = FileUploadUtil.getFileByInputStream(file.getInputStream(), suffix, FileUploadUtil.UPLOAD_IMAGE_DIR);
			if ( null != f) {
				String path = f.getPath().replace(EnvPropertiesConfiger.getValue("uploadDir"), "");
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"上传成功", path);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败", null);
		}catch(Exception e) {
			log.error("上传失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "upload",method=RequestMethod.POST)
	@ResponseBody
	public String uploadStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			//欢迎登录安全教育平台图片
			String image = AjaxWebUtil.getRequestPayload(request);
			String suffix = request.getParameter("suffix");
			String filepath = getfilepath(image,suffix);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"上传成功", filepath);
		}catch(Exception e) {
			log.error("上传失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"上传失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	private String getfilepath(String imageData,String subfix) {
		if (StringUtils.isEmpty(subfix)) {
			subfix = "jpg";
		}
		File b1SrcFile = FileUploadUtil.getFileByHex(imageData,subfix, FileUploadUtil.UPLOAD_IMAGE_DIR);
		if ( null != b1SrcFile) {
			return b1SrcFile.getPath().replace(EnvPropertiesConfiger.getValue("uploadDir"), "");
		}
		return null;
	}
}
