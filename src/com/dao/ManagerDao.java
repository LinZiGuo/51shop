package com.dao;

import com.model.Manager;

public interface ManagerDao {

	Manager getByUsernameAndPwd(String username, String password) throws Exception;

}
