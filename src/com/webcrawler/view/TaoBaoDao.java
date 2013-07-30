package com.webcrawler.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;
import com.webcrawler.taobao.TBgoods;
import com.webcrawler.taobao.TBreply;
import com.webcrawler.taobao.TBshop;

public class TaoBaoDao {
	public List<TBgoods> getAllGoods() {
		List<Map> res = DBHelper.executeQuery(TaoBaoContext.ALL_GOODS,
				new Object[] {});
		List<TBgoods> goodList = new ArrayList<TBgoods>();
		for (Map map : res) {
			TBgoods good = new TBgoods(map);
			goodList.add(good);
		}
		return goodList;
	}

	public List<TBreply> getAllReplys(String goodsId) {
		List<Map> res = DBHelper.executeQuery(TaoBaoContext.ALL_Replys,
				new Object[] { goodsId });
		List<TBreply> replyList = new ArrayList<TBreply>();
		for (Map map : res) {
			TBreply con = new TBreply(map);
			replyList.add(con);
		}
		return replyList;
	}

	public int getReplyNum(String goodsId) {
		return getAllReplys(goodsId).size();
	}
	public int GoodsNum() {
		return getAllGoods().size();
	}
	
	public List<TBreply> getReplysByPageNo(String goodsId, int pageNo,
			int everyPageNum) {
		List<TBreply> conList = getAllReplys(goodsId);
		List<TBreply> res = new ArrayList<TBreply>();
		int max = pageNo * everyPageNum;
		int total = getReplyNum(goodsId);
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
	
	public List<TBgoods> getGoodsByPageNo(int pageNo,
			int everyPageNum) {
		List<TBgoods> conList = getAllGoods();
		List<TBgoods> res = new ArrayList<TBgoods>();
		int max = pageNo * everyPageNum;
		int total = GoodsNum();
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
 
	public Map<String,TBshop> getAllShops() {
		List<Map> res = DBHelper.executeQuery(TaoBaoContext.ALL_SHOPS,
				new Object[] { });
		Map<String,TBshop> conList = new HashMap<String,TBshop>();
		for (Map map : res) {
			TBshop shop = new TBshop(map);
			conList.put(shop.getShopId(),shop);
		}
		return conList;
	}
	

}
