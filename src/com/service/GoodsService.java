package com.service;

import java.util.List;

import com.model.Goods;
import com.model.PageBean;

public interface GoodsService {

	List<Goods> findHitGoods() throws Exception;

	List<Goods> findNewGoods() throws Exception;

	List<Goods> findNowGoods() throws Exception;

	Goods getById(String ID) throws Exception;

	List<Goods> getRelatedGoods(String typename) throws Exception;

	PageBean<Goods> findByPage(int pageNumber, int pageSize, String ID) throws Exception;

	List<Goods> findAll() throws Exception;

	void save(Goods goods, Integer typeId) throws Exception;

}
