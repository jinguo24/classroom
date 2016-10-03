package com.simple.admin.util;

import javax.servlet.http.HttpServletRequest;

import com.simple.constant.Constant;
import com.simple.model.SysUser;


public class LoginUserUtil {

	public static SysUser getCurrentUser(HttpServletRequest request) {
		return (SysUser) request.getSession().getAttribute(Constant.CURRENT_USER);
	}
	public static void setCurrentUser(HttpServletRequest request,SysUser user) {
		request.getSession().setAttribute(Constant.CURRENT_USER,user);
	}
	public static void removeCurrentUser(HttpServletRequest request) {
		request.getSession().removeAttribute(Constant.CURRENT_USER);
	}
	public static String getLeaseholderId(HttpServletRequest request) {
		SysUser user = getCurrentUser(request);
		return user.getLeaseholderId();
	}
}
