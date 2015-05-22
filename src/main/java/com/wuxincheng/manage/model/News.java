package com.wuxincheng.manage.model;

import java.io.Serializable;

/**
 * 发表的新闻/微信文章
 */
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String url;

	private String domain;

	/** 源图片地址 */
	private String imgLink;
	
	/** 图片存在本地的位置 */
	private String imgResPath;
	
	/** 图片存在本地处理后的位置 */
	private String imgLocPath;

	private String title;

	private String subTitle;

	private String content;

	private String createTime;

	private String creator;

	private Double truthDegree = 0D;
	private long opinionTimes = 0L;
	
	/** 文章存入日期 */
	private String settleDate;
	
	/**
	 * 文章访问量
	 */
	private Integer readerCount;
	
	/** 虚拟活跃度 */
	private String virtualActive;

	/** 微信公众号名称 */
	private String weChatPublicName;
	/** 微信公众号 */
	private String weChatPublicNO;
	/** 搜狗搜索返回ID */
	private String sogouDocid;
	/** 搜狗搜索微信的ID */
	private String sogouOpenid;
	/** 状态: null或0-正常, (-1)-预发布, 1-不发布, 2-删除 */
	private String state;

	private Long commentId;

	/** T_REALLY_COMMENT中的评论 */
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Double getTruthDegree() {
		return truthDegree;
	}

	public void setTruthDegree(Double truthDegree) {
		this.truthDegree = truthDegree;
	}

	public long getOpinionTimes() {
		return opinionTimes;
	}

	public void setOpinionTimes(long opinionTimes) {
		this.opinionTimes = opinionTimes;
	}

	public String getWeChatPublicName() {
		return weChatPublicName;
	}

	public void setWeChatPublicName(String weChatPublicName) {
		this.weChatPublicName = weChatPublicName;
	}

	public String getWeChatPublicNO() {
		return weChatPublicNO;
	}

	public void setWeChatPublicNO(String weChatPublicNO) {
		this.weChatPublicNO = weChatPublicNO;
	}

	public String getSogouDocid() {
		return sogouDocid;
	}

	public void setSogouDocid(String sogouDocid) {
		this.sogouDocid = sogouDocid;
	}

	public String getSogouOpenid() {
		return sogouOpenid;
	}

	public void setSogouOpenid(String sogouOpenid) {
		this.sogouOpenid = sogouOpenid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	@Override
	public String toString() {
		return "News: [title: " + getTitle() + ", domain: " + getDomain()
				+ ", sogouDocid: " + getSogouDocid() + ", imgLink: "
				+ getImgLink() + ", url: " + getUrl() + ", subTitle: "
				+ getSubTitle() + "]";
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getVirtualActive() {
		return virtualActive;
	}

	public void setVirtualActive(String virtualActive) {
		this.virtualActive = virtualActive;
	}

	public Integer getReaderCount() {
		return readerCount;
	}

	public void setReaderCount(Integer readerCount) {
		this.readerCount = readerCount;
	}

	public String getImgResPath() {
		return imgResPath;
	}

	public void setImgResPath(String imgResPath) {
		this.imgResPath = imgResPath;
	}

	public String getImgLocPath() {
		return imgLocPath;
	}

	public void setImgLocPath(String imgLocPath) {
		this.imgLocPath = imgLocPath;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

}
