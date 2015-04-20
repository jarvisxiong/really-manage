package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuxincheng.manage.model.Admins;
import com.wuxincheng.manage.service.AdminsService;
import com.wuxincheng.manage.util.Constants;

/**
 * 管理员管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/admins")
public class AdminsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(AdminsController.class);
	
	@Autowired private AdminsService adminsService;
	
	/**
	 * 显示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("显示管理员列表页面");
		setMenuFlag(request, "admins");
		
		List<Admins> adminzs = adminsService.queryAll();
		try {
			if (adminzs != null && adminzs.size() > 0) {
				model.addAttribute("adminzs", adminzs);
			} else {
				model.addAttribute("adminzs", Collections.EMPTY_LIST);
				logger.info("没有查询到管理员信息");
			}
		} catch (Exception e) {
			logger.error("在查询管理员列表时出现异常", e);
		}
		
		return "admins/list";
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(@RequestParam String adminsLogin, Model model) {
		if (StringUtils.isEmpty(adminsLogin)) { // 
			logger.info("显示管理员新增页面");
		} else {
			logger.info("显示管理员修改页面");
			
			Admins adminz = null;
			logger.info("修改管理员的编号: " + adminsLogin);
			
			adminz = adminsService.query(adminsLogin);
			
			model.addAttribute("adminz", adminz);
		}
		
		return "admins/edit";
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	public String doEdit(HttpServletRequest request, Admins admins, Model model) {
		logger.info("处理编辑管理员数据");
		String detaultPage = "admins/edit";
		
		String adminsLogin = admins.getAdminsLogin();
		if (null == adminsLogin || "".equals(adminsLogin.trim())) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "登录名不能为空");
			return detaultPage;
		}
		
		// 查询adminsLogin是否已经存在
		Admins adminsFlag = adminsService.query(adminsLogin);
		if (adminsFlag != null) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "登录名已经存在");
			return detaultPage;
		}
		
		String adminsPwd = admins.getAdminsPwd();
		String adminsPwd2 = admins.getAdminsPwd2();
		if (null == adminsPwd || "".equals(adminsPwd.trim()) || null == adminsPwd2 || "".equals(adminsPwd2.trim())) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "密码不能为空");
			return detaultPage;
		}
		
		if (!adminsPwd.equals(adminsPwd2)) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "两次输入密码不一样");
			return detaultPage;
		}
		
		String adminsName = admins.getAdminsName();
		if (null == adminsName || "".equals(adminsName.trim())) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "姓名不能为空");
			return detaultPage;
		}
		
		try {
			admins.setState("0");
			
			adminsService.insert(admins);
			
			model.addAttribute(Constants.MSG_TYPE_SUCCESS, "管理员编辑成功");
		} catch (Exception e) {
			logger.error("在编辑博客时出现异常: ", e);
			model.addAttribute(Constants.MSG_TYPE_DANGER, "管理员编辑时出现异常，请联系管理员");
		}
		
		return list(request, model);
	}
	
}
