package com.service.impl;

import java.util.List;

import com.dao.OrderDao;
import com.model.Order;
import com.model.OrderItem;
import com.model.PageBean;
import com.service.OrderService;
import com.tools.BeanFactory;
import com.tools.ConnDB;

public class OrderServiceImpl implements OrderService {

	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception{
		try {
			//获取dao
			OrderDao orderDao = (OrderDao) BeanFactory.getBean("OrderDao");
			
			//1.开启事务
			ConnDB.startTransaction();
			
			//2.向Orders表中插入一条
			orderDao.save(order);
			
			//3.向Orderitem表中插入n条
			for (OrderItem oi : order.getItems()) {
				orderDao.save(oi);
			}
			
			//4.事务控制
			ConnDB.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			ConnDB.rollbackAndClose();
			throw e;
		}
	}

	/**
	 * 我的订单
	 */
	@Override
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pagesize, String username) throws Exception {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("OrderDao");
		
		//1.创建pagebean
		PageBean<Order> pageBean = new PageBean<>(pageNumber, pagesize);
		
		//2.查询总条数 设置总条数
		int totalRecord = orderDao.getTotalRecord(username);
		pageBean.setTotalRecord(totalRecord);
		
		//3.查询当前页数据 设置当前页数据
		List<Order> data = orderDao.findMyOrdersByPage(pageBean,username);
		pageBean.setData(data);
		
		return pageBean;
	}

	/**
	 * 订单详情
	 */
	@Override
	public Order getById(String id) throws Exception {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("OrderDao");
		
		return orderDao.getById(id);
	}

	/**
	 * 更新订单
	 */
	@Override
	public void updateOrder(Order order) throws Exception {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("OrderDao");
		
		orderDao.updateOrder(order);
	}

	@Override
	public List<Order> findAllByState(String state) throws Exception {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("OrderDao");
		return orderDao.findAllByState(state);
	}

}
