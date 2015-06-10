package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuxincheng.common.util.Constants;
import com.wuxincheng.common.util.Validation;
import com.wuxincheng.manage.model.BlogInfo;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.BlogInfoService;
import com.wuxincheng.manage.service.WeChatService;

/**
 * 博客管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/blogInfo")
public class BlogInfoController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BlogInfoController.class);
	
	/** 每页显示条数 */
	private final Integer pageSize = 10;
	
	@Autowired private BlogInfoService blogInfoService;
	@Autowired private WeChatService typeService;
	
	/**
	 * 显示列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, String currentPage, Model model) {
		logger.info("显示博客列表页面");
		setMenuFlag(request, "blogInfo");
		
		if (Validation.isBlank(currentPage) || !Validation.isInt(currentPage, "0+")) {
			currentPage = "1";
		}
		
		Integer current = Integer.parseInt(currentPage);
		Integer start = null;
		Integer end = null;
		if (current > 1) {
			start = (current - 1) * pageSize;
			end = pageSize;
		} else {
			start = 0;
			end = pageSize;
		}
		
		Map<String, Object> pager = blogInfoService.queryPager(start, end);
		
		try {
			if (pager != null && pager.size() > 0) {
				pager.put("currentPage", currentPage);
				pager.put("pageSize", pageSize);
				
				Integer totalCount = (Integer)pager.get("totalCount");
				Integer lastPage = (totalCount/pageSize);
				Integer flag = (totalCount%pageSize)>0?1:0;
				pager.put("lastPage", lastPage + flag);
				model.addAttribute("pager", pager);
			} else {
				model.addAttribute("blogInfos", Collections.EMPTY_LIST);
				logger.info("没有查询到博客信息");
			}
		} catch (Exception e) {
			logger.error("在查询博客明细时出现异常", e);
		}
		
		return "info/list";
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(String blogId, Model model) {
		List<WeChat> types = typeService.queryAll(); // 查询所有类别
		
		if (StringUtils.isEmpty(blogId)) { // 
			logger.info("显示博客新增页面");
		} else {
			logger.info("显示博客修改页面");
			
			BlogInfo blogInfo = null;
			logger.info("修改博客的编号: " + blogId);
			
			blogInfo = blogInfoService.queryByBlogId(blogId);
			
			logger.info("查询到博客信息 blogInfo: " + blogInfo.toString());
			
			model.addAttribute("blogInfo", blogInfo);
		}
		
		model.addAttribute("types", types);
		
		return "info/edit";
	}
	
	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doEdit")
	public String doEdit(HttpServletRequest request, BlogInfo blogInfo, Model model) {
		logger.info("处理编辑博客数据");
		
		try {
			blogInfoService.edit(blogInfo);
			
			model.addAttribute(Constants.MSG_TYPE_SUCCESS, "博客编辑成功");
		} catch (Exception e) {
			logger.error("在编辑博客时出现异常: ", e);
			model.addAttribute(Constants.MSG_TYPE_DANGER, "博客编辑时出现异常，请联系管理员");
		}
		
		return list(request, "1", model);
	}
	
	/**
	 * 删除
	 * 
	 * @param blogId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, String blogId, Model model) {
		BlogInfo blogInfo = blogInfoService.queryByBlogId(blogId);
		if (null == blogInfo) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "博客已经被删除");
			return list(request, "1", model);
		}
		
		if (StringUtils.isEmpty(blogId)) { // 
			logger.warn("博客不存在，删除失败 blogId: " + blogId);
			model.addAttribute(Constants.MSG_TYPE_WARNING, "博客不存在，删除失败");
		} else {
			logger.info("删博客的编号 blogId: " + blogId);
			
			boolean deleteFlag = blogInfoService.delete(blogId);
			
			if (deleteFlag) {
				model.addAttribute(Constants.MSG_TYPE_SUCCESS, "博客删除成功");
			} else {
				model.addAttribute(Constants.MSG_TYPE_WARNING, "博客删除失败，请重试");
			}
		}
		
		return list(request, "1", model);
	}
	
}
