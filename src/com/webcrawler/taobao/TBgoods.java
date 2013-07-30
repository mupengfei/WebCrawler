package com.webcrawler.taobao;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class TBgoods {
	private String id;
	private String shopId;
	private String goodName;
	private String description;
	private String keywords;
	public boolean save() {
		String sql = "insert into tb_goods values (?,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.id, this.shopId,
				this.goodName, this.description, this.keywords});
	}
	public TBgoods(Map map) {
		super();
		this.id =  (String)map.get("id");
		this.shopId = (String)map.get("shop_id");
		this.goodName = (String)map.get("good_name");
		this.description = (String)map.get("description");
		this.keywords = (String)map.get("keywords");
	}
	public TBgoods(String id, String shopId, String goodName,
			String description, String keywords) {
		super();
		this.id = id;
		this.shopId = shopId;
		this.goodName = goodName;
		this.description = description;
		this.keywords = keywords;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
