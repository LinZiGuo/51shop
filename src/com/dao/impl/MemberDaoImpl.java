package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.MemberDao;
import com.model.Member;
import com.tools.ChStr;
import com.tools.ConnDB;

public class MemberDaoImpl implements MemberDao {

	private ConnDB conn=new ConnDB();
	private ChStr chStr=new ChStr();
	@Override
	public int insert(Member member) {
		int ret = -1;									// 用于记录更新记录的条数
		try {											// 捕捉异常
			String sql = "Insert into tb_Member (userName,trueName,passWord,city,address, "
					+ " postcode,cardNO,cardType,tel,email) values('"
					+ chStr.chStr(member.getUsername()) + "','" + chStr.chStr(member.getTruename()) + "','"
					+ chStr.chStr(member.getPwd()) + "','" + chStr.chStr(member.getCity()) + "','" 
		              + chStr.chStr(member.getAddress())
					+ "','" + chStr.chStr(member.getPostcode()) + "','" + chStr.chStr(member.getCardno()) 
		              + "','"+ chStr.chStr(member.getCardtype()) + "','" + chStr.chStr(member.getTel()) + "','"
		              + chStr.chStr(member.getEmail())
					+ "')";								// 用于实现保存会员信息的SQL语句
			ret = conn.executeUpdate(sql);				// 执行SQL语句实现保存会员信息到数据库
		} catch (Exception e) {							// 处理异常
			e.printStackTrace();							// 输出异常信息
			ret = 0;										// 设置变量的值为0，表示保存会员信息失败
		}
		conn.close();									// 关闭数据库的连接
		return ret;										// 返回更新记录的条数

	}

	@Override
	public List<Member> select() {
		Member form = null;								// 声明会员对象
		List list = new ArrayList();						// 创建一个List集合对象，用于保存会员信息
		String sql = "select * from tb_member";			// 查询全部会员信息的SQL语句
		ResultSet rs = conn.executeQuery(sql);					// 执行查询操作
		try {												// 捕捉异常
			while (rs.next()) {
				form = new Member();							// 实例化一个会员对象
				form.setID(Integer.valueOf(rs.getString(1)));	// 获取会员ID
				list.add(form);								// 把会员信息添加到List集合对象中
			}
		} catch (SQLException ex) {							// 处理异常
		}
		conn.close();										// 关闭数据库的连接
		return list;
 
	}

	/**
	 * 用户注册
	 */
	@Override
	public void save(Member member) {
		try {											// 捕捉异常
			String sql = "Insert into tb_Member (userName,trueName,passWord,city,address, "
					+ " postcode,cardNO,cardType,tel,email) values('"
					+ chStr.chStr(member.getUsername()) + "','" + chStr.chStr(member.getTruename()) + "','"
					+ chStr.chStr(member.getPwd()) + "','" + chStr.chStr(member.getCity()) + "','" 
		              + chStr.chStr(member.getAddress())
					+ "','" + chStr.chStr(member.getPostcode()) + "','" + chStr.chStr(member.getCardno()) 
		              + "','"+ chStr.chStr(member.getCardtype()) + "','" + chStr.chStr(member.getTel()) + "','"
		              + chStr.chStr(member.getEmail())
					+ "')";								// 用于实现保存会员信息的SQL语句
			conn.executeUpdate(sql);				// 执行SQL语句实现保存会员信息到数据库
		} catch (Exception e) {							// 处理异常
			e.printStackTrace();										// 设置变量的值为0，表示保存会员信息失败
		}
		conn.close();
	}

	/**
	 * 用户登录
	 */
	@Override
	public Member getByUsernameAndPwd(String username, String password) throws Exception {
		Member member = new Member();
		try {											// 捕捉异常
			String sql = "select * from tb_Member where username='"+username+"' and password='"+password+"'";								// 用于实现查询会员信息的SQL语句
			ResultSet rs = conn.executeQuery(sql);					// 执行查询操作
			if (rs.next()) {
				member.setUsername(rs.getString("username"));
			}
			
		} catch (Exception e) {							// 处理异常
			e.printStackTrace();										// 设置变量的值为0，表示保存会员信息失败
		}
		conn.close();
		return member;
	}

	@Override
	public List<Member> select(Member member) {
		Member form = null;								// 声明会员对象
		List list = new ArrayList();						// 创建一个List集合对象，用于保存会员信息
		String sql = "select * from tb_member where username='"+member.getUsername()+"'";			// 查询全部会员信息的SQL语句
		ResultSet rs = conn.executeQuery(sql);					// 执行查询操作
		try {												// 捕捉异常
			while (rs.next()) {
				form = new Member();							// 实例化一个会员对象
				form.setID(Integer.valueOf(rs.getString(1)));	// 获取会员ID
				list.add(form);								// 把会员信息添加到List集合对象中
			}
		} catch (SQLException ex) {							// 处理异常
		}
		conn.close();										// 关闭数据库的连接
		return list;
	}

}
