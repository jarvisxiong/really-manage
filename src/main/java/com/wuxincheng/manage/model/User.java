package com.wuxincheng.manage.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -2245998103101886883L;

	private Long id;

	private String email;

	private String lastLoginTime;

	private String mobilePhone;

	private String registerTime;

	private String username;

	private Double truthDegreeScore;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTruthDegreeScore() {
		return truthDegreeScore;
	}

	public void setTruthDegreeScore(Double truthDegreeScore) {
		this.truthDegreeScore = truthDegreeScore;
	}

}
