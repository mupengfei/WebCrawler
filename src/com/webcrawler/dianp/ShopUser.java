package com.webcrawler.dianp;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.webcrawler.db.utils.DBHelper;

public class ShopUser {
	private String id;
	private String username;
	private String url;
	public static Set<String> queryAllIds() {
		Set<String> set = new HashSet<String>();
		String sql = "select id from dp_shopuser ";
		List<Map> list = DBHelper.executeQuery(sql, new Object[] {});
		for (Map map : list) {
			set.add((String) map.get("id"));
		}
		return set;
	}
	public boolean save() {
		String sql = "insert into dp_shopuser values (?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.id, this.username,
				this.url});
	}
	public ShopUser(Map map) {
		super();
		this.id = (String)map.get("id");
		this.username = (String)map.get("username");
		this.url = (String)map.get("url");
	}
	public ShopUser(String id, String username, String url) {
		super();
		this.id = id;
		this.username = username;
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
