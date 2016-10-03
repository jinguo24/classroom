package com.simple.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.simple.admin.util.LoginUserUtil;
import com.simple.common.util.AjaxWebUtil;
import com.simple.common.util.MD5;
import com.simple.common.util.PrimaryKeyUtil;
import com.simple.constant.Constant;
import com.simple.email.SimpleMailSender;
import com.simple.enums.DictionaryEnum;
import com.simple.model.ClassRegister;
import com.simple.model.Dictionary;
import com.simple.model.JyjgUser;
import com.simple.model.PageResult;
import com.simple.model.SysUser;
import com.simple.model.UserForm;
import com.simple.service.ClassRegistorService;
import com.simple.service.DictionaryService;
import com.simple.service.JyjgUserService;
import com.simple.service.SysUserService;
import com.simple.service.TemplateService;
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	TemplateService templateService;
	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	SysUserService sysUserService;
	@Autowired
	ClassRegistorService classRegisterService;
	@Autowired
	JyjgUserService jyjgUserService;
	
	@RequestMapping(value = "querySex",method=RequestMethod.GET)
	@ResponseBody
	public String querySex(HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,DictionaryEnum.SEX.getKey());
	}
	
	@RequestMapping(value = "queryMarry",method=RequestMethod.GET)
	@ResponseBody
	public String queryMarry(HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,DictionaryEnum.MARRAY.getKey());
	}
	
	@RequestMapping(value = "queryMacao",method=RequestMethod.GET)
	@ResponseBody
	public String queryMacao(HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,DictionaryEnum.MACAO.getKey());
	}
	
	@RequestMapping(value = "queryIdTypes",method=RequestMethod.GET)
	@ResponseBody
	public String queryIdTypes(HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,DictionaryEnum.IDCARD_TYPE.getKey());
	}
	
	@RequestMapping(value = "queryProvinces",method=RequestMethod.GET)
	@ResponseBody
	public String queryProvinces(HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,DictionaryEnum.PROVINCE.getKey());
	}
	
	@RequestMapping(value = "queryCitys",method=RequestMethod.GET)
	@ResponseBody
	public String queryCitys(String province,HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,province);
	}
	
	@RequestMapping(value = "queryAreas",method=RequestMethod.GET)
	@ResponseBody
	public String queryAreas(String city,HttpServletRequest request, HttpServletResponse response) {
		return getDictionays(request,response,city);
	}
	
	private String getDictionays(HttpServletRequest request,HttpServletResponse response,String key) {
		try {
			List<Dictionary> dictionarys = dictionaryService.getByParentCode(key);
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
			sysUserService.addSysUser(JSON.parseObject(AjaxWebUtil.getRequestPayload(request), UserForm.class),LoginUserUtil.getLeaseholderId(request),LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"创建成功", null);
		}catch(Exception e) {
			log.error("创建失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"创建失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "queryById",method=RequestMethod.GET)
	@ResponseBody
	public String queryByCode(String studentId,HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser su = sysUserService.querySysUser(LoginUserUtil.getLeaseholderId(request), studentId);
			if ( null != su) {
				return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", su.toUserForm());
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:未查询到用户", "未查询到用户");
		}catch(Exception e) {
			log.error("创建模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "update",method=RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		try {
			sysUserService.updateSysUser(JSON.parseObject(AjaxWebUtil.getRequestPayload(request), UserForm.class),LoginUserUtil.getLeaseholderId(request),LoginUserUtil.getCurrentUser(request).getName());
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"修改成功", null);
		}catch(Exception e) {
			log.error("修改模版失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "query",method=RequestMethod.GET)
	@ResponseBody
	public String query(SysUser user,String begin,String end,int pageIndex,
			int pageSize,HttpServletRequest request, HttpServletResponse response) {
		String leaseholderId = LoginUserUtil.getLeaseholderId(request);
		user.setLeaseholderId(leaseholderId);
		PageResult users =  sysUserService.query(user,begin,end, pageIndex, pageSize);
		return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", users);
	}
	
	@RequestMapping(value = "delete/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable int id,HttpServletRequest request, HttpServletResponse response) {
		try {
			sysUserService.delete(LoginUserUtil.getLeaseholderId(request), id);
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"删除成功", null);
		}catch(Exception e) {
			log.error("删除字典失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"删除字典失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping(value = "currentUser",method=RequestMethod.GET)
	@ResponseBody
	public String currentUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"查询成功", LoginUserUtil.getCurrentUser(request));
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "updatePassword",method=RequestMethod.POST)
	@ResponseBody
	public String updatePassword(String oldPassword,String newPassword,HttpServletRequest request, HttpServletResponse response) {
		try {
			SysUser user = LoginUserUtil.getCurrentUser(request);
			String loginType = user.getLoginType();
			if (Constant.LOGIN_TYPE_TANENT.equals(loginType)) {
				ClassRegister classRegister = classRegisterService.queryClassRegister(user.getLoginAccount());
				if ( null == classRegister) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"安全教室帐号不存在", null);
				}
				if (!classRegister.getSqmm().equals(MD5.stringMD5(oldPassword))) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:密码错误", null);
				}
				classRegisterService.updatePassword(user.getLoginAccount(), MD5.stringMD5(newPassword));
			}else if (Constant.LOGIN_TYPE_TEACHER.equals(loginType)) {
				SysUser teacher = sysUserService.queryByAccount(user.getLoginAccount());
				if ( null == teacher) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"用户帐号不存在", null);
				}
				if (!teacher.getPassword().equals(MD5.stringMD5(oldPassword))) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:密码错误", null);
				}
				sysUserService.updatePassword(teacher.getLeaseholderId(), teacher.getStudentId(), MD5.stringMD5(newPassword));
			}else if (Constant.LOGIN_TYPE_JYJG.equals(loginType)) {
				JyjgUser jyjg = jyjgUserService.queryUser(user.getLoginAccount());
				if ( null == jyjg) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"教育机构帐号不存在", null);
				}
				if (!jyjg.getPassword().equals(oldPassword)) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"修改失败:密码错误", null);
				}
				jyjgUserService.updatePassword(jyjg.getAccount(), newPassword);
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"更新成功", user);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value = "forget",method=RequestMethod.POST)
	@ResponseBody
	public String forget(String account,int type,HttpServletRequest request, HttpServletResponse response) {
		try {
			
			boolean isvalid = false;
			String password = PrimaryKeyUtil.getRandomString();
			String domain = "";
			String email = "";
			if (type == 1 ) {
				SysUser user = sysUserService.queryByAccount(account);
				if ( null == user) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"用户帐号不存在", null);
				}
				sysUserService.updatePassword(user.getLeaseholderId(), user.getStudentId(), MD5.stringMD5(password));
				domain = "http://class.zhongguoanquanjiaoyu.com";
				email = user.getEmail();
				isvalid = true;
			}else if (type == 2 ) {
				ClassRegister classRegister = classRegisterService.queryClassRegister(account);
				if ( null == classRegister) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"安全教室帐号不存在", null);
				}
				classRegisterService.updatePassword(classRegister.getSqzh(), MD5.stringMD5(password));
				email = classRegister.getEmail();
				domain = "http://class.zhongguoanquanjiaoyu.com";
				isvalid = true;
			}else if (type == 3) {
				JyjgUser user = jyjgUserService.queryUser(account);
				if ( null == user) {
					return AjaxWebUtil.sendAjaxResponse(request, response, false,"教育机构帐号不存在", null);
				}
				jyjgUserService.updatePassword(account, password);
				domain = "http://admin.zhongguoanquanjiaoyu.com";
				email = user.getEmail();
				isvalid = true;
			}else {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"类型不合法", null);
			}
			if (StringUtils.isEmpty(email)) {
				return AjaxWebUtil.sendAjaxResponse(request, response, false,"邮箱为空", null);
			}
			if (isvalid) {
				StringBuffer contentsb = new StringBuffer("<div>你好, 尊敬的").append(account).append("用户,您的帐号密码已经重置： <font color=\"red\">").append(password).append("</font></div>");
				contentsb.append("&nbsp;&nbsp;&nbsp;<div>请您及时登录<a href=\"").append(domain).append("\" target=\"_blank\">").append(domain).append("</a>修改密码.<div>");
				contentsb.append("&nbsp;&nbsp;&nbsp;<div>此为系统邮件，请勿回复，谢谢！</div>");
				SimpleMailSender.sendHtmlEmail(email, "密码重置", contentsb.toString());
			}
			return AjaxWebUtil.sendAjaxResponse(request, response, true,"发送成功", null);
		}catch(Exception e) {
			log.error("查询失败",e);
			return AjaxWebUtil.sendAjaxResponse(request, response, false,"查询失败:"+e.getLocalizedMessage(), e.getLocalizedMessage());
		}
	}
	
	
	
	
}
