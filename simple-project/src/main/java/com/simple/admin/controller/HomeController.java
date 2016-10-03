package com.simple.admin.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主界面controller
 * 
 * @author zhenglong.wei
 *
 */
@Controller
@RequestMapping(value="/home")
public class HomeController {
	
	@RequestMapping(value = "homepage",method = RequestMethod.GET)
	public ModelAndView gotoHomepage(HttpServletRequest request) {
		ModelAndView md = new ModelAndView("home/home-main");
		return md;
	}
	
	@RequestMapping(value = "getTreeList/{type}",method = RequestMethod.POST)
	@ResponseBody
	public List getTreeList(@PathVariable int type) {
		return null;
	}

}