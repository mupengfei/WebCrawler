package com.webcrawler.view;

public class TaoBaoContext {
	public static final String ALL_GOODS = "select * from tb_goods";
	
	public static final String ALL_Replys = "select * from tb_reply where goods_id = ? ";

	public static final String ALL_SHOPS = "select * from tb_shop";
}
