package com.dao;

import java.util.List;

import com.model.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category category) throws Exception;

}
