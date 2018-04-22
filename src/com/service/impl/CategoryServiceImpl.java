package com.service.impl;

import java.util.List;

import com.constant.Constant;
import com.dao.CategoryDao;
import com.dao.impl.CategoryDaoImpl;
import com.model.Category;
import com.service.CategoryService;
import com.tools.BeanFactory;
import com.tools.JedisUtils;
import com.tools.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	/**
	 * 查询所有分类
	 */
	@Override
	public String findAll() throws Exception {
//		// 1.调用dao 查询所有分类
//		CategoryDao categoryDao = new CategoryDaoImpl();
//		List<Category> list = categoryDao.findAll();
		List<Category> list = findList();
		
		//2.将list转换成json字符串
		if(list != null && list.size() > 0) {
			return JsonUtil.list2json(list);
		}
		return null;
	}

	/**
	 * 从redis中获取所有的分类
	 */
	@Override
	public String findAllFromRedis() throws Exception {
//		// 1.获取jedis
//		Jedis jedis = JedisUtils.getJedis();
//		//2.从redis中获取数据
//		String value = jedis.get(Constant.STORE_CATEGORY_LIST);
//		//3.判断数据是否为空
//		if (value == null) {
//			//3.1若为空，调用findAll() 将查询的结果放入redis 返回
//			value = findAll();
//			
//			jedis.set(Constant.STORE_CATEGORY_LIST, value);
//		}
//		return value;
		
		Jedis jedis = null;
		String value = null;
		try {
			//1.从redis获取分类信息
			try {
				//1.1获取连接
				jedis = JedisUtils.getJedis();
				
				//1.2获取数据 判断数据是否为空
				value = jedis.get(Constant.STORE_CATEGORY_LIST);
				
				//1.3若不为空 直接返回数据
				if (value != null) {
					return value;
				}
			} catch (Exception e) {
				
			}
			
			//2.redis中 若无数据 则从数据库中获取 并将数据放入redis中
			value = findAll();
			
			//3.将value放入redis中
			try {
				jedis.set(Constant.STORE_CATEGORY_LIST, value);
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			//移除jedis
			JedisUtils.closeJedis(jedis);
		}
		return value;
	}

	/**
	 * 后台展示所有分类
	 */
	@Override
	public List<Category> findList() throws Exception {
		CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("CategoryDao");
		return categoryDao.findAll();
	}

	/**
	 * 保存分类信息
	 */
	@Override
	public void save(Category category) throws Exception {
		CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("CategoryDao");
		categoryDao.save(category);
		
		Jedis jedis = null;
		try {
			jedis = JedisUtils.getJedis();
			jedis.del(Constant.STORE_CATEGORY_LIST);
		} finally {
			JedisUtils.closeJedis(jedis);
		}
	}

}
