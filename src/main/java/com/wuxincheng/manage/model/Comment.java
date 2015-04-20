package com.wuxincheng.manage.model;

import java.io.Serializable;

public class Comment implements Serializable {
	private static final long serialVersionUID = 8743293013760225307L;

	private Long id;

	private String newsId;

	private String content;

	private String creator;

	private String createTime;

	private int background;

	private int alpha;

	private double hotspotValue = 0D;

	private long attention = 0L;

	private String state;

	private int truthDegree;

	private int relationship;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public double getHotspotValue() {
		return hotspotValue;
	}

	public void setHotspotValue(double hotspotValue) {
		this.hotspotValue = hotspotValue;
	}

	public long getAttention() {
		return attention;
	}

	public void setAttention(long attention) {
		this.attention = attention;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getTruthDegree() {
		return truthDegree;
	}

	public void setTruthDegree(int truthDegree) {
		this.truthDegree = truthDegree;
	}

	public int getRelationship() {
		return relationship;
	}

	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}

}
