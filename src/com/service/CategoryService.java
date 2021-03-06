package com.service;

import java.util.List;

import com.model.Category;

public interface CategoryService {

	String findAll() throws Exception;

	String findAllFromRedis() throws Exception;

	List<Category> findList() throws Exception;

	void save(Category category) throws Exception;

}
