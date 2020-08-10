package com.example.demo.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class HlhtController {
	private Logger log = Logger.getLogger(HlhtController.class);

	private static Map<String,String> keys ;
	static{
		keys=new HashMap();
		keys.put("01", "globebill2018");
		keys.put("04", "fortuneBill2019");
	}
	@RequestMapping(value = { "hlhtNotify" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST },produces="application/json; charset=utf-8")
	@ResponseBody
	public String get305(@RequestBody String str) throws Exception {
		Map<String, String> reqMap = JSONObject.parseObject(str, TreeMap.class);
		log.info("接收："+reqMap);
		Map<String, String> respMap = new TreeMap<String, String>();
		String  jsonStr = "";
		String SourceSysId=reqMap.get("SourceSysId");
		String key=keys.get(SourceSysId);
//		if(!EpccUtil.validSign(reqMap, key)){
//			log.info("验签未通过");
//		}
		respMap.put("SourceSysId", SourceSysId);
		if(reqMap.get("MsgTpId").equals("305")){
			respMap.put("MsgTpId", "306");
			respMap.put("PyeeAcctTp", "04");
			respMap.put("PyeeAcctId", "20103003");
			respMap.put("PyeeNm", "wzf");
			respMap.put("PyeeCtryNo", "CN");
			respMap.put("PyeeAreaNo", "440300");
			respMap.put("PyeeTrxTrmTp", "01");
			respMap.put("PyeeTrxTrmNo", "2312233");
			/*cnpc://arqr.clr.com/10002000300040005000602/DC?id=20180101ABCD0000222
				2&am=CNY200.00&sa=02&si=Z1000000000001&sn=4000300011&sc=bJMNhV3WPyVz84GL0hVgu2p
				u%2BzcKZQ0ER7ACVOjVFaYtbZfKTax4bQ0GU1aTLMtChw6JRI7jTZSlbnkWk9pk3g%3D*/
			respMap.put("LmtAcctTp", "01");
			respMap.put("BizTp", "120001");
			respMap.put("TrxAmt", "CNY1.00");
			respMap.put("TrxId", reqMap.get("TrxId"));
			respMap.put("OrdrId", "2020041416573463737859341770123");
			respMap.put("OrdrDesc", "test");
			respMap.put("PltfrmNm", "jiancheng");
			respMap.put("StorNb", "1112");
			respMap.put("StorNm", "test");
			respMap.put("StorArea", "440300");
			respMap.put("StorAdrs", "test");
			respMap.put("StorCshr", "22");
			respMap.put("AcgAgentId", "22");
			respMap.put("AcgAgentNm", "aaa");
			respMap.put("SysRtnInf", "系统处理成功");
			respMap.put("SysRtnCd", "00000000");
			respMap.put("SysRtnDesc", "系统处理成功");
			respMap.put("BizStsCd", "00000000");
//		respMap.put("BizStsCd", "RB600023");
//		respMap.put("BizStsCd", "RB600024");
//		respMap.put("BizStsCd", "RB600025");
			respMap.put("BizStsDesc", "业务处理成功");
		}else {
			respMap.put("MsgTpId", "262");
			respMap.put("TrxId", reqMap.get("TrxId"));
			respMap.put("TrxDtTm", "2020-04-10T16:36:04");
			
			respMap.put("TrxStatus", "02");
			
			respMap.put("TrxAmt", "CNY1.00");
			respMap.put("BizTp", reqMap.get("BizTp"));
			respMap.put("AcctInTp", "01");
			respMap.put("TrxCtgy", "0117");
			respMap.put("OrdrDesc", "2|2|商户名称 商户名称 1%123456789012345%01%11%123456789012345%5045%2#2#商品 1^CNY1000.00^1#商品 2^CNY999.99^ 99999|商户名称 商户名称 2%223456789012345%04%01%410109197001019871%5046%1#1#商品 3^CNY1.00^1|");
			respMap.put("OrdrId", "2020042809393812955391331770102");
//			respMap.put("PltfrmNm", "1354391769252974253895462493534786924626");
			respMap.put("BatchId", "B201907090101");
			respMap.put("RPFlg", "");
			
			respMap.put("SysRtnInf", "系统处理成功");
			respMap.put("SysRtnCd", "00000000");
			respMap.put("SysRtnDesc", "系统处理成功");
			respMap.put("BizStsCd", "00000000");
			respMap.put("BizStsDesc", "业务处理成功");
		}
		
//		EpccUtil.addSign(respMap, key);
		jsonStr = JSON.toJSONString(respMap);
		log.info("响应："+respMap);
		return jsonStr;
	}
	
	
	
}