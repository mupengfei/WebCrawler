package com.webcrawler.dianp;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class ShopInfo {
	private String id;
	private String shopTitle;
	private String shopKouwei;
	private String shopHuanjing;
	private String shopFuwu;
	private String shopTel;
	private String shopLocal;
	private String shopAlias;
	private String shopSynopsis;
	private String shopBusTime;
	public boolean queryIsHas(String id) {
		String sql = "select * from dp_shopinfo where id = ?";
		if(DBHelper.executeQuery(sql, new Object[] { id}).size() == 0){
			return false;
		}
		return true;
	}
	public boolean save() {
		String sql = "insert into dp_shopinfo values (?,?,?,?,?,?,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.id, this.shopTitle,
				this.shopKouwei, this.shopHuanjing, this.shopFuwu, this.shopTel,
				this.shopLocal, this.shopAlias, this.shopSynopsis, this.shopBusTime});
	}
	public ShopInfo(Map map) {
		super();
		this.id = (String)map.get("id");
		this.shopTitle = (String)map.get("shop_title");
		this.shopKouwei = (String)map.get("shop_kouwei");
		this.shopHuanjing = (String)map.get("shop_huanjing");
		this.shopFuwu = (String)map.get("shop_fuwu");
		this.shopTel = (String)map.get("shop_tel");
		this.shopLocal = (String)map.get("shop_local");
		this.shopAlias = (String)map.get("shop_alias");
		this.shopSynopsis = (String)map.get("shop_synopsis");
		this.shopBusTime = (String)map.get("shop_bustime");
	}
	public ShopInfo(String id, String shopTitle, String shopKouwei,
			String shopHuanjing, String shopFuwu, String shopTel,
			String shopLocal, String shopAlias, String shopSynopsis,
			String shopBusTime) {
		super();
		this.id = id;
		this.shopTitle = shopTitle;
		this.shopKouwei = shopKouwei;
		this.shopHuanjing = shopHuanjing;
		this.shopFuwu = shopFuwu;
		this.shopTel = shopTel;
		this.shopLocal = shopLocal;
		this.shopAlias = shopAlias;
		this.shopSynopsis = shopSynopsis;
		this.shopBusTime = shopBusTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	public String getShopKouwei() {
		return shopKouwei;
	}
	public void setShopKouwei(String shopKouwei) {
		this.shopKouwei = shopKouwei;
	}
	public String getShopHuanjing() {
		return shopHuanjing;
	}
	public void setShopHuanjing(String shopHuanjing) {
		this.shopHuanjing = shopHuanjing;
	}
	public String getShopFuwu() {
		return shopFuwu;
	}
	public void setShopFuwu(String shopFuwu) {
		this.shopFuwu = shopFuwu;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getShopLocal() {
		return shopLocal;
	}
	public void setShopLocal(String shopLocal) {
		this.shopLocal = shopLocal;
	}
	public String getShopAlias() {
		return shopAlias;
	}
	public void setShopAlias(String shopAlias) {
		this.shopAlias = shopAlias;
	}
	public String getShopSynopsis() {
		return shopSynopsis;
	}
	public void setShopSynopsis(String shopSynopsis) {
		this.shopSynopsis = shopSynopsis;
	}
	public String getShopBusTime() {
		return shopBusTime;
	}
	public void setShopBusTime(String shopBusTime) {
		this.shopBusTime = shopBusTime;
	}
}
