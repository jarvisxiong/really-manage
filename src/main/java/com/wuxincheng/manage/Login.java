package com.wuxincheng.manage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuxincheng.manage.model.Admins;
import com.wuxincheng.manage.service.AdminsService;
import com.wuxincheng.manage.util.Constants;
import com.wuxincheng.manage.util.MD5;
import com.wuxincheng.manage.util.Validation;

@Controller
@RequestMapping("/admins")
public class Login {
	
	private static Logger logger = LoggerFactory.getLogger(Login.class);
	
	@Autowired private AdminsService adminsService;
	
	@RequestMapping(value = "/login")
	public String login(Model model) {
		logger.info("显示后台管理登录页面");
		
		/*
		// 登录背景切换方式
		List<String> backgrounds = new ArrayList<String>();
		
		backgrounds.add(0, "http://www.bz889.com/uploads/allimg/120723/co120H3131240-0.jpg");
		backgrounds.add(1, "http://img.tuku.com/upload/attach/2013/06/94949-7IUzBD6.jpg");
		backgrounds.add(2, "http://www.mtg.net.cn/up2013/201012/10122508526255.jpg");
		backgrounds.add(3, "http://www.meiniu.cc/uploads/allimg/111128/1-11112P95201.jpg");
		
		int randNum = (int) (Math.random() * (backgrounds.size()-1)); // 产生0-1000的整数随机数
		
		model.addAttribute("background", backgrounds.get(randNum));
		 */
		
		return "login";
	}
	
	@RequestMapping(value = "/doLogin")
	public String doLogin(HttpServletRequest request, Model model,
			@RequestParam String adminsLogin, @RequestParam String adminsPwd) {
		logger.info("登录到系统");
		
		if (Validation.isBlank(adminsLogin) || Validation.isBlank(adminsPwd)) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "用户名和密码都不能为空");
			return "login";
		}
		
		// Admins admins = adminsService.login(adminsLogin, adminsPwd);
		Admins admins = adminsService.query(adminsLogin);

		if (null == admins) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "用户不存在，请检查您的账号");
			return "login";
		}
		
		String pwd = admins.getAdminsPwd(); // 数据库中的密码
		
		// 登录密码加密
		String adminsPwdMD5Str = MD5.encryptMD5Pwd(adminsPwd);
		
		if (!Validation.isBlank(pwd) && pwd.equals(adminsPwdMD5Str)) {
			request.getSession().setAttribute("admins", admins);
		} else {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "用户密码不正确");
			return "login";
		}
		
		return "main";
	}
	
	@RequestMapping(value = "/main")
	public String main() {
		return "main";
	}

}
