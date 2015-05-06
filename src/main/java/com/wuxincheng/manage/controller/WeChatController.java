package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.WeChatService;
import com.wuxincheng.manage.util.Constants;
import com.wuxincheng.manage.util.WeiXinFetchTool;

/**
 * 微信号公众号管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/weChat")
public class WeChatController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(WeChatController.class);
	
	private static final String WECHAT_STATE_NORMAL = "0";
	// private static final String WECHAT_STATE_FROZN = "1";
	private static final String WECHAT_STATE_DELETED = "2";
	
	@Autowired private WeChatService weChatService;
	
	private WeChat wechat;
	
	/**
	 * 显示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		logger.info("显示所有微信公众号");
		setMenuFlag(request, "weChat");
		
		List<WeChat> weChats = weChatService.queryAll();
		try {
			if (weChats != null && weChats.size() > 0) {
				model.addAttribute("weChats", weChats);
			} else {
				model.addAttribute("weChats", Collections.EMPTY_LIST);
			}
		} catch (Exception e) {
		}
		
		return "weChat/list";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model) {
		logger.info("显示微信公众号编辑页面");
		return "weChat/edit";
	}
	
	@RequestMapping(value = "/view")
	public String view(String openId, Model model) {
		logger.info("抓取微信公众号信息");
		
		wechat = WeiXinFetchTool.fetchWechatPublicNoInfoBySogouOpenid(openId);
		
		if (wechat != null && !"".equals(wechat)) {
			model.addAttribute("wechat", wechat);
		} else {
			
		}
		
		return "weChat/view";
	}
	
	@RequestMapping(value = "/show")
	public String show(String openId, Model model) {
		logger.info("查看微信公众号信息");
		
		if (StringUtils.isEmpty(openId)) {
			return "weChat/edit";
		}
		
		wechat = WeiXinFetchTool.fetchWechatPublicNoInfoBySogouOpenid(openId);
		if (null == wechat) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "openid不存在");
			return "weChat/edit";
		}
		
		model.addAttribute("wechat", wechat);
		
		return "weChat/show";
	}
	
	@RequestMapping(value = "/doEdit")
	public String doEdit(HttpServletRequest request, Model model) {
		logger.info("处理微信公众号信息");
		if (null == wechat) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "新增失败: 抓取微信公众号失败");
			return "weChat/edit";
		}
		
		// 检查openId是否已经存在
		WeChat wechatFlag = weChatService.queryByOpenId(wechat.getOpenId());
		
		if (wechatFlag != null && WECHAT_STATE_NORMAL.equals(wechatFlag.getState())) { // 0-正常
			model.addAttribute(Constants.MSG_TYPE_WARNING, "新增失败: 微信公众号已经存在");
			return list(request, model);
		}
		
		if (wechatFlag != null && WECHAT_STATE_DELETED.equals(wechatFlag.getState())) { // 2-删除
			wechat.setState(WECHAT_STATE_NORMAL);
			weChatService.updateState(wechat);
			model.addAttribute(Constants.MSG_TYPE_SUCCESS, "新增成功");
			return list(request, model);
		}
		
		// 保存
		try {
			wechat.setState(WECHAT_STATE_NORMAL); // 0-正常
			weChatService.insert(wechat);
		} catch (Exception e) {
			model.addAttribute(Constants.MSG_TYPE_DANGER, "新增失败: 微信公众号新增出现异常");
			return list(request, model);
		}
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "新增成功");
		
		return list(request, model);
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, String openId, Model model) {
		logger.info("删除微信公众号信息");
		
		if (StringUtils.isEmpty(openId)) {
			model.addAttribute(Constants.MSG_TYPE_DANGER, "删除失败: 微信公众号openId为空");
			return list(request, model);
		}
		
		WeChat wechatUpdate = new WeChat();
		wechatUpdate.setOpenId(openId);
		wechatUpdate.setState(WECHAT_STATE_DELETED);
		
		weChatService.updateState(wechatUpdate);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "删除成功");
		
		return list(request, model);
	}
	
	@RequestMapping(value = "/frozen")
	public String frozen(HttpServletRequest request, String openId, Model model) {
		return list(request, model);
	}

}
