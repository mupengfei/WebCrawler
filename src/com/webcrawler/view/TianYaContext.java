package com.webcrawler.view;

public class TianYaContext {
	public static final String ALL_POSTS = "select * from post_info";
	
	public static final String ALL_Replys = "select * from content_item where post_id = ? order by item_time asc ";

	public static final String ALL_USERS = "select * from user_info";
}
