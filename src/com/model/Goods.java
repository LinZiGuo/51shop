package com.model;

import java.text.DateFormat;
import java.util.Date;

public class Goods {

	private Integer ID;
	private String goodsname;
	private String typename;
	private String introduce;
	private Float price;
	private Float nowprice;
	private String picture;
	private String intime;
	private Integer newgoods;
	private Integer sale;
	private Integer hit;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getNowprice() {
		return nowprice;
	}
	public void setNowprice(Float nowprice) {
		this.nowprice = nowprice;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getIntime() {
		return intime;
	}
	public void setIntime(Date intime) {
		DateFormat dtf = DateFormat.getDateTimeInstance();
		this.intime = dtf.format(intime);
	}
	public Integer getNewgoods() {
		return newgoods;
	}
	public void setNewgoods(Integer newgoods) {
		this.newgoods = newgoods;
	}
	public Integer getSale() {
		return sale;
	}
	public void setSale(Integer sale) {
		this.sale = sale;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	
}
