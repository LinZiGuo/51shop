package com.service.impl;

import java.util.List;

import com.dao.GoodsDao;
import com.dao.impl.GoodsDaoImpl;
import com.model.Goods;
import com.model.PageBean;
import com.service.GoodsService;
import com.tools.JsonUtil;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public List<Goods> findHitGoods() throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = goodsDao.findHitGoods();
		
		return list;
	}

	@Override
	public List<Goods> findNewGoods() throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = goodsDao.findNewGoods();
		
		return list;
	}

	@Override
	public List<Goods> findNowGoods() throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = goodsDao.findNowGoods();
		
		return list;
	}

	@Override
	public Goods getById(String ID) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		Goods goods = goodsDao.getById(ID);
		
		return goods;
	}

	@Override
	public List<Goods> getRelatedGoods(String typename) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = goodsDao.getRelatedGoods(typename);
		return list;
	}

	@Override
	public PageBean<Goods> findByPage(int pageNumber, int pageSize, String ID) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		//1.创建pagebean
		PageBean<Goods> pageBean = new PageBean<>(pageNumber,pageSize);
		
		//2.设置当前页数据
		List<Goods> data = goodsDao.findByPage(pageNumber,pageSize,ID);
		pageBean.setData(data);
		
		//3.设置总记录数
		int totalRecord = goodsDao.getTotalRecord(ID);
		pageBean.setTotalRecord(totalRecord);
		
		//4.设置总页数
		int totalPage = totalRecord/pageSize;
		if(totalRecord%pageSize != 0) {
			totalPage++;
		}
		pageBean.setTotalPage(totalPage);
		
		return pageBean;
	}

	@Override
	public List<Goods> findAll() throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = goodsDao.findAll();
		
		return list;
	}

	@Override
	public void save(Goods goods,Integer typeId) throws Exception {
		GoodsDao goodsDao = new GoodsDaoImpl();
		goodsDao.save(goods,typeId);
	}

}
