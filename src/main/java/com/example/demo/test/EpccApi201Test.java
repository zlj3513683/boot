package com.example.demo.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;


/**
 * 互联互通收单侧主扫
 * @author liaofen
 *
 */
@Slf4j
public class EpccApi201Test {
	
	
	private static String desKey="";
	private static String signKey="";
	private String SourceSysId="";
	/**回执报文中需要解密的字段*/
	static Map<String,String[]> dess;
	private static String url="https://58.250.203.150:8443/EpccService/epccApi";
	static {
		dess=new HashMap<>();
		dess.put("102", new String[]{"SgnAcctId","SgnAcctNm","InstgAcct"});
		dess.put("104", new String[]{"InstgAcct"});
		dess.put("113", new String[]{"SgnAcctId","SgnAcctNm","IDNo", "MobNo","LglRepNm", "LglRepIDNo","InstgAcct"});
		dess.put("213", new String[]{"PyerAcctNo","PyerAcctNm","PyeeBkNo","PyeeBkNm"});
		dess.put("218", new String[]{"PyerBkNo","PyerBkNm","PyeeAcctNo","PyeeAcctNm"});
		dess.put("243", new String[]{"PyerAcctId","PyerAcctNm"});
		dess.put("302", new String[]{"PyerAcctId","PyerAcctNm"});
		
	}
	/**
	 * 
	 * @Description: 交易类型为快捷支付 收款扫码
	 * @author 
	 * @Title :test201AppAsXML
	 * @Time: 
	 * @throws Exception
	 *             void
	 * @throws:
	 */
	//@Test
	public void test201AppAsXMLFor0116() throws Exception {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("SourceSysId", SourceSysId);
		map.put("MsgTpId", "201");
		map.put("ReservedField", "1375");// 预留位
		map.put("PyerAcctIssrId", "C1010611003603");// 付款方账户所属机构标识
		map.put("PyerAcctTp", "99");// 付款方账户类型 
		map.put("SgnNo", "736580922");// 条码受理终端识别的用户码
		
		map.put("PyeeAcctIssrId", "Z2005050000048");// 收款方在支付机构的账户编号
		map.put("PyeeAcctTp", "04");// 支付账户类型
		map.put("PyeeAcctId", "20025013");// 收款方在支付机构的账户编号
		map.put("PyeeNm", "lafiy");// 收款方在支付机构的账户名称
		map.put("PyeeTrxTrmTp", "01");
		map.put("PyeeTrxTrmNo", "202004141444");
		map.put("PyeeCtryNo", "CN");
		map.put("PyeeAreaNo", "350000");
		
		map.put("TrxAmt", "CNY20019.80");// 交易金额
		map.put("TrxCtgy", "0116");// 交易类型
		map.put("BizTp", "100007");// 业务种类
		map.put("AcctInTp", "02");// 业务种类
		
		
		map.put("OrdrId", ""+new Date().getTime());// 订单编号
		map.put("OrdrDtTm", dateToStr(new Date()) );
		map.put("OrdrDesc", "订单详情1");// 订单详情
		
		map.put("TrxDevcInf", "127.0.0.1|||||||");
			
		sendEpccApi(map);
	}

	
	/**
	 * 
	 * @author: wzf
	 * @Title 退款-收款扫码
	 * @Time: 2020-4-16下午4:13:37
	 * @Description:
	 */
	//@Test
	public void test205AppAsXmlFor0127() throws Exception{
		Map<String, String> map = new TreeMap<String, String>();
		map.put("SourceSysId", SourceSysId);
		map.put("MsgTpId", "205");
		map.put("ReservedField", "1375");// 预留位
		map.put("PyeeAcctIssrId", "Z2005050000048");// 收款方账户所属机构标识
		map.put("PyeeAcctTp", "00");//收款方账户类型
		map.put("SgnNo", "736580922");//协议号,收款扫码时的用户码
		//清算账户信息
//		map.put("ResfdAcctIssrId", "");
//		map.put("InstgAcctId", "");
		//map.put("InstgAcctNm", "");
		
		map.put("TrxCtgy", "0127");
		map.put("TrxAmt", "CNY20019.80");// 交易金额
		map.put("OriBizTp", "100007");
		map.put("OriTrxId", "2020031201184678962683611770003");// 原交易流水号
		map.put("OriDbtrBankId", "2020031214521509063497381770101");// 原付款行银行流水号
		map.put("OriTrxAmt", "CNY20019.80");// 原交易金额
//		map.put("BatchId", "B201809170012");// 批次号
		
		map.put("PyerAcctIssrId", "Z0000000000089");// 收单机构金融机构标识
		map.put("PyerAcctId", "20103003");// 退款方收单机构账户编号
		map.put("PyerAcctTp", "04");// 退款方账户类型
		map.put("OrdrId", ""+new Date().getTime());// 订单编号
		map.put("OrdrDtTm", dateToStr(new Date()) );
		sendEpccApi(map);
	}
	
	
	/**
	 * 
	 * @author: wzf
	 * @Title 退款 付款扫码
	 * @Time: 2020-4-16下午4:13:31
	 * @Description:
	 */
	//@Test
	public void test205AppAsXmlFor0128() throws Exception{
		
		Map<String, String> map = new TreeMap<String, String>();
		map.put("SourceSysId", SourceSysId);
		map.put("MsgTpId", "205");
		map.put("ReservedField", "1375");// 预留位
		map.put("PyeeAcctIssrId", "C1010611003601");// 收款方账户所属机构标识
		map.put("PyeeAcctTp", "00");//收款方账户类型
		//清算账户信息
		map.put("ResfdAcctIssrId", "");
		map.put("InstgAcctId", "");
		//map.put("InstgAcctNm", "");
		
		map.put("TrxCtgy", "0128");
		map.put("TrxAmt", "CNY1.00");// 交易金额
		map.put("OriBizTp", "110001");
		map.put("OriTrxId", "2020041317273499396639481770101");// 原交易流水号
		map.put("OriDbtrBankId", "20180917111506");// 原付款行银行流水号
		map.put("OriTrxAmt", "CNY1.00");// 原交易金额
//		map.put("BatchId", "B201809170012");// 批次号
		
		map.put("PyerAcctIssrId", "C1010611003601");// 收单机构金融机构标识
		map.put("PyerAcctId", "20103003");// 退款方收单机构账户编号
		map.put("PyerAcctTp", "04");// 退款方账户类型
		map.put("OrdrId", ""+new Date().getTime());// 订单编号
		map.put("OrdrDtTm", dateToStr(new Date()) );
		sendEpccApi(map);
	}
	
	/**
	 * 
	 * @author: wzf
	 * @Title
	 * @Time: 2020-4-16下午4:14:59
	 * @Description:交易状态查询申请
	 */
	//@Test
	public void test301AppAsXML() throws Exception {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("SourceSysId", SourceSysId);
		map.put("MsgTpId", "301");
		map.put("ReservedField", "1375");// 预留位
		map.put("OriTrxCtgy", "0127");// 
		map.put("OriTrxId", "2020031201184678962683611770003");// 
		map.put("OriTrxDtTm", "2020-03-12T14:53:08");// 
		sendEpccApi(map);
	}
	
	/**
	 * 
	 * @author: wzf
	 * @Title
	 * @Time: 2020-4-16下午4:14:30
	 * @Description:交易详情查询申请
	 */
	//@Test
	public void test313AppAsXML() throws Exception {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("SourceSysId", SourceSysId);
		map.put("MsgTpId", "313");
		map.put("ReservedField", "1375");// 预留位
		
		map.put("OriTrxCtgy", "0117");// 原交易类别
//		map.put("OriTrxId", "2019111401430076490337831770001");// 原交易流水号
//		map.put("OriTrxDtTm", "2019-11-14T12:21:17");// 原交易日期时间
//		map.put("OriTrxId", "2019111401572097416305311770105");// 原交易流水号
//		map.put("OriTrxDtTm", "2019-11-14T10:31:21");// 原交易日期时间2019/11/14 10:31:21
		map.put("OriTrxId", "2020041317273499396639481770101");// 原交易流水号
		map.put("OriTrxDtTm", "2020-04-13T17:27:34");// 原交易日期时间2019/11/14 10:31:21
		sendEpccApi(map);
	}
	
	
	public static void sendEpccApi(Map<String, String> map) {
		Map<String, String> signMap = new TreeMap<String, String>();
		for (String name : map.keySet()) {
			if(StringUtils.isNotBlank(map.get(name))){
				signMap.put(name, map.get(name));
			}
		}
		// 用户公钥
//		String key = "fortuneBill2019";
		signMap.put("MerSign", signKey);
		String encrySign = SecUtil.getSHA256Encrypt(convertMapToString(signMap, "&"));
		map.put("MerSign", encrySign);

		sendHttps(map, url);
	}
	
	public static void sendHttps(Map<String, String> map, String url) {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			TrustManager tm = new TrustAllStrategy();
			sslContext.init(null, new TrustManager[] { tm }, null);

			SSLSocketFactory sslSocketFactory = new SSLSocketFactory(sslContext);
			sslSocketFactory
					.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme protocolScheme = new Scheme("HTTPS", 443, sslSocketFactory);
			httpClient.getConnectionManager().getSchemeRegistry()
					.register(protocolScheme);


			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			// 装填参数
			List<NameValuePair> pars = new ArrayList<NameValuePair>();
			System.out.println("请求参数：");
			for (String str : map.keySet()) {
				pars.add(new BasicNameValuePair(str, map.get(str)));
				System.out.println(str + ":" + map.get(str));
			}

			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(pars,
					"UTF-8");
			httpPost.setEntity(uefEntity);
			// 执行请求操作，并拿到结果（同步阻塞）
			long t1 = System.currentTimeMillis();
			HttpResponse response = httpClient.execute(httpPost);
			long t2 = System.currentTimeMillis();
			System.out.println("共耗时（ms）：" + (t2 - t1));
			// 获取结果实体
			HttpEntity entity = response.getEntity();
			String contentMimeType = EntityUtils.getContentMimeType(entity);

			String body = "";
			if (entity != null) {
				// 按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			System.out.println("------------------------");
			System.out.println("返回信息:" + body);
			System.out.println("------------------------");
			if (contentMimeType.contains("application/json")) {
				Map<String, Object> result = JSON.parseObject(body);
				Map<String, Object> treeMap = new TreeMap<>();
				String MsgTpId=(String) result.get("MsgTpId");
				if(dess.containsKey(MsgTpId)){//敏感字段解密
					String[] dessFileds=dess.get(MsgTpId);
					for (String filed : dessFileds) {
						String desValue=null;
						Object obj=result.get(filed);
						desValue=obj!=null?obj.toString():desValue;
						result.put(filed,DESStrategyUtil.decryptMode(desValue,desKey));
					}
				}
				
				treeMap.putAll(result);

				for (String str : treeMap.keySet()) {
					System.out.println(str + ":" + result.get(str));
				}
			
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	public static String convertMapToString(Map<String, String> map,
			String spliceType) {
		StringBuilder sb = new StringBuilder();

		for (String name : map.keySet()) {
			sb.append(name).append("=").append(map.get(name))
					.append(spliceType);

		}
		sb.delete(sb.lastIndexOf(spliceType), sb.lastIndexOf(spliceType)
				+ spliceType.length());
		return sb.toString();

	}
	
	private String dateToStr(Date date){
		String dtFormat = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat sdf=new SimpleDateFormat(dtFormat);
		return sdf.format(date);
		
	}
}
