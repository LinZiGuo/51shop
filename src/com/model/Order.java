package com.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 订单模块
 * @author Administrator
 *
 */
public class Order {

	private String orderID;
	private Integer state;//订单状态   0.未付款   1.已付款  2.已发货  3.已完成  
	private String username;
	private String receiveName;
	private String address;
	private String tel;
	private String orderDate;
	private String beizhu;
	private Float subTotal;
	//表示当前订单属于哪个用户
	private Member member;
	
	//表示当前订单包含的订单项
	private List<OrderItem> items = new ArrayList<>();

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		DateFormat dtf = DateFormat.getDateTimeInstance();
		this.orderDate = dtf.format(orderDate);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public Float getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	
}
