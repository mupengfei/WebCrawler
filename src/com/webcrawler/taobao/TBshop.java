package com.webcrawler.taobao;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class TBshop {
	private String shopId;
	private String shopName;
	private String shopUrl;
	private String miaoshu;
	private String fuwu;
	private String fahuo;
	
	public TBshop(Map map) {
		super();
		this.shopId = (String)map.get("shop_id");
		this.shopName = (String)map.get("shop_name");
		this.shopUrl = (String)map.get("shop_url");
		this.miaoshu = (String)map.get("miaoshu");
		this.fuwu = (String)map.get("fuwu");
		this.fahuo = (String)map.get("fahuo");
	}

	
	public boolean save() {
		String sql = "insert into tb_shop values (?,?,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.shopId, this.shopName,
				this.shopUrl, this.miaoshu, this.fuwu, this.fahuo});
	}
	public TBshop(String shopId, String shopName, String shopUrl,
			String miaoshu, String fuwu, String fahuo) {
		super();
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopUrl = shopUrl;
		this.miaoshu = miaoshu;
		this.fuwu = fuwu;
		this.fahuo = fahuo;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getMiaoshu() {
		return miaoshu;
	}
	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}
	public String getFuwu() {
		return fuwu;
	}
	public void setFuwu(String fuwu) {
		this.fuwu = fuwu;
	}
	public String getFahuo() {
		return fahuo;
	}
	public void setFahuo(String fahuo) {
		this.fahuo = fahuo;
	}
	
}
