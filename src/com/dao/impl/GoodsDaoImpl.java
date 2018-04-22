package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.constant.Constant;
import com.dao.GoodsDao;
import com.model.Goods;
import com.tools.ConnDB;

public class GoodsDaoImpl implements GoodsDao {

	private ConnDB conn=new ConnDB();
	
	@Override
	public List<Goods> findHitGoods() throws Exception {
		Goods form = null;
		List list = new ArrayList();
		String sql = "select top 2 ID,GoodsName,nowprice,picture " + 
		"from tb_goods order by hit desc";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString(2));
				form.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
				form.setPicture(rs.getString("picture"));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public List<Goods> findNewGoods() throws Exception {
		Goods form = null;
		List list = new ArrayList();
		String sql = "select top 12 t1.ID, t1.GoodsName,t1.price,t1.picture,t2.TypeName "
				+"from tb_goods t1,tb_subType t2 where t1.typeID=t2.ID and "
				+"t1.newGoods=1 order by t1.INTime desc";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString(2));
				form.setTypename(rs.getString("typename"));
				form.setPrice(Float.valueOf(rs.getFloat("price")));
				form.setPicture(rs.getString("picture"));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public List<Goods> findNowGoods() throws Exception {
		Goods form = null;
		List list = new ArrayList();
		String sql = "select top 12 t1.ID, t1.GoodsName,t1.price,t1.nowPrice,t1.picture,t2.TypeName "
				+"from tb_goods t1,tb_subType t2 where t1.typeID=t2.ID and t1.sale=1 "
				+"order by t1.INTime desc";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString(2));
				form.setTypename(rs.getString("typename"));
				form.setPrice(Float.valueOf(rs.getFloat("price")));
				form.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
				form.setPicture(rs.getString("picture"));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public Goods getById(String ID) throws Exception {
		Goods goods = null;
		String sql = "select t1.*,t2.typename "+
				"from tb_goods t1,tb_subType t2 "+
				"where t1.typeID=t2.ID and t1.ID="+ID;
		ResultSet rs = conn.executeQuery(sql);
		if (rs.next()) {
			goods = new Goods();
			goods.setID(Integer.valueOf(rs.getString(1)));
			goods.setGoodsname(rs.getString("goodsname"));
			goods.setTypename(rs.getString("typename"));
			goods.setIntroduce(rs.getString("introduce"));
			goods.setPrice(Float.valueOf(rs.getFloat("price")));
			goods.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
			goods.setPicture(rs.getString("picture"));
		}
		
		conn.close();
		return goods;
	}

	@Override
	public List<Goods> getRelatedGoods(String typename) throws Exception {
		Goods form = null;
		List list = new ArrayList();
		String sql = "select top 6 t1.ID,GoodsName,nowprice,picture,TypeName "+
				"from tb_goods t1,tb_subtype t2 where t2.ID=t1.typeID and typename='" + typename+"'";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString("goodsname"));
				form.setTypename(rs.getString("typename"));
				form.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
				form.setPicture(rs.getString("picture"));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public List<Goods> findByPage(int pageNumber, int pageSize, String ID) throws Exception {
		Goods form = null;
		List list = new ArrayList();
		
		int start = 1+(pageNumber-1)*pageSize;
		int end = start+pageSize;
		
//		String sql = "select * from tb_goods "+
//				"where typeID in (select t2.ID from tb_superType t1,tb_subType t2 "+
//				"where t1.ID=t2.superType and t1.ID="+ID+" )";
		
		String sql = "select * from( select ROW_NUMBER() over(order by ID) temp,*"+
				" from tb_goods where typeID in "+
				"(select t2.ID from tb_superType t1,tb_subType t2 "+
				"where t1.ID=t2.superType and t1.ID="+ID+" )"+
				") tt where temp>="+start+" and temp<="+end;
		
//		select *
//		from (
//		    select ROW_NUMBER() over(order by ID) temp,*
//			from tb_goods
//			where typeID>35
//		) tt
//		where temp>0 and temp<13
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString("goodsname"));
				form.setPrice(Float.valueOf(rs.getFloat("price")));
				form.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
				form.setPicture(rs.getString("picture"));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public int getTotalRecord(String ID) throws Exception {
		int totalRecord=0;
		String sql = "select count(*) from tb_goods "+
				"where typeID in (select t2.ID from tb_superType t1,tb_subType t2 "+
				"where t1.ID=t2.superType and t1.ID="+ID+" )";
		ResultSet rs = conn.executeQuery(sql);
		
		if(rs.next()) {
			totalRecord=Integer.valueOf(rs.getString(1));
		}
		
		return totalRecord;
	}

	@Override
	public List<Goods> findAll() throws Exception {
		Goods form = null;
		List list = new ArrayList();
		String sql = "select t1.*,t2.typename from tb_goods t1,tb_subtype t2 where t1.typeId=t2.Id and sale=1 order by t1.ID desc";
		ResultSet rs = conn.executeQuery(sql);
		try {
			while (rs.next()) {
				form = new Goods();
				form.setID(Integer.valueOf(rs.getString(1)));
				form.setGoodsname(rs.getString("goodsname"));
				form.setTypename(rs.getString("typename"));
				form.setNowprice(Float.valueOf(rs.getFloat("nowprice")));
				form.setPicture(rs.getString("picture"));
				form.setIntroduce(rs.getString("introduce"));
				form.setPrice(Float.valueOf(rs.getFloat("price")));
				form.setIntime(rs.getTimestamp("intime"));
				form.setNewgoods(Integer.valueOf(rs.getString("newgoods")));
				form.setSale(Integer.valueOf(rs.getString("sale")));
				form.setHit(Integer.valueOf(rs.getString("hit")));
				list.add(form);
			}
		} catch (SQLException ex) {
		}
		conn.close();
		return list;
	}

	@Override
	public void save(Goods goods,Integer typeId) throws Exception {
		String sql = "insert into tb_goods(goodsname,typeID,introduce,price," + 
				"nowprice,picture,intime,newGoods,sale,hit) " + 
				"values('" + goods.getGoodsname() + "',"+typeId+",'"+goods.getIntroduce()+"',"+
				goods.getPrice()+","+goods.getNowprice()+",'"+goods.getPicture()+"','"+
				goods.getIntime()+"',"+goods.getNewgoods()+","+goods.getSale()+","+goods.getHit()+")";
		System.out.println("*********sql="+sql);
		conn.executeUpdate(sql);
		conn.close();
	}

}
