package com.example.demo.test;

import org.apache.http.conn.ssl.TrustStrategy;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 信任所有证书，忽略校验的策略
 * @author heyiyi
 */
public class TrustAllStrategy implements TrustStrategy ,X509TrustManager{

	@Override
	public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		//信任所有证书，用于通知商户时忽略验证商户证书有效性
		return true;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}

}
