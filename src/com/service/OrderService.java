package com.service;

import java.util.List;

import com.model.Order;
import com.model.PageBean;

public interface OrderService {

	void save(Order order) throws Exception;

	PageBean<Order> findMyOrdersByPage(int pageNumber, int pagesize, String username) throws Exception;

	Order getById(String id) throws Exception;

	void updateOrder(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

}
