package com.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import sun.util.resources.cldr.es.TimeZoneNames_es_419;

public class ConnDB {

	public Connection conn=null;
	public Statement statement=null;
	public ResultSet rs=null;
	
	private static String dbClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbUrl="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=db_shop";
	private static String dbUser="sa";
	private static String dbPwd="123456";
	/**
	 * 功能：创建与数据库的连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(dbClassName).newInstance();
			conn=DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (conn==null) {
			System.err.println("DbConnectionManager.getConnection():"
					+dbClassName+"\r\n :"+dbUrl+"\r\n "
					+dbUser+"/"+dbPwd);
		}
		return conn;
	}
	/**
	 * 功能：更新数据
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql){
		int result=0;
		try {
			conn=getConnection();
			statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result=statement.executeUpdate(sql);
		} catch (SQLException e) {
			result=0;
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 功能：根据指定的sql语句查询数据
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(String sql){
		try {
			conn=getConnection();
			statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 功能：根据指定的SQL语句查询数据 并转换成List<Map<String, Object>>
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> resultSetToMap(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			conn=getConnection();
			statement=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs=statement.executeQuery(sql);
			ResultSetMetaData rsmtd;
			
			while (rs.next()) {
				rsmtd = rs.getMetaData();
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				for (int i = 1; i <= rsmtd.getColumnCount(); i++) {
					String columnLabel = rsmtd.getColumnLabel(i);
					map.put(columnLabel, rs.getObject(columnLabel));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			
		}
		return list;
	}
	
	/**
	 * 功能：关闭数据库连接
	 */
	public void close(){
		try {
			if(rs!=null)
				rs.close();
			if (statement!=null) {
				statement.close();
			}
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：更新数据后获取生成的自动编号
	 * 
	 * @param sql
	 * @return
	 */
	public int executeUpdate_id(String sql) {
		int result = 0;
		try {										// 捕捉异常
			conn = getConnection();					// 获取数据库连接
			// 创建用于执行SQL语句的Statement对象
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			result = statement.executeUpdate(sql);			// 执行SQL语句
			String ID = "select @@IDENTITY as id";		// 定义用于获取刚刚生成的自动编号的SQL语句
			rs = statement.executeQuery(ID);				// 获取刚刚生成的自动编号
			if (rs.next()) {							// 如果存在数据
				int autoID = rs.getInt("id");			// 把获取到的自动编号保存到变量autoID中
				result = autoID;
			}
		} catch (SQLException ex) {					// 处理异常
			result = 0;
		}
		return result;								// 返回获取结果
	}

	public static void main(String[] args){
		if(getConnection()!=null){
			System.out.println("数据库连接成功！");
		}
	}
	
	/**
	 * 开启事务
	 * @throws SQLException 
	 */
	public static void startTransaction() throws SQLException {
		getConnection().setAutoCommit(false);
	}
	
	/**
	 * 事务提交
	 */
	public static void commitAndClose() {
		try {
			Connection conn = getConnection();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 事务回滚
	 */
	public static void rollbackAndClose() {
		try {
			Connection conn = getConnection();
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
