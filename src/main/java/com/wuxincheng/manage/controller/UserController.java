package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.manage.model.User;
import com.wuxincheng.manage.service.UserService;

/**
 * 用户管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService userService;
	
	/**
	 * 显示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("显示用户列表页面");
		setMenuFlag(request, "user");
		
		List<User> users = userService.queryAll();
		try {
			if (users != null && users.size() > 0) {
				model.addAttribute("users", users);
			} else {
				model.addAttribute("users", Collections.EMPTY_LIST);
				logger.info("没有查询到用户信息");
			}
		} catch (Exception e) {
			logger.error("在查询用户列表时出现异常", e);
		}
		
		return "user/list";
	}
	
}
