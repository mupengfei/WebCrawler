package com.webcrawler.crawlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcrawler.tianya.ContentItem;
import com.webcrawler.tianya.PostInfo;
import com.webcrawler.tianya.UserInfo;

public class TianYaCrawler {
	private static final String BASE_URL = "http://bbs.tianya.cn";

	public static Document getHtml(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
//		HttpHost proxy = new HttpHost("10.130.20.89", 8080);
//		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
//				proxy);
		HttpResponse response1 = null;
		HttpEntity entity = null;
		try {
			response1 = httpclient.execute(httpGet);
			System.out.println(response1.getStatusLine());
			entity = response1.getEntity();
			System.out.println(entity.getContentType());
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "utf-8"));
			StringBuffer sb = new StringBuffer();
			String s = "";
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			return Jsoup.parse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				EntityUtils.consume(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			httpGet.releaseConnection();
		}
	}

	public static PostInfo getPostInfo(Document doc) {
		Elements title = doc.getElementsByTag("title");
		Element postHead = doc.getElementById("post_head");
		Elements postInfo = postHead.getElementsByClass("atl-info");
		Elements info = postInfo.get(0).getElementsByTag("span");
		String author = info.get(0).getElementsByTag("a").get(0).attr("uid");
		Elements ss = doc.getElementsByClass("atl-content").get(0)
				.getAllElements();
		String content = "";
		for (Element element : ss) {
			if (element.className().equals("bbs-content clearfix")) {
				content = element.html();
				break;
			}
		}
		return new PostInfo(title.get(0).text(), author, info.get(1).text(),
				info.get(2).text(), info.get(3).text(), content);
	}

	public static ContentItem getContentItem(Element element) {
		String time = element.attr("js_resTime");
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Elements els = element.getElementsByClass("atl-info");
		Element userInfo = els.get(0).getElementsByTag("a").get(0);
		String uid = userInfo.attr("uid");
		Element content = element.getElementsByClass("bbs-content").get(0);
		return new ContentItem(uid, date, content.html());
	}

	public static UserInfo getUserInfo(Element element) {
		Elements els = element.getElementsByClass("atl-info");
		Element userInfo = els.get(0).getElementsByTag("a").get(0);
		String uid = userInfo.attr("uid");
		String uname = userInfo.attr("uname");
		String url = userInfo.attr("href");
		return new UserInfo(uid, uname, url);
	}

	public static String getLink(Document doc) {
		Elements eles = doc.getElementsByClass("atl-pages").get(0)
				.getElementsByTag("a");
		for (Element element : eles) {
			if ("обрЁ".equals(element.text())) {
				return element.attr("href");
			}
		}
		return "none";
	}

	public static void goWork(Document doc, int pid) {
		int i = 0;
		for (Element element : doc.getElementsByClass("atl-item")) {
			if (i++ == 0) {
				continue;
			}
			UserInfo ui = getUserInfo(element);
			if(!ui.queryAllUids().contains(ui.getUid())){
				ui.save();
			}
			ContentItem ci = getContentItem(element);
			ci.setPostId(pid);
			ci.save();
		}
	}

	public static void start(String url) {
		Document doc = getHtml(url);
		PostInfo pi = getPostInfo(doc);
		pi.save();
		String link = getLink(doc);
		goWork(doc,pi.getId());
		while (!"none".equals(link)) {
			url = BASE_URL + link;
			doc = getHtml(url);
			link = getLink(doc);
			goWork(doc,pi.getId());
		}
	}

	public static void main(String[] args) {
		start("http://bbs.tianya.cn/post-no05-266685-1.shtml");
	}
}
