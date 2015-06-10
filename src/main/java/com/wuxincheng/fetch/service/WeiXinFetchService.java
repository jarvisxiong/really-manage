package com.wuxincheng.fetch.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuxincheng.common.util.DateUtil;
import com.wuxincheng.fetch.util.WeiXinFetchTool;
import com.wuxincheng.manage.dao.CommentDao;
import com.wuxincheng.manage.dao.NewsDao;
import com.wuxincheng.manage.dao.WeChatDao;
import com.wuxincheng.manage.exception.ServiceException;
import com.wuxincheng.manage.model.Comment;
import com.wuxincheng.manage.model.News;
import com.wuxincheng.manage.model.WeChat;
import com.wuxincheng.walker.htmlunit.HtmlUnitSpider;

@Service("weiXinFetchService")
public class WeiXinFetchService {
	private static Logger logger = LoggerFactory.getLogger(WeiXinFetchService.class);

	@Autowired private NewsDao newsDao;
	@Autowired private CommentDao commentDao;
	@Autowired private WeChatDao weChatDao;
	
	private Integer insertCount = 0;
	
	private String settleDate = DateUtil.getCurrentDate(new Date(), "yyyyMMdd");
	
	/**
	 * 单独抓取
	 * 
	 * @param encryData
	 * @throws ServiceException
	 */
	public void fetchWeiXinArticle(String encryLink, String openid) throws ServiceException {
		logger.info("开始手动抓取微信公众号文章");
		
		// 查询数据库中已经从微信抓取的文章唯一标识docId
		List<String> savedWeChatNewsDocidCompare = newsDao.getAllWeChatDocid();
		logger.debug("已经查询出数据库中所有的微信文章的docid");
		
		List<News> prepareSaveNews = WeiXinFetchTool.fectArticle(null, null, encryLink, 0);
		if (null == prepareSaveNews || prepareSaveNews.size() < 1) { // 如果没有抓取到数据继续抓取
			logger.warn("抓取的数据为空");
			return;
		}
		
		if (saveCurrentFetchData(null, prepareSaveNews, savedWeChatNewsDocidCompare)) {
			return;
		}
	}
	
	/**
	 * 定时任务抓取微信公众号文章
	 */
	public void processWeiXinFetch() throws ServiceException {
		logger.info("开始定时任务抓取微信公众号文章");
		
		// 查询所有的公众号信息
		List<WeChat> weChats = weChatDao.queryAll();
		if (null == weChats || weChats.size() < 1) { // 如果没有查询到公众号就结束定时任务
			throw new ServiceException(ServiceException.GENERAL_EXCEPTION, "没有查询到公众号信息");
		}
		logger.info("已经查询出" + weChats.size() + "条微信公众号信息");
		
		// 查询数据库中已经从微信抓取的文章唯一标识docId
		List<String> savedWeChatNewsDocidCompare = newsDao.getAllWeChatDocid();
		logger.info("已经查询出数据库中所有的微信文章的docid");
		
		// 根据openId访问搜狗微信搜索
		for (int i = 0; i < weChats.size(); i++) {
			insertCount = 0; // 新增标志置0
			WeChat weChat = weChats.get(i);
			logger.info("抓取第"+(i+1)+"个公众号["+weChat.getPublicName()+"("+weChat.getPublicNO()+")]文章数据");
			if (StringUtils.isEmpty(weChat.getOpenId())) { // 如果openId为空, 继续下一个公众号文章的抓取
				logger.warn("抓取微信文章失败: openid为空");
				continue;
			}
			
			if (StringUtils.isEmpty(weChat.getEncryData())) { // 如果EncryData为空, 继续下一个公众号文章的抓取
				logger.warn("抓取微信文章失败: EncryData为空");
				continue;
			}
			
			int page = 1;
			List<News> prepareSaveNews = null;
			while (page < 10) { // 最多抓取到第10页
				prepareSaveNews = HtmlUnitSpider.wechatParse(weChat.getOpenId(), weChat.getEncryData(), page);
				// WeiXinFetchTool.fectArticle(weChat.getOpenId(), weChat.getEncryData(), null, page);
				if (null == prepareSaveNews || prepareSaveNews.size() < 1) { // 如果没有抓取到数据继续抓取
					logger.warn("抓取的数据为空 page={}", page);
					break;
				}
				
				if (saveCurrentFetchData(weChat.getOpenId(), prepareSaveNews, savedWeChatNewsDocidCompare)) {
					break;
				}
				
				page++;
			}
			
			if (insertCount > 0) {
				// 更新公众号抓取的时间
				weChat.setFetchTime(DateUtil.getCurrentDate(new Date(), "yyyyMMdd HH:mm:ss"));
				weChatDao.updateFetchTime(weChat);
			}
			
			logger.info("微信公众号["+weChat.getPublicName()+"("+weChat.getPublicNO()+")]文章抓取结束.");
		}
		
		logger.info("定时任务抓取微信公众号文章完成");
	}

	/**
	 * 保存抓取的文章
	 * 
	 * @param openid
	 *            搜狗的openId
	 * @param prepareSaveNews
	 *            需要保存的数据
	 * @param docidSavedCompare
	 *            数据库中已经保存文章的docId
	 */
	private boolean saveCurrentFetchData(String openid, List<News> prepareSaveNews, List<String> docidSavedCompare) {
		// 是否继续抓取这个公众号的文章, false-继续, true-不继续
		boolean isFetchContinue = false;
		
		for (News prepareSaveNew : prepareSaveNews) {
			if (docidSavedCompare != null && docidSavedCompare.size() > 0) {
				if (docidSavedCompare.contains(prepareSaveNew.getSogouDocid())) {
					// logger.info("文章[" + prepareSaveNew.getTitle() + "]在数据库中已经存在");
					// 验证是否已经保存此微信文章了, docId是搜狗返回的唯一标识
					isFetchContinue = true;
					continue;
				}
			}
			
			prepareSaveNew.setSogouOpenid(openid);
			prepareSaveNew.setReaderCount(0);
			prepareSaveNew.setSettleDate(settleDate);
			newsDao.insert(prepareSaveNew);
			
			int newsid = newsDao.queryNewsIdByDocid(prepareSaveNew.getSogouDocid());
			Comment comment = new Comment();
			comment.setNewsId(newsid+"");
			comment.setBackground(2014000000);
			comment.setAlpha(-1);
			comment.setCreator("2");
			comment.setState("1"); // 1-不显示, 0-显示
			commentDao.insert(comment);
			
			insertCount++;
			
			logger.info("添加文章成功, data: " + prepareSaveNew.toString());
		}
		
		return isFetchContinue;
	}
	
}
