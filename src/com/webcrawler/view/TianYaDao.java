package com.webcrawler.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcrawler.db.utils.DBHelper;
import com.webcrawler.dianp.ShopUser;
import com.webcrawler.tianya.ContentItem;
import com.webcrawler.tianya.PostInfo;
import com.webcrawler.tianya.UserInfo;

public class TianYaDao {
	public List<PostInfo> getAllPost() {
		List<Map> res = DBHelper.executeQuery(TianYaContext.ALL_POSTS,
				new Object[] {});
		List<PostInfo> postList = new ArrayList<PostInfo>();
		for (Map map : res) {
			PostInfo post = new PostInfo(map);
			postList.add(post);
		}
		return postList;
	}

	public List<ContentItem> getAllReplys(int postId) {
		List<Map> res = DBHelper.executeQuery(TianYaContext.ALL_Replys,
				new Object[] { postId });
		List<ContentItem> conList = new ArrayList<ContentItem>();
		for (Map map : res) {
			ContentItem con = new ContentItem(map);
			conList.add(con);
		}
		return conList;
	}

	public int getReplyNum(int postId) {
		return getAllReplys(postId).size();
	}
	public int PostNum() {
		return getAllPost().size();
	}
	
	public List<ContentItem> getReplysByPageNo(int postId, int pageNo,
			int everyPageNum) {
		List<ContentItem> conList = getAllReplys(postId);
		List<ContentItem> res = new ArrayList<ContentItem>();
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
	
	public List<PostInfo> getPostByPageNo(int pageNo,
			int everyPageNum) {
		List<PostInfo> conList = getAllPost();
		List<PostInfo> res = new ArrayList<PostInfo>();
		int max = pageNo * everyPageNum;
		int total = PostNum();
		if (max > total) {
			max = total;
		}
		for (int i = (pageNo - 1) * everyPageNum; i < max; i++) {
			res.add(conList.get(i));
		}
		return res;
	}
 
	public Map<String,UserInfo> getAllUsers() {
		List<Map> res = DBHelper.executeQuery(TianYaContext.ALL_USERS,
				new Object[] { });
		Map<String,UserInfo> conList = new HashMap<String,UserInfo>();
		for (Map map : res) {
			UserInfo user = new UserInfo(map);
			conList.put(user.getUid(),user);
		}
		return conList;
	}
	
	public static void main(String[] args) {
		TianYaDao dao = new TianYaDao();
		List<ContentItem> l = dao.getReplysByPageNo(1, 4, 3);
		for (ContentItem p : l) {
			System.out.println(p.getItemTime());
		}
	}
}
