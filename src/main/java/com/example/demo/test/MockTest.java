/**
 * 
 */
package com.example.demo.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;



/**
 * @author liaofen
 *
 */
public class MockTest {
	Logger log = Logger.getLogger(MockTest.class);

	private static String url="https://58.250.203.150:8443/EpccService/NotifyServlet";
//	private static String url="https://58.250.203.150:38080/EpccService/NotifyServlet";

	/**
	 * 
	 * @Description: 模拟账户侧发起305商户订单查询申请报文
	 * @author liaofen
	 * @Title :test305
	 * @Time: 2020-4-16 下午5:01:24
	 */
	//@Test
	public void test305() {
		testNotify(""+305);
	}

	public static void main(String[] args) {
		new MockTest().test208();
	}
	/**
	 * 
	 * @Description: 模拟账户侧发起261商户订单申请报文
	 * @author liaofen
	 * @Title :test261
	 * @Time: 2020-4-16 下午5:01:52
	 */
	//@Test
	public void test261() {
		testNotify(""+261);
	}
/**
	 * 
	 * @Description: 模拟网联发起快捷支付终态通知
	 * @author liaofen
	 * @Title :test207
	 * @Time: 2020-5-13 下午4:14:27
	 */
	//@Test
	public void test207() {
		testNotify(""+207);
	}
	/**
	 * 
	 * @Description: 模拟网联发起退款终态通知
	 * @author liaofen
	 * @Title :test208
	 * @Time: 2020-5-13 下午4:20:16
	 */
	//@Test
	public void test208() {
		testNotify(""+208);
	}
	/**
	 * 模拟网联发起支付终态通知
	 * @Description: TODO
	 * @author liaofen
	 * @Title :test244
	 * @Time: 2020-5-13 下午4:20:51
	 */
	//@Test
	public void test244() {
		testNotify(""+244);
	}

	private void testNotify(String msgTpId){
		String path="E:\\zlj\\qb\\doc\\互联互通测试20200512\\互联互通测试20200513\\test"+msgTpId+".xml";
		post(url, path);
	}
	
	private void post(String url,String path){
		post(url, path, true);
	}
	private void post(String url,String path,boolean isEpcc){
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try{
//			HttpClient httpClient = new DefaultHttpClient();
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			TrustManager tm = new TrustAllStrategy();
			sslContext.init(null, new TrustManager[] { tm }, null);

			SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext);
			sslSocketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme protocolScheme = new Scheme("HTTPS", 443, sslSocketFactory);
			httpClient.getConnectionManager().getSchemeRegistry()
					.register(protocolScheme);
//			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("e://test//test21301.xml")));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String line;
			StringBuilder data = new StringBuilder("");
			while ((line = reader.readLine()) != null){
				data.append(line).append("\n");
				System.out.println(line);
			}
			StringEntity entity = new StringEntity(data.toString(), "UTF-8");
			entity.setContentEncoding("UTF-8");
			post.setEntity(entity);
			post.setHeader("ReservedField", 1367 + "");
			if(isEpcc){
				post.setHeader("MsgTp", "epcc.261.001.01");// 
			}
			HttpResponse response = httpClient.execute(post);
			System.out.println(response.toString());
			String content = EntityUtils.toString(response.getEntity());
			log.info("response:" + content);
		}catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
