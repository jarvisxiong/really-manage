package com.wuxincheng.manage.controller;

import java.util.Collections;
import java.util.Date;
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

import com.wuxincheng.common.util.ConfigHelper;
import com.wuxincheng.common.util.Constants;
import com.wuxincheng.common.util.DateUtil;
import com.wuxincheng.common.util.HttpClientHelper;
import com.wuxincheng.common.util.Validation;
import com.wuxincheng.fetch.helper.FetchHtmlHelper;
import com.wuxincheng.fetch.service.WeiXinFetchService;
import com.wuxincheng.manage.Pager;
import com.wuxincheng.manage.exception.ServiceException;
import com.wuxincheng.manage.model.Comment;
import com.wuxincheng.manage.model.News;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.manage.service.NewsService;
import com.wuxincheng.manage.service.WeChatService;

/**
 * 微信文章管理/素材管理
 * 
 * @author wuxincheng
 *
 */
@Controller
@RequestMapping("/manage/news")
public class NewsController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	private static final String MANAGE_NAME = Constants.MANAGE_NAME;
	
	/** 每页显示条数 */
	private final Integer pageSize = Pager.PAGER_SIZE;
	
	private String currentPage;
	
	@Autowired private NewsService newsService;
	@Autowired private WeChatService weChatService;
	@Autowired private WeiXinFetchService weiXinFetchService;
	
	/** 查询日期 */
	private String queryStartDate;
	private String queryEndDate;
	
	private String sogouOpenid;
	
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, String currentPage, String queryStartDate, 
			String queryEndDate, String query, String sogouOpenid, Model model) {
		logger.info(MANAGE_NAME+"显示素材管理中的文章列表");
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
		} else {
			this.queryStartDate = DateUtil.getCurrentDate(new Date(), "yyyyMMdd");
			this.queryEndDate = DateUtil.getSpecifiedDayAfter(this.queryStartDate, "yyyyMMdd");
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
		queryParam.put("resource", "resource"); // 
		queryParam.put("queryStartDate", this.queryStartDate);
		queryParam.put("queryEndDate", queryEndDateAfter);
		queryParam.put("sogouOpenid", this.sogouOpenid);
		
		// ---------------------------------
		Map<String, Object> pager = newsService.queryPager(queryParam);
		
		try {
			if (pager != null && pager.size() > 0) { // 判断查询出来的数据是否为空
				Integer totalCount = (Integer)pager.get("totalCount"); // 总记录数
				Integer lastPage = (totalCount/pageSize); // 最后一页
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
				logger.info(MANAGE_NAME+"没有查询到文章信息");
			}
		} catch (Exception e) {
			logger.error(MANAGE_NAME+"在查询文章明细时出现异常", e);
		}
		
		// 查询条件的回显
		model.addAttribute("queryStartDate", this.queryStartDate);
		model.addAttribute("queryEndDate", this.queryEndDate);
		model.addAttribute("sogouOpenid", this.sogouOpenid);
		
		model.addAttribute("pageSize", pageSize);
		
		// 微信公众号
		@SuppressWarnings("unchecked")
		List<WeChat> weChats = (List<WeChat>)request.getSession().getAttribute("weChats");
		if (weChats == null || weChats.size() < 1) {
			weChats = weChatService.queryAll();
			request.getSession().setAttribute("weChats", weChats);
		}
		
		return "news/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Long newsId, Model model) {
		logger.info(MANAGE_NAME+"删除文章信息");
		
		if (StringUtils.isEmpty(newsId)) {
			model.addAttribute(Constants.MSG_TYPE_DANGER, "删除失败: 帖子newsId为空");
			return list(request, "1", null, null, null, null, model);
		}
		
		newsService.delete(newsId);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "删除成功");
		
		return list(request, this.currentPage, null, null, null, null, model);
	}
	
	@RequestMapping(value = "/intoDBatch")
	public String intoDBatch(HttpServletRequest request, Long[] newsIds, Model model) {
		logger.info(MANAGE_NAME+"入库");
		
		if (newsIds.length < 1) {
			model.addAttribute(Constants.MSG_TYPE_WARNING, "入库失败，请选择入库文章");
			return list(request, "1", null, null, null, null, model);
		}
		
		newsService.intoDBatch(newsIds);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "入库成功");
		
		return list(request, this.currentPage, null, null, null, null, model);
	}
	
	@RequestMapping(value = "/praeUrl")
	public String praeUrl() {
		logger.info(MANAGE_NAME+"显示解析URL页面");
		return "news/prae_url";
	}
	
	@RequestMapping(value = "/praeShow")
	public String praeShow(String url, Model model) {
		logger.info(MANAGE_NAME+"解析URL, 并显示解析后的内容");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("news_url", url);
		
		Map<String, String> responseData = FetchHtmlHelper.getData(url);
		
		if(responseData == null) {
			model.addAttribute("message", "没有找到文章内容");
			return "news/prae_url";
		}
		
		News news = new News();
		news.setTitle(responseData.get("news_title").toString());
		// news.setImgLink(responseData.get("news_img_link").toString());
		news.setImgLocPath(responseData.get("img_loc_path").toString());
		news.setDomain(responseData.get("news_domain").toString());
		news.setUrl(url);
		
		model.addAttribute("news", news);
		
		return "news/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String newsId, Model model) {
		if (StringUtils.isEmpty(newsId)) { // 
			logger.info(MANAGE_NAME+"显示文章新增页面");
		} else {
			logger.info(MANAGE_NAME+"显示文章修改页面");
			
			News news = null;
			logger.info(MANAGE_NAME+"修改文章的编号: " + newsId);
			
			news = newsService.queryNewsById(newsId);
			
			logger.info(MANAGE_NAME+"查询到文章信息 news: " + news.toString());
			
			model.addAttribute("news", news);
		}
		
		return "news/edit";
	}
	
	@RequestMapping(value = "/doEdit")
	public String doEdit(HttpServletRequest request, News news, Model model) {
		logger.info(MANAGE_NAME+"处理编辑文章数据");
		
		try {
			newsService.edit(news);
			
			model.addAttribute(Constants.MSG_TYPE_SUCCESS, "文章编辑成功");
		} catch (Exception e) {
			logger.error(MANAGE_NAME+"在编辑文章时出现异常: ", e);
			model.addAttribute(Constants.MSG_TYPE_DANGER, "文章编辑时出现异常，请联系管理员");
		}
		
		return list(request, this.currentPage, null, null, null, null, model);
	}
	
	@RequestMapping(value = "/send")
	public String send(HttpServletRequest request, String newsId, Model model) {
		logger.info(MANAGE_NAME+"发布文章");
		
		if (!StringUtils.isEmpty(newsId)) {
			newsService.sendNews4App(newsId);
		}
		
		return list(request, this.currentPage, null, null, null, null, model);
	}
	
	@RequestMapping(value = "/comment")
	public String comment(String newsId, String commentId, Model model) {
		logger.info(MANAGE_NAME+"显示评论页面, newsId=" + newsId + ", commentId=" +commentId);
		
		model.addAttribute("newsId", newsId);
		model.addAttribute("commentId", commentId);
		
		return "news/comment";
	}
	
	@RequestMapping(value = "/doComment")
	public String doComment(HttpServletRequest request, Comment comment, Model model) {
		logger.info(MANAGE_NAME+"处理文章评论的数据");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("commentId", comment.getId());
		data.put("userId", "2");
		data.put("truthDegree", comment.getTruthDegree());
		data.put("relationship", comment.getRelationship());
		data.put("content", comment.getContent());
		
		String serverBase = ConfigHelper.getInstance().getConfig("serverBase", "config.properties");
		String url = serverBase + "/opinion/add";
		HttpClientHelper.doPost(url, data);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "评论成功");
		
		return list(request, this.currentPage, null, null, null, null, model);
	}

	@RequestMapping(value = "/fetchManually")
	public String fetchManually(HttpServletRequest request, Model model) {
		logger.info("{}开始启动抓取微信文章", MANAGE_NAME);
		try {
			weiXinFetchService.processWeiXinFetch();
		} catch (ServiceException e) {
			logger.error("{}启动抓取出现异常", MANAGE_NAME, e);
		}
		logger.info("{}启动抓取微信文章结束", MANAGE_NAME);
		
		model.addAttribute(Constants.MSG_TYPE_SUCCESS, "抓取完成");
		
		return list(request, "1", null, null, null, null, model);
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
