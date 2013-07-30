package com.webcrawler.crawlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

import com.webcrawler.dianp.ShopInfo;
import com.webcrawler.dianp.ShopReply;
import com.webcrawler.dianp.ShopUser;

public class DianPCrawler {
	public static final String ROOT_URL = "http://www.dianping.com";

	public static void main(String[] args) {
		start("http://www.dianping.com/shop/4527620?KID=142549");
	}

	public static void start(String url) {
		Document doc = getHtml(url);
		String shopId = getShopInfo(doc.getElementsByClass("shop-info-con").get(0)
				.html(), url);
		if(shopId == null)
			return;
		String replyUrl = ROOT_URL
				+ doc.getElementsByClass("comm-more-skip").get(0)
						.getElementsByTag("a").get(0).attr("href");
		getAllReply(replyUrl.split("#")[0], getHtml(replyUrl).html(), shopId);
	}

	public static void getNowReply(Document doc, String shopId) {
		Elements users = doc.getElementsByClass("pic");
		Elements contents = doc.getElementsByClass("J_brief-cont");
		Set<String> userSet = ShopUser.queryAllIds();
		for (int i = 0; i < users.size(); i++) {
			Element user = users.get(i).getElementsByClass("J_card").get(1);
			ShopUser su = new ShopUser(user.attr("user-id"), user.text(),
					ROOT_URL + user.attr("href"));
			contents.get(i).text();
			ShopReply sr = new ShopReply(user.attr("user-id"), shopId, contents
					.get(i).text());
			if (!userSet.contains(su.getId()))
				su.save();
			sr.save();
		}
	}

	public static void getAllReply(String url, String html, String shopId) {
		String s = "";
		Document doc = null;
		int i = 0;
		doc = Jsoup.parse(html);
		getNowReply(doc, shopId);
		s = getLink(doc);
		while (!"none".equals(s)) {
			doc = getHtml(url + s);
			getNowReply(doc, shopId);
			s = getLink(doc);
		}
	}

	public static String getLink(Document doc) {
		Elements es = doc.getElementsByClass("Pages");
		Elements eles = es.get(0).getAllElements();
		for (Element element : eles) {
			if ("ÏÂÒ»Ò³".equals(element.text())) {
				return element.attr("href");
			}
		}
		return "none";
	}

	public static String getShopInfo(String html, String url) {
		String id = url.split("=")[1];
		Document doc = Jsoup.parse(html);
		String title = getShopTitle(doc);
		Map<String, String> score = getShopScore(doc);
		Map<String, String> loc = getShopLocation(doc);
		Map<String, String> other = getShopOtherInfo(doc);
		ShopInfo is = new ShopInfo(id, title, score.get("kouwei"),
				score.get("huanjing"), score.get("fuwu"), loc.get("tel"),
				loc.get("local"), other.get("alias"), other.get("synopsis"),
				other.get("busTime"));
		if (!is.queryIsHas(is.getId())){
			is.save();
			return id;
		}
		return null;
	}

	public static Map<String, String> getShopOtherInfo(Document doc) {
		Elements eles = doc.getElementsByClass("desc-info").get(0)
				.getElementsByTag("li");
		String alias = splitString(eles.get(0).text());
		String synopsis = splitString(eles.get(1).text());
		String busTime = eles.get(2).getElementsByTag("span").get(0).text();
		Map<String, String> map = new HashMap<String, String>();
		map.put("alias", alias);
		map.put("synopsis", synopsis);
		map.put("busTime", busTime);
		return map;
	}

	public static Map<String, String> getShopLocation(Document doc) {
		Elements eles = doc.getElementsByClass("shop-location").get(0)
				.getElementsByTag("span");
		String local = eles.get(0).text() + eles.get(1).text();
		String tel = eles.get(2).text();
		Map<String, String> map = new HashMap<String, String>();
		map.put("local", local);
		map.put("tel", tel);
		return map;
	}

	public static Map<String, String> getShopScore(Document doc) {
		Elements eles = doc.getAllElements();
		Element e = null;
		for (Element element : eles) {
			if (element.className().equals("status-list taste-status")) {
				e = element;
				break;
			}
		}
		String kouwei = e.getElementsByTag("span").get(2).text();
		String huanjing = e.getElementsByTag("span").get(5).text();
		String fuwu = e.getElementsByTag("span").get(8).text();
		Map<String, String> map = new HashMap<String, String>();
		map.put("kouwei", kouwei);
		map.put("huanjing", huanjing);
		map.put("fuwu", fuwu);
		return map;
	}

	public static String getShopTitle(Document doc) {
		Elements eles = doc.getElementsByClass("shop-title");
		return eles.get(0).text();
	}

	public static Document getHtml(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpHost proxy = new HttpHost("127.0.0.1", 8087);
		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxy);
		httpGet.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:9.0.1) Gecko/20100101 Firefox/9.0.1");
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
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

	public static String splitString(String src) {
		String[] s = src.split(" ");
		return s[1];
	}
}
