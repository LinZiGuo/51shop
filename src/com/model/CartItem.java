package com.model;
/**
 * 购物项
 * @author Administrator
 *
 */
public class CartItem {

	private Goods goods;
	private Float subtotal=0.0f;
	private Integer count=0;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Float getSubtotal() {
		if (goods.getNowprice() != null) {
			return goods.getNowprice()*count;
		}
		return goods.getPrice()*count;
	}
//	public void setSubtotal(Float subtotal) {
//		this.subtotal = subtotal;
//	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public CartItem(Goods goods, Integer count) {
		super();
		this.goods = goods;
		this.count = count;
	}
	
	
}
