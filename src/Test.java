import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class Test {
	public static void main(String[] args) throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(
				"http://rate.tmall.com/list_detail_rate.htm?itemId=18262338831&spuId=218525420&sellerId=1579178521&order=1&currentPage=2&append=0&content=1&tagId=&posi=&_ksTS=1371048283937_1053&callback=jsonp1054");
//		 HttpHost proxy = new HttpHost("127.0.0.1", 8087);
//		 httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
//		 proxy);
		HttpResponse response1 = httpclient.execute(httpGet);

		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the
		// network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST either fully consume the response content or abort
		// request
		// execution by calling HttpGet#releaseConnection().

		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			System.out.println(entity.getContentType());
			InputStream is = entity.getContent();OutputStreamWriter out = null;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is,"GBK"));
				String s = "";
				File file = new File("d:/tianya.html");
				file.createNewFile();
				out = new OutputStreamWriter(new FileOutputStream(file));
				while((s = reader.readLine())!=null){
//					System.out.println(s);
					
					System.out.println(new String(s.getBytes(),"GBK"));
					out.write(s);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				is.close();
				out.close();
			} 
			EntityUtils.consume(entity);
		} finally {
			httpGet.releaseConnection();
		}

		// HttpPost httpPost = new HttpPost("http://targethost/login");
//		 List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		// nvps.add(new BasicNameValuePair("username", "vip"));
		// nvps.add(new BasicNameValuePair("password", "secret"));
		// httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		// HttpResponse response2 = httpclient.execute(httpPost);
		//
		// try {
		// System.out.println(response2.getStatusLine());
		// HttpEntity entity2 = response2.getEntity();
		// // do something useful with the response body
		// // and ensure it is fully consumed
		// EntityUtils.consume(entity2);
		// } finally {
		// httpPost.releaseConnection();
		// }
	}
}
