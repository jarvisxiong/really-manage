package com.wuxincheng.manage.model;

import java.io.Serializable;

/**
 * 管理员
 * 
 * @author wuxincheng
 * 
 */
public class Admins implements Serializable {

	private static final long serialVersionUID = 4630414971929898544L;

	private String adminsLogin;
	private String adminsPwd;
	private String adminsPwd2;
	private String adminsName;
	private String sex;
	private String createTime;
	private String memo;
	private String state;
	private String adminsLevel;

	public String getAdminsLogin() {
		return adminsLogin;
	}

	public void setAdminsLogin(String adminsLogin) {
		this.adminsLogin = adminsLogin;
	}

	public String getAdminsPwd() {
		return adminsPwd;
	}

	public void setAdminsPwd(String adminsPwd) {
		this.adminsPwd = adminsPwd;
	}

	public String getAdminsPwd2() {
		return adminsPwd2;
	}

	public void setAdminsPwd2(String adminsPwd2) {
		this.adminsPwd2 = adminsPwd2;
	}

	public String getAdminsName() {
		return adminsName;
	}

	public void setAdminsName(String adminsName) {
		this.adminsName = adminsName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdminsLevel() {
		return adminsLevel;
	}

	public void setAdminsLevel(String adminsLevel) {
		this.adminsLevel = adminsLevel;
	}

}
