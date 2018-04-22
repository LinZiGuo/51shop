package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.constant.Constant;
import com.dao.OrderDao;
import com.model.Goods;
import com.model.Order;
import com.model.OrderItem;
import com.model.PageBean;
import com.tools.ChStr;
import com.tools.ConnDB;

public class OrderDaoImpl implements OrderDao {

	private ConnDB conn=new ConnDB();
	private ChStr chStr=new ChStr();
	
	/**
	 * 保存订单
	 */
	@Override
	public void save(Order order) throws Exception {
		String sql = "Insert into tb_order (OrderID,bnumber,username,recevieName,address,"
				+ "tel,OrderDate,bz) values('" + order.getOrderID() + "'," + order.getState() + ",'"
				+ chStr.chStr(order.getMember().getUsername()) + "','" + chStr.chStr(order.getReceiveName()) + "','"
	            + chStr.chStr(order.getAddress()) + "','" + chStr.chStr(order.getTel()) + "','"
	            + order.getOrderDate() + "','" + chStr.chStr(order.getBeizhu())
				+ "')";
		
		conn.executeUpdate(sql);
		conn.close();
	}

	/**
	 * 保存订单项
	 */
	@Override
	public void save(OrderItem oi) throws Exception {
		String sql = "Insert into tb_order_detail (orderID,goodsID,price,number) "
				+ "values('" + oi.getOrder().getOrderID() + "',"
				+ oi.getGoods().getID() + "," + oi.getSubtotal() + "," + oi.getCount()
				+ ")";
		conn.executeUpdate(sql);
		conn.close();
	}

	/**
	 * 获取我的订单总条数
	 */
	@Override
	public int getTotalRecord(String username) throws Exception {
		String sql = "select count(*) from tb_order where username='" + username + "'";
		ResultSet rs = conn.executeQuery(sql);
		int totalRecord = -1;
		if (rs.next()) {
			totalRecord = rs.getInt(1);
		}
		conn.close();
		return totalRecord;
	}

	/**
	 * 获取我的订单 当前页数据
	 */
	@Override
	public List<Order> findMyOrdersByPage(PageBean<Order> pageBean, String username) throws Exception {
		Order order = null;
		List<Order> list = new ArrayList<>();
		
		int start = 1+(pageBean.getPageNumber()-1)*pageBean.getPageSize();
		int end = start+pageBean.getPageSize();
		//查询所有订单基本信息
		String sql = "select * from( select ROW_NUMBER() over(order by OrderID) temp,*"+
				" from tb_order"+ ") tt where temp>="+start+" and temp<="+end + 
				" order by orderDate desc";
		
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				order = new Order();
				order.setOrderID(rs.getString("OrderID"));
				order.setUsername(rs.getString("username"));
				order.setState(rs.getInt("bnumber"));
				order.setReceiveName(rs.getString("recevieName"));
				order.setAddress(rs.getString("address"));
				order.setTel(rs.getString("tel"));
				order.setOrderDate(rs.getTimestamp("OrderDate"));
				order.setBeizhu(rs.getString("bz"));
				list.add(order);
			}
		} catch (SQLException ex) {
		}
		
		//遍历订单集合获得每一个订单 查询每个订单订单项
		for (Order o : list) {
			sql="select t1.orderID,picture,goodsName,t2.price,number " +
					"from tb_order_detail t1,tb_goods t2 " + 
					"where t1.goodsID=t2.ID and t1.orderID='" + o.getOrderID() + "'";
			List<Map<String, Object>> maplist = conn.resultSetToMap(sql);
			float sum = 0.0f; 
			
			//遍历maplist 获取每一个订单项详情，封装成orderitem，将其加入当前订单的订单项列表中
			for (Map<String, Object> map : maplist) {
				//1.封装成orderitem
				//a.创建orderitem
				OrderItem oi = new OrderItem();
				
				//b.封装orderitem
				oi.setCount((Integer) map.get("number"));
				oi.setSubtotal(Float.valueOf(map.get("price").toString()) * oi.getCount());
				
				//c.手动封装goods
				Goods goods = new Goods();
				
				BeanUtils.populate(goods, map);
				goods.setGoodsname((String) map.get("goodsName"));
				
				oi.setGoods(goods);
				sum += oi.getSubtotal();
				//2.将orderitem放入order的订单列表
				o.getItems().add(oi);
			}
			o.setSubTotal(sum);
		}
		
		conn.close();
		return list;
	}

	@Override
	public Order getById(String id) throws Exception {
		Order order = null;
		String sql = "select * from tb_order where orderID='" + id + "'";
		ResultSet rs = conn.executeQuery(sql);
		
		if (rs.next()) {
			order = new Order();
			order.setOrderID(rs.getString("OrderID"));
			order.setUsername(rs.getString("username"));
			order.setState(rs.getInt("bnumber"));
			order.setReceiveName(rs.getString("recevieName"));
			order.setAddress(rs.getString("address"));
			order.setTel(rs.getString("tel"));
			order.setOrderDate(rs.getTimestamp("OrderDate"));
			order.setBeizhu(rs.getString("bz"));
		}
		
		sql="select t1.orderID,picture,goodsName,t2.price,number " +
				"from tb_order_detail t1,tb_goods t2 " + 
				"where t1.goodsID=t2.ID and t1.orderID='" + order.getOrderID() + "'";
		List<Map<String, Object>> maplist = conn.resultSetToMap(sql);
		float sum = 0.0f;
		
		for (Map<String, Object> map : maplist) {
			//1.封装成orderitem
			//a.创建orderitem
			OrderItem oi = new OrderItem();
			
			//b.封装orderitem
			oi.setCount((Integer) map.get("number"));
			oi.setSubtotal(Float.valueOf(map.get("price").toString()) * oi.getCount());
			
			//c.手动封装goods
			Goods goods = new Goods();
			
			BeanUtils.populate(goods, map);
			goods.setGoodsname((String) map.get("goodsName"));
			
			oi.setGoods(goods);
			sum += oi.getSubtotal();
			//2.将orderitem放入order的订单列表
			order.getItems().add(oi);
		}
		order.setSubTotal(sum);
		return order;
	}

	@Override
	public void updateOrder(Order order) throws Exception {
		String sql = "update tb_order set bnumber="+order.getState()+" where orderID='" + order.getOrderID() + "'";
		conn.executeUpdate(sql);
		conn.close();
	}

	@Override
	public List<Order> findAllByState(String state) throws Exception {
		Order order = null;
		String sql = "select * from tb_order";
		
		if (state ==null||state.trim().length()==0) {
			sql+=" order by OrderDate desc";
		} else {
			int temp = Integer.valueOf(state);
			sql+=" where bnumber="+temp+" order by OrderDate desc";			
		}
		ResultSet rs = conn.executeQuery(sql);
		List<Order> list = new ArrayList<>();
		while (rs.next()) {
			order = new Order();
			order.setOrderID(rs.getString("OrderID"));
			order.setUsername(rs.getString("username"));
			order.setState(rs.getInt("bnumber"));
			order.setReceiveName(rs.getString("recevieName"));
			order.setAddress(rs.getString("address"));
			order.setTel(rs.getString("tel"));
			order.setOrderDate(rs.getTimestamp("OrderDate"));
			order.setBeizhu(rs.getString("bz"));
			list.add(order);
		}
		return list;
	}

}
