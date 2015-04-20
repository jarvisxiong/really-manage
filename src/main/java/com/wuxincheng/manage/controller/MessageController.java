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

import com.wuxincheng.manage.model.Message;
import com.wuxincheng.manage.service.MessageService;

@Controller
@RequestMapping("/manage/message")
public class MessageController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired MessageService messageService;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("显示用户反馈信息页面");
		setMenuFlag(request, "user");
		
		List<Message> messages = messageService.queryAll();
		try {
			if (messages != null && messages.size() > 0) {
				model.addAttribute("messages", messages);
			} else {
				model.addAttribute("users", Collections.EMPTY_LIST);
				logger.info("没有查询到用户反馈信息");
			}
		} catch (Exception e) {
			logger.error("在用户反馈信息列表时出现异常", e);
		}
		
		return "message/list";
	}
	
}
