package com.webcrawler.tianya;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 

import com.webcrawler.db.utils.DBHelper;

public class UserInfo {
	private String uid;
	private String uname;
	private String url;

	public UserInfo(Map map) {
		super();
		this.uid = (String)map.get("uid");
		this.uname = (String)map.get("uname");
		this.url = (String)map.get("url");
	}
	
	public UserInfo(String uid, String uname, String url) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.url = url;
	}

	public boolean save() {
		String sql = "insert into user_info values (?,?,?)";
		return DBHelper.execute(sql, new Object[] { this.uid, this.uname, this.url });
	}

	public Set<String> queryAllUids() {
		Set<String> set = new HashSet<String>();
		String sql = "select uid from user_info ";
		List<Map> list = DBHelper.executeQuery(sql, new Object[] {});
		for (Map map : list) {
			set.add((String) map.get("uid"));
		}
		return set;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
