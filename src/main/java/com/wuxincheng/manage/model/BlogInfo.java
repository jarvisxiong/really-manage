package com.wuxincheng.manage.model;

import java.io.Serializable;

/**
 * 博客信息
 * 
 * @author wuxincheng
 * 
 */
public class BlogInfo implements Serializable {

	private static final long serialVersionUID = -1305416709459511421L;

	/** 博客主键 */
	private Integer blogId;
	/** 标题 */
	private String blogTitle;
	/** 博客类型主键 */
	private String blogType;
	/** 类型名称 */
	private String blogTypeName;
	/** 内容 */
	private String blogContent;
	/** 创建时间 */
	private String blogTime;
	/** 阅读次数 */
	private Integer readCount;
	/** 分享次数 */
	private Integer shareCount;
	/** 博客状态: 0-正常, 1-不发布(禁用), 2-删除 */
	private String blogState;
	/** 博客连接 */
	private String blogLink;

	// ---
	/** 博客来源 */
	private String resource;
	/** 作者 */
	private String author;
	/** 博客简介(用于列表显示) */
	private String subContent;

	/** 图片连接 */
	private String picLink;

	/** 更新时间 */
	private String updateTime;

	public BlogInfo() {
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getBlogType() {
		return blogType;
	}

	public void setBlogType(String blogType) {
		this.blogType = blogType;
	}

	public String getBlogTypeName() {
		return blogTypeName;
	}

	public void setBlogTypeName(String blogTypeName) {
		this.blogTypeName = blogTypeName;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public String getBlogLink() {
		return blogLink;
	}

	public void setBlogLink(String blogLink) {
		this.blogLink = blogLink;
	}

	public String getBlogTime() {
		return blogTime;
	}

	public void setBlogTime(String blogTime) {
		this.blogTime = blogTime;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public String getBlogState() {
		return blogState;
	}

	public void setBlogState(String blogState) {
		this.blogState = blogState;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubContent() {
		return subContent;
	}

	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}

	public String getPicLink() {
		return picLink;
	}

	public void setPicLink(String picLink) {
		this.picLink = picLink;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BlogInfo [blogId=" + blogId + ", blogTitle=" + blogTitle
				+ ", blogType=" + blogType + ", blogTypeName=" + blogTypeName
				+ ", blogTime=" + blogTime + ", readCount=" + readCount
				+ ", shareCount=" + shareCount + ", blogState=" + blogState
				+ ", blogLink=" + blogLink + ", resource=" + resource
				+ ", author=" + author + ", subContent=" + subContent
				+ ", picLink=" + picLink + "]";
	}

}
