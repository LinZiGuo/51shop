package com.constant;

public interface Constant {

	/**
	 * 注册成功
	 */
	int REGISTER_SUCCESS = 1;
	
	/**
	 * 注册用户已存在
	 */
	int USER_EXISTED = 0;
	
	/**
	 * 记住用户名
	 */
	String SAVE_NAME = "ok";
	
	/**
	 * redis中存储分类列表的key
	 */
	String STORE_CATEGORY_LIST = "STORE_CATEGORY_LIST";
	
	/**
	 * redis的服务器地址
	 */
	String REDIS_HOST = "127.0.0.1";
	
	/**
	 * redis的服务器端口器
	 */
	int REDIS_PORT = 6379;
	
	/**
	 * 热门商品
	 */
	int IS_HIT = 1;
	
	/**
	 * 不是热门商品
	 */
	int IS_NOT_HIT = 0;
	
	/**
	 * 最新商品
	 */
	int IS_NEW = 1;
	
	/**
	 * 不是最新商品
	 */
	int IS_NOT_NEW = 0;
	
	/**
	 * 订单状态 未付款
	 */
	int ORDER_WEIFUKUAN=0;
	
	/**
	 * 订单状态 已付款
	 */
	int ORDER_YIFUKUAN=1;
	
	/**
	 * 订单状态 已发货
	 */
	int ORDER_YIFAHUO=2;
	
	/**
	 * 订单状态 已完成
	 */
	int ORDER_YIWANCHENG=3;
}
