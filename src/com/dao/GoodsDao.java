package com.dao;

import java.util.List;

import com.model.Goods;

public interface GoodsDao {

	List<Goods> findHitGoods() throws Exception;

	List<Goods> findNewGoods() throws Exception;

	List<Goods> findNowGoods() throws Exception;

	Goods getById(String ID) throws Exception;

	List<Goods> getRelatedGoods(String typename) throws Exception;

	List<Goods> findByPage(int pageNumber, int pageSize, String ID) throws Exception;

	int getTotalRecord(String ID) throws Exception;

	List<Goods> findAll() throws Exception;

	void save(Goods goods, Integer typeId) throws Exception;

}
