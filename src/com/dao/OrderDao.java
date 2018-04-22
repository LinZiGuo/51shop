package com.dao;

import java.util.List;

import com.model.Order;
import com.model.OrderItem;
import com.model.PageBean;

public interface OrderDao {

	void save(Order order) throws Exception;

	void save(OrderItem oi) throws Exception;

	int getTotalRecord(String username) throws Exception;

	List<Order> findMyOrdersByPage(PageBean<Order> pageBean, String username) throws Exception;

	Order getById(String id) throws Exception;

	void updateOrder(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
