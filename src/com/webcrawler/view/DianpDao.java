package com.webcrawler.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;
import com.webcrawler.dianp.ShopInfo;
import com.webcrawler.dianp.ShopReply;
import com.webcrawler.dianp.ShopUser;

public class DianpDao {
	public List<ShopInfo> getAllShop() {
		List<Map> res = DBHelper.executeQuery(DianpContext.ALL_SHOPS,
				new Object[] {});
		List<ShopInfo> shopList = new ArrayList<ShopInfo>();
		for (Map map : res) {
			ShopInfo post = new ShopInfo(map);
			shopList.add(post);
		}
		return shopList;
	}

	public List<ShopReply> getAllReplys(int shopId) {
		List<Map> res = DBHelper.executeQuery(DianpContext.ALL_Replys,
				new Object[] { shopId });
		List<ShopReply> conList = new ArrayList<ShopReply>();
		for (Map map : res) {
			ShopReply con = new ShopReply(map);
			conList.add(con);
		}
		return conList;
	}
	
	public int ShopNum() {
		return getAllShop().size();
	}

	public int getReplyNum(int shopId) {
		return getAllReplys(shopId).size();
	}

	public List<ShopInfo> getShopByPageNo(int pageNo,
			int everyPageNum) {
		List<ShopInfo> conList = getAllShop();
		List<ShopInfo> res = new ArrayList<ShopInfo>();
		int max = pageNo * everyPageNum;
		int total = ShopNum();
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
	
	public List<ShopReply> getReplysByPageNo(int postId, int pageNo,
			int everyPageNum) {
		List<ShopReply> conList = getAllReplys(postId);
		List<ShopReply> res = new ArrayList<ShopReply>();
		int max = pageNo * everyPageNum;
		int total = getReplyNum(postId);
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
	
	public Map<String,ShopUser> getAllUsers() {
		List<Map> res = DBHelper.executeQuery(DianpContext.ALL_USERS,
				new Object[] { });
		Map<String,ShopUser> conList = new HashMap<String,ShopUser>();
		for (Map map : res) {
			ShopUser user = new ShopUser(map);
			conList.put(user.getId(),user);
		}
		return conList;
	}
	
	public static void main(String[] args) {
		DianpDao dao = new DianpDao();
		List<ShopReply> l = dao.getReplysByPageNo(142549, 4, 3);
		for (ShopReply p : l) {
			System.out.println(p.getContent());
		}
	}
}
