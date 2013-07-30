package com.webcrawler.sina;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.webcrawler.db.utils.DBHelper;

public class WbUser {
	private String userId;
	private String userName;
	private String url;
	public boolean save() {
		String sql = "insert into wb_user values (?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.userId, this.userName, this.url });
	}
	public Set<String> queryAllUids() {
		Set<String> set = new HashSet<String>();
		String sql = "select user_id from wb_user ";
		List<Map> list = DBHelper.executeQuery(sql, new Object[] {});
		for (Map map : list) {
			set.add((String) map.get("user_id"));
		}
		return set;
	}
	public WbUser(Map map) {
		super();
		this.userId =  (String)map.get("user_id");
		this.userName = (String)map.get("user_name");
		this.url = (String)map.get("url");
	}
	public WbUser(String userId, String userName, String url) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.url = url;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
