import java.io.BufferedReader; 
import java.io.FileInputStream; 
import java.io.InputStreamReader; 
import java.net.URLDecoder; 
import java.security.KeyStore; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Iterator; 
import java.util.List; 
import java.util.Map; 
 
import javax.net.ssl.KeyManagerFactory; 
 
import javax.net.ssl.SSLContext; 
import javax.net.ssl.TrustManager; 
import javax.net.ssl.TrustManagerFactory; 
 
import org.apache.http.HttpResponse; 
import org.apache.http.NameValuePair; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.entity.UrlEncodedFormEntity; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.conn.scheme.Scheme; 
import org.apache.http.conn.ssl.SSLSocketFactory; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.message.BasicNameValuePair; 

public class Connnnnn {

public String connect(String url1, String xml) throws Exception { 
        BufferedReader in = null; 
        String keyStore = "c:\\a.pfx"; //֤���·����pfx��ʽ
        String trustStore = "c:\\b.jks";//��Կ���ļ���jks��ʽ 
        String keyPass ="111111"; //pfx�ļ�������
        String trustPass = "22222"; //jks�ļ�������
 
        SSLContext sslContext = null; 
        try { 
 
            KeyStore ks = KeyStore.getInstance("pkcs12"); 
            // ����pfx�ļ�            
             ks.load(new FileInputStream(keyStore), keyPass.toCharArray()); 
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509"); 
            kmf.init(ks, keyPass.toCharArray()); 
 
            KeyStore ts = KeyStore.getInstance("jks"); 
          //����jks�ļ�            
             ts.load(new FileInputStream(trustStore), trustPass.toCharArray()); 
            TrustManager[] tm; 
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509"); 
            tmf.init(ts); 
            tm = tmf.getTrustManagers(); 
 
            sslContext = SSLContext.getInstance("SSL"); 
//��ʼ��
            sslContext.init(kmf.getKeyManagers(), tm, null); 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
 
        String result = null; 
        try { 
 
            HttpClient httpclient = new DefaultHttpClient(); 
             //�������ص�
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext); 
            Scheme sch = new Scheme("https", 800, socketFactory); 
            httpclient.getConnectionManager().getSchemeRegistry().register(sch); 
            HttpPost httpPost = null; 
 
            httpPost = new HttpPost("www.baidu.com"); 
 
            // ������/ֵ���б� 
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(); 
            parameters.add(new BasicNameValuePair("Name", "zhangsan")); 
            parameters.add(new BasicNameValuePair("passWord", "123456")); 
           
            // ����UrlEncodedFormEntity���� 
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(parameters); 
//            LOGGER.info("formEntity={}", formEntiry); 
            httpPost.setEntity(formEntiry); 
            HttpResponse httpResponse = httpclient.execute(httpPost); 
            in = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent())); 
            StringBuffer sb = new StringBuffer(""); 
            String line = ""; 
            String NL = System.getProperty("line.separator"); 
            while ((line = in.readLine()) != null) { 
                sb.append(line + NL); 
            } 
            in.close(); 
            result = sb.toString(); 
 
            result = URLDecoder.decode(result.toString(), "GBK"); 
//            LOGGER.info("result={}", result); 
            return result; 
        } finally { 
            if (in != null) { 
                try { 
                    in.close(); 
                } catch (Exception e) { 
                    e.printStackTrace(); 
                } 
            } 
        } 
 
    } 
}
