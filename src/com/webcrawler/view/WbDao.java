package com.webcrawler.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;
import com.webcrawler.sina.WbContent;
import com.webcrawler.sina.WbUser;
import com.webcrawler.tianya.ContentItem;

public class WbDao { 

	public List<WbContent> getAllContents() {
		List<Map> res = DBHelper.executeQuery(WbContext.ALL_CONTENTS,
				new Object[] {  });
		List<WbContent> conList = new ArrayList<WbContent>();
		for (Map map : res) {
			WbContent con = new WbContent(map);
			conList.add(con);
		}
		return conList;
	}

	public int getContentsNum() {
		return getAllContents().size();
	}

	public List<WbContent> getContentsByPageNo(int pageNo,
			int everyPageNum) {
		List<WbContent> conList = getAllContents();
		List<WbContent> res = new ArrayList<WbContent>();
		int max = pageNo * everyPageNum;
		int total = getContentsNum();
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
 
	public Map<String,WbUser> getAllUsers() {
		List<Map> res = DBHelper.executeQuery(WbContext.ALL_USERS,
				new Object[] { });
		Map<String,WbUser> conList = new HashMap<String,WbUser>();
		for (Map map : res) {
			WbUser user = new WbUser(map);
			conList.put(user.getUserId(),user);
		}
		return conList;
	}
	
	public static void main(String[] args) {
		WbDao dao = new WbDao();
		Map<String,WbUser> l = dao.getAllUsers();
//		for (WbUser p : l) {
			System.out.println(l.get("1014268115").getUserName());
//		}
	}
}
