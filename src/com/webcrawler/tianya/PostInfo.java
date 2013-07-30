package com.webcrawler.tianya;

import java.util.List;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;

public class PostInfo {
	private int id;
	private String title;
	private String author;
	private String time;
	private String djSum;
	private String hfSum;
	private String content;

	public PostInfo(String title, String author, String time, String djSum,
			String hfSum, String content) {
		super();
		this.title = title;
		this.author = author;
		this.time = time;
		this.djSum = djSum;
		this.hfSum = hfSum;
		this.content = content;
	}
	
	public PostInfo(Map map) {
		super();
		this.id = (Integer)map.get("id");
		this.title = (String)map.get("title");
		this.author = (String)map.get("author");
		this.time = (String)map.get("time");
		this.djSum = (String)map.get("dj_sum");
		this.hfSum = (String)map.get("hf_sum");
		this.content = (String)map.get("content");
	}


	public boolean save() {
		int pid = queryMaxId() + 1;
		this.id = pid;
		String sql = "insert into post_info values (?,?,?,?,?,?,?)";
		return DBHelper.execute(sql, new Object[] { pid, this.title, this.author,
				this.time, this.djSum, this.hfSum, this.content });
	}

	public int queryMaxId() {
		String sql = "select count(*) as count from post_info ";
		List<Map> list = DBHelper.executeQuery(sql, new Object[] {});
		return ((Long) list.get(0).get("count")).intValue();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDjSum() {
		return djSum;
	}

	public void setDjSum(String djSum) {
		this.djSum = djSum;
	}

	public String getHfSum() {
		return hfSum;
	}

	public void setHfSum(String hfSum) {
		this.hfSum = hfSum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
