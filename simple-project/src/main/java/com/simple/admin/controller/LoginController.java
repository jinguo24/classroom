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
import com.simple.common.util.MD5;
import com.simple.constant.Constant;
import com.simple.model.ClassRegister;
import com.simple.model.JyjgUser;
import com.simple.model.SysUser;
import com.simple.service.ClassRegistorService;
import com.simple.service.JyjgUserService;
import com.simple.service.SysUserService;
/**
 * 
 * @author zhenglong.wei
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	SysUserService sysUserService;
	@Autowired
	ClassRegistorService registerService;
	@Autowired
	JyjgUserService jyjgUserService;
	
	/**
	 * 安全教室登录
	 * 1---授权帐号登录
	 * 2---老师登录
	 * @param sqzh
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "tanentDoLogin",method=RequestMethod.POST)
	@ResponseBody
	public String doLogin(int type,String sqzh,String password,HttpServletRequest request, HttpServletResponse response) {
		if (type==1) {
			ClassRegister user = registerService.queryClassRegister(sqzh);
			if ( user == null ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:帐号不存在.", null);
			}
			if (!user.getSqmm().equals(MD5.stringMD5(password))) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:密码错误.", null);
			}
			SysUser su = new SysUser();
			su.setLeaseholderId(user.getTanentid());
			su.setXxbh(user.getXxbh());
			su.setXxmc(user.getXxmc());
			su.setLoginType(Constant.LOGIN_TYPE_TANENT);
			su.setLoginAccount(user.getSqzh());
			LoginUserUtil.setCurrentUser(request, su);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"登录成功", su);
		}else if (type==2) {
			SysUser user = sysUserService.queryByAccount(sqzh);
			if (null == user ) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:帐号不存在.", null);
			}
			if (!user.getPassword().equals(MD5.stringMD5(password))) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:密码错误.", null);
			}
			SysUser su = new SysUser();
			su.setLeaseholderId(user.getLeaseholderId());
			su.setXxbh(user.getXxbh());
			su.setXxmc(user.getName());
			su.setLoginType(Constant.LOGIN_TYPE_TEACHER);
			su.setStudentId(user.getStudentId());
			su.setLoginAccount(user.getAccount());
			LoginUserUtil.setCurrentUser(request, su);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"登录成功", su);
		}
		return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:登录类型错误", null);
	}
	
	@RequestMapping(value = "jyjgDoLogin",method=RequestMethod.POST)
	@ResponseBody
	public String jyjgDoLogin(String name,String password,HttpServletRequest request, HttpServletResponse response) {
		JyjgUser user = jyjgUserService.queryUser(name);
		if ( user == null ) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:工号不存在.", null);
		}
		if (!user.getPassword().equals(password)) {
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"登录失败:密码错误.", null);
		}
		SysUser su = new SysUser();
		su.setLeaseholderId(user.getTanentId());
		su.setStudentId(user.getAccount());
		su.setName(user.getName());
		su.setLoginType(Constant.LOGIN_TYPE_JYJG);
		su.setLoginAccount(user.getAccount());
		LoginUserUtil.setCurrentUser(request, su);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"登录成功", su);
	}
	
	@RequestMapping(value = "logout",method=RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		LoginUserUtil.removeCurrentUser(request);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"登出成功", null);
	}
	
	
}
