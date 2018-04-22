package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.CategoryDao;
import com.model.Category;
import com.tools.ConnDB;

public class CategoryDaoImpl implements CategoryDao {

	private ConnDB conn=new ConnDB();
	
	/**
	 * 查询所有分类
	 */
	@Override
	public List<Category> findAll() throws Exception {
		Category form = null;
		List list = new ArrayList();
		String sql = "select * from tb_superType";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Category();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setTypename(rs.getString(2));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	/**
	 * 保存分类信息
	 */
	@Override
	public void save(Category category) throws Exception {
		String sql = "insert into tb_superType(typename) values('" + category.getTypename() + "')";
		conn.executeUpdate(sql);
		conn.close();
	}

}
