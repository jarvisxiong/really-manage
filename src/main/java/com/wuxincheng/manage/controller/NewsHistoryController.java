package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.HashMap;
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
import com.wuxincheng.common.util.DateUtil;
import com.wuxincheng.common.util.Validation;
import com.wuxincheng.manage.Pager;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.NewsService;
import com.wuxincheng.manage.service.WeChatService;

/**
 * 微信文章历史管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/news/history")
public class NewsHistoryController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(NewsHistoryController.class);
	
	/** 每页显示条数 */
	private final Integer pageSize = Pager.PAGER_SIZE;
	
	String currentPage;
	
	@Autowired private NewsService newsService;
	@Autowired private WeChatService weChatService;
	
	/** 查询日期 */
	private String queryStartDate;
	private String queryEndDate;
	
	private String sogouOpenid;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, String currentPage, String queryStartDate, 
			String queryEndDate, String query, String sogouOpenid, Model model) {
		logger.info("显示文章列表页面");
		setMenuFlag(request, "news");
		
		// ---------------------------------
		// 判断当前页码的合法性
		if (Validation.isBlank(currentPage) || !Validation.isInt(currentPage, "0+")) {
			currentPage = "1";
		}
		
		// ---------------------------------
		// 验证是否为查询
		if (!StringUtils.isEmpty(query)) { // 查询
			this.queryStartDate = queryStartDate;
			this.queryEndDate = queryEndDate;
			this.sogouOpenid = sogouOpenid;
		}
		// 验证查询日期
		String queryEndDateAfter = null;
		if (StringUtils.isEmpty(this.queryStartDate) || StringUtils.isEmpty(this.queryEndDate)) {
			this.queryStartDate = null;
			this.queryEndDate = null;
		} else {
			queryEndDateAfter = DateUtil.getSpecifiedDayAfter(this.queryEndDate, "yyyyMMdd");
		}
		// 验证查询的公众号
		if (StringUtils.isEmpty(this.sogouOpenid)){
			this.sogouOpenid = null;
		}
		
		// ---------------------------------
		// 根据当前页处理查询条件
		this.currentPage = currentPage;
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
		
		// 封装查询条件
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("start", start);
		queryParam.put("end", end);
		queryParam.put("history", "history"); // 
		queryParam.put("queryStartDate", this.queryStartDate);
		queryParam.put("queryEndDate", queryEndDateAfter);
		queryParam.put("sogouOpenid", this.sogouOpenid);
		
		Map<String, Object> pager = newsService.queryPager(queryParam);
		
		try {
			if (pager != null && pager.size() > 0) {
				Integer totalCount = (Integer)pager.get("totalCount");
				Integer lastPage = (totalCount/pageSize);
				Integer flag = (totalCount%pageSize)>0?1:0;
				pager.put("lastPage", lastPage + flag);
				
				// 如果当前页数大于总页数, 减1处理
				if (current > (lastPage + flag)) {
					current--;
					this.currentPage = current+"";
				}
				
				pager.put("currentPage", current);
				pager.put("pageSize", pageSize);
				
				model.addAttribute("pager", pager);
			} else {
				model.addAttribute("news", Collections.EMPTY_LIST);
				logger.info("没有查询到文章信息");
			}
		} catch (Exception e) {
			logger.error("在查询文章明细时出现异常", e);
		}
		
		model.addAttribute("queryStartDate", this.queryStartDate);
		model.addAttribute("queryEndDate", this.queryEndDate);
		model.addAttribute("sogouOpenid", this.sogouOpenid);
		
		model.addAttribute("pageSize", pageSize);
		
		@SuppressWarnings("unchecked")
		List<WeChat> weChats = (List<WeChat>)request.getSession().getAttribute("weChats");
		if (weChats == null || weChats.size() < 1) {
			weChats = weChatService.queryAll();
			request.getSession().setAttribute("weChats", weChats);
		}
		
		return "history/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Long newsId, Model model) {
		logger.info("删除文章信息");
		
		if (StringUtils.isEmpty(newsId)) {
			model.addAttribute(Constants.MSG_TYPE_DANGER, "删除失败: 帖子newsId为空");
			return list(request, "1", null, null, null, null, model);
		}
		
		newsService.delete(newsId);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "删除成功");
		
		return list(request, this.currentPage, null, null, null, null, model);
	}

	public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}
	
}
