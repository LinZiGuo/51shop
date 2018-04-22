package com.service.impl;

import com.dao.ManagerDao;
import com.model.Manager;
import com.service.AdminService;
import com.tools.BeanFactory;

public class AdminServiceImpl implements AdminService {

	@Override
	public Manager login(String username, String password) throws Exception {
		ManagerDao managerDao = (ManagerDao) BeanFactory.getBean("ManagerDao");
		return managerDao.getByUsernameAndPwd(username, password);
	}

	
}
