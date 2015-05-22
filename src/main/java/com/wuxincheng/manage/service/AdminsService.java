package com.wuxincheng.manage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.wuxincheng.manage.dao.AdminsDao;
import com.wuxincheng.manage.model.Admins;
import com.wuxincheng.manage.service.AdminsService;

@Repository("adminsService")
public class AdminsService {
	
	@Resource private AdminsDao adminsDao;

	public Admins query(String adminsLogin) {
		Admins admins = new Admins();
		admins.setAdminsLogin(adminsLogin);
		return adminsDao.query(admins);
	}

	public Admins login(String adminsLogin, String adminsPwd) {
		Admins admins = new Admins();
		admins.setAdminsLogin(adminsLogin);
		admins.setAdminsPwd(adminsPwd);
		return adminsDao.query(admins);
	}

	public void insert(Admins admins) {
		adminsDao.insert(admins);
	}

	public void update(Admins admins) {
		adminsDao.update(admins);
	}

	public void modifyPwd(Admins admins) {
		adminsDao.modifyPwd(admins);
	}

	public List<Admins> queryAll() {
		return adminsDao.queryAll();
	}

	public void edit(Admins admins) {
		
	}

}
