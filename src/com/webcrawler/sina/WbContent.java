package com.webcrawler.sina;

import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class WbContent {
	private int id;
	private String userId;
	private String content;
	public boolean save() {
		String sql = "insert into wb_content values (?,?,?)";
		return DBHelper.execute(sql, new Object[] { null, this.userId, this.content });
	}
	public WbContent(Map map) {
		super();
		this.id = (Integer)map.get("id");
		this.userId = (String)map.get("user_id");
		this.content = (String)map.get("content");
	}
	public WbContent(String userId, String content) {
		super();
		this.userId = userId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
