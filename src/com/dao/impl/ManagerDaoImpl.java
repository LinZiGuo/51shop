package com.dao.impl;

import java.sql.ResultSet;

import com.dao.ManagerDao;
import com.model.Manager;
import com.tools.ConnDB;


public class ManagerDaoImpl implements ManagerDao {
	private ConnDB conn=new ConnDB();

	@Override
	public Manager getByUsernameAndPwd(String username, String password) throws Exception {
		Manager manager = new Manager();
		try {											// 捕捉异常
			String sql = "select manager,PWD from tb_manager where manager='"+username+"' and PWD='"+password+"'";								// 用于实现查询会员信息的SQL语句
			ResultSet rs = conn.executeQuery(sql);					// 执行查询操作
			if (rs.next()) {
				manager.setManager(rs.getString("manager"));
			}
			
		} catch (Exception e) {							// 处理异常
			e.printStackTrace();										// 设置变量的值为0，表示保存会员信息失败
		}
		conn.close();
		return manager;
	}

}
