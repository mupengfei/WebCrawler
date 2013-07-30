package com.webcrawler.tianya;

import java.util.Date;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class ContentItem {
	private int id;
	private int postId;
	private String uid;
	private Date itemTime;
	private String content;

	public ContentItem(String uid, Date itemTime, String content) {
		super();
		this.uid = uid;
		this.itemTime = itemTime;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContentItem(Map map) {
		super();
		this.id = (Integer)map.get("id");
		this.postId = (Integer)map.get("post_id");
		this.uid =  (String)map.get("uid");
		this.itemTime = (Date)map.get("item_time");
		this.content = (String)map.get("content");
	}

	public ContentItem(int postId, String uid, Date itemTime,
			String content) {
		super();
		this.postId = postId;
		this.uid = uid;
		this.itemTime = itemTime;
		this.content = content;
	}

	public boolean save() {
		String sql = "insert into content_item values (null,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.postId, this.uid,
				this.itemTime, this.content });
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getItemTime() {
		return itemTime;
	}

	public void setItemTime(Date itemTime) {
		this.itemTime = itemTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
}
