package com.webcrawler.crawlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.webcrawler.taobao.TBgoods;
import com.webcrawler.taobao.TBreply;
import com.webcrawler.taobao.TBshop;

public class TaobaoCrawler { 
		//http://rate.tmall.com/list_detail_rate.htm?itemId=17379734317&spuId=215146917&sellerId=734635960&order=1&currentPage=1&append=0&content=1&tagId=&posi=&_ksTS=1371131905910_2541&callback=jsonp2542
	
		public static void main(String[] args) throws Exception { 
//			System.out.println(splitUrl("http://detail.tmall.com/item.htm?spm=a2106.m874.1000384.13.ypxKkj&id=18881399779&source=dou&scm=1029.newlist-0.bts1.51108009&ppath=&sku=#J_Reviews"));
			start("http://detail.tmall.com/item.htm?spm=a2106.m874.1000384.14.hAvoTJ&id=24406132047&source=dou&_u=ditkq9b36a6&scm=1029.newlist-0.bts1.51108009&ppath=&sku=");
		}
		
		public static void start(String url){
			Document doc = Jsoup.parse(getHtml(url));
			getInfo(doc, splitUrl(url).get("id"));
			getReply(doc, splitUrl(url).get("id"));
		}
		
		public static void getReply(Document doc, String goodsId){
			int i = doc.html().indexOf("spuId");
			String spuId = doc.html().substring(i + 8,
					i + 17);
			i = doc.html().indexOf("userId");
			String userId = doc.html().substring(i + 9,
					i + 19);
			String replys = getHtml("http://rate.tmall.com/list_detail_rate.htm?itemId=" + goodsId + "&spuId=" + spuId + "&sellerId=" + userId + "&order=1&currentPage=1&append=0&content=1&tagId=&posi=&_ksTS=1371131905910_2541&callback=jsonp2542");
			int left = replys.indexOf("(");
			int right = replys.indexOf(")");
			replys = replys.substring(left + 1,
					replys.length() - 1);
			Object obj = JSONValue.parse(replys);
			JSONObject obje = (JSONObject) obj;
			JSONArray array = (JSONArray)((JSONObject)obje.get("rateDetail")).get("rateList");
			for (Object o : array) {
				new TBreply(goodsId, (String)((JSONObject)o).get("displayUserNick"), (String)((JSONObject)o).get("auctionSku"), (String)((JSONObject)o).get("rateContent")).save();
			}
		}
		
		public static TBshop getShopInfo(Document doc){
			Element shopInfo = doc.getElementById("shop-info-new");
			Elements eles = doc.getElementsByClass("shop-rate");
			Elements scros = null;
			if(eles.size() == 1)
				scros = eles.get(0).getElementsByClass("count");
			else
				scros = eles.get(1).getElementsByClass("count");
			String miaoshu = scros.get(0).text();
			String fuwu = scros.get(1).text();
			String fahuo = scros.get(2).text();
			String shopName = shopInfo.getElementsByClass("extend").get(0).getElementsByTag("a").get(0).text();
			Elements as = shopInfo.getElementsByClass("other").get(0).getElementsByTag("a");
			String shopUrl = as.get(0).attr("href");
			String shopId = splitUrl(as.get(1).attr("href")).get("itemid");
			return new TBshop(shopId, shopName, shopUrl,
					miaoshu, fuwu, fahuo);
		}
		
		public static void getInfo(Document doc, String goodsId){
			TBshop shop = getShopInfo(doc);
			shop.save();
			Elements metas = doc.getElementsByTag("meta");
			String description = "";
			String keywords = "";
			String goodName = doc.getElementsByTag("title").get(0).text();
			for (Element meta : metas) {
				String meName =  meta.attr("name");
				if("description".equals(meName)){
					description = meta.attr("content");
				}else if("keywords".equals(meName)){
					keywords = meta.attr("content");
				}
			}
			TBgoods go = new  TBgoods(goodsId, shop.getShopId(), goodName,
					description, keywords);
			go.save();
		}
		
		public static String getHtml(String url) {
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
						is, "GBK"));
				StringBuffer sb = new StringBuffer();
				String s = "";
				while ((s = reader.readLine()) != null) {
					sb.append(s);
				}
				return sb.toString();
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
		
		public static Map<String, String> splitUrl(String url){
			Map<String, String> params = new HashMap<String, String>();
			String regEx="\\?";   
			Pattern p =Pattern.compile(regEx);  
			Matcher m = p.matcher(url);  
			String[] sss = p.split(url);
			String[] pas = sss[1].split("&");
			for (int i = 0; i < pas.length; i++) {
				String[] res = pas[i].split("\\=");
				if(res.length > 1)
					params.put(pas[i].split("\\=")[0], pas[i].split("\\=")[1]);
				else
					params.put(pas[i].split("\\=")[0], "");
			}
			return params;
		}
}
