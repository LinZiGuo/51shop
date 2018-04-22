package com.service;

import com.model.Manager;

public interface AdminService {

	Manager login(String username, String password) throws Exception;

}
