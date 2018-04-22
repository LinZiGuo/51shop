package com.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer, CartItem> itemMap = new HashMap<Integer, CartItem>();
	private Float total = 0.0F;
	
	/**
	 * 获取所有的购物项
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return itemMap.values();
	}
	
	public Map<Integer, CartItem> getItemMap() {
		return itemMap;
	}
	public void setItemMap(Map<Integer, CartItem> itemMap) {
		this.itemMap = itemMap;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	
	/**
	 * 加入到购物车
	 * @param item
	 */
	public void add2Cart(CartItem item) {
		//获取商品的ID
		Integer id = item.getGoods().getID();
		//判断购物车是否有
		if (itemMap.containsKey(id)) {
			//有 修改数量 = 原来数量+新加数量
			//原有的购物项
			CartItem oItem = itemMap.get(id);
			
			oItem.setCount(oItem.getCount()+item.getCount());
		} else {
			//无
			itemMap.put(id, item);
		}
		//修改总金额
		total += item.getSubtotal();
	}
	
	/**
	 * 从购物车移除一个购物项
	 * @param item
	 */
	public void removeFromCart(String id) {
		//1.从购物车（map）移除购物项
		CartItem item = itemMap.remove(Integer.valueOf(id));
		
		//2.修改总金额
		total -= item.getSubtotal();
	}
	
	/**
	 * 清空购物车
	 * @param item
	 */
	public void ClearCart() {
		//1.清空map
		itemMap.clear();
		
		//2.修改总金额=0
		total = 0.0f;
	}
}
