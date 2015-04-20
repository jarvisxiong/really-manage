package com.wuxincheng.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基Controller
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/base")
public class BaseController {
	
	public void setMenuFlag(HttpServletRequest request, String menuFlag){
		request.getSession().setAttribute("menuFlag", menuFlag);
	}
	
}
