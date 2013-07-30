package com.webcrawler.dianp;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class ShopReply {
	private int id;
	private String userId;
	private String shopId;
	private String content;
	public boolean save() {
		String sql = "insert into dp_shopreply values (?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.id, this.userId,
				this.shopId,this.content});
	}
	public ShopReply(Map map) {
		super();
		this.id = (Integer)map.get("id");
		this.userId = (String)map.get("user_id");
		this.shopId = (String)map.get("shop_id");
		this.content = (String)map.get("content");
	}
	public ShopReply(String userId, String shopId, String content) {
		super();
		this.userId = userId;
		this.shopId = shopId;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
