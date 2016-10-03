package com.simple.admin.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.model.ClassRegister;
import com.simple.service.ClassRegistorService;
import com.simple.service.DecorateService;
@Controller
@RequestMapping(value = "/decorate")
public class DecorateController {
	
	private static final Logger log = LoggerFactory.getLogger(DecorateController.class);

	@Autowired
	ClassRegistorService classRegistorService;
	@Autowired
	DecorateService decorateService;
	
	@RequestMapping(value = "setCourseXlImage",method=RequestMethod.POST)
	@ResponseBody
	public String setCourseXlImage(String tanentId,String jsbh,String xlbh,String xlmc,String image,HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegister cr = classRegistorService.getClassRegister(tanentId, jsbh);
			if ( null == cr ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败：教室未注册.", null);
			}
			decorateService.setCourseXlImage(tanentId, jsbh, xlbh, xlmc, image, cr.getXxbh(), cr.getXxmc(), LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"设置成功", null);
		}catch(Exception e) {
			log.error("设置失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败:"+e.getLocalizedMessage(), null);
		}
	}
	
	@RequestMapping(value = "setCourseImage",method=RequestMethod.POST)
	@ResponseBody
	public String setCourseImage(String tanentId,String jsbh,String kcbh,String kcmc,String image,HttpServletRequest request, HttpServletResponse response) {
		try {
			ClassRegister cr = classRegistorService.getClassRegister(tanentId, jsbh);
			if ( null == cr ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败：教室未注册.", null);
			}
			decorateService.setCourseImage(tanentId, jsbh, kcbh, kcmc, image, cr.getXxbh(), cr.getXxmc(), LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"设置成功", null);
		}catch(Exception e) {
			log.error("设置失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"设置失败:"+e.getLocalizedMessage(), null);
		}
	}
	
}
