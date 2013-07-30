package com.webcrawler.view;

public class DianpContext {
	public static final String ALL_SHOPS = "select * from dp_shopinfo";
	
	public static final String ALL_Replys = "select * from dp_shopreply where shop_id = ?";
	
	public static final String ALL_USERS = "select * from dp_shopuser";
}
