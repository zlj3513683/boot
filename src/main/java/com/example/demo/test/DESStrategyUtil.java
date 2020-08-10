package com.example.demo.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;


public class DESStrategyUtil {
	// 定义加密算法，有DES、DESede(即3DES)、Blowfish
	private static final String Algorithm = "DESede";
	private static final String PASSWORD_CRYPT_KEY = "shenzhenglobebill3Desede";
	 // ECB不需要向量iv，CBC需要向量iv
    private static final String Transformation = "DESede/ECB/PKCS5Padding";// "算法/模式/补码方式"
	
	/*
	 * 根据字符串生成密钥字节数组
	 * 
	 * @param keyStr 密钥字符串
	 * 
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		System.out.println(toHex(key));;
		return key;
	}

	

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = "0" + hex;
			}
			ret += hex;
		}
		return ret;
	}

	public static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
			sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(buffer[i] & 15, 16));
		}
		return sb.toString();
	}
//	  public static String decryptMode(String str) {
//		  return decryptMode(str, PASSWORD_CRYPT_KEY);
//	  }
//	  public static String encryptMode(String str) {
//		  return encryptMode(str, PASSWORD_CRYPT_KEY);
//	  }
	    /**
	     * 解密
	     * 
	     * @param str
	     * @return
	     */
	    public static String decryptMode(String str,String key) {
	        if (StringUtils.isBlank(str)) {
	            return str;
	        }
	        try {
	            byte[] raw = key.getBytes("utf-8");
	            SecretKey skey = new SecretKeySpec(raw, Algorithm);
	            Cipher cipher = Cipher.getInstance(Transformation);
	            cipher.init(Cipher.DECRYPT_MODE, skey);
	            byte[] encrypted1 = new Base64().decode(str);
	            byte[] original = cipher.doFinal(encrypted1);
	            String originalString = new String(original, "utf-8");
	            return originalString;
	        } catch (Exception e) {
	        	System.err.println(str+"3des解密失败"+e.getMessage());
	            return null;
	        }
	    }

	    /**
		 * 加密方法
		 * 
		 * @param str
		 *            源数据的字符串
		 * @return
		 */
	    public static String encryptMode(String str,String key) {
	    	if (StringUtils.isBlank(str)) {
	            return str;
	        }
	    	try {
	    		byte[] raw = key.getBytes("utf-8");
	            SecretKey skey = new SecretKeySpec(raw, Algorithm);
	            Cipher cipher = Cipher.getInstance(Transformation);
	            cipher.init(Cipher.ENCRYPT_MODE, skey);
	            byte[] original = cipher.doFinal(str.getBytes("utf-8"));
	            String originalString = new Base64().encodeToString(original);
	            return originalString;
			} catch (Exception e) {
				System.err.println(str+"3des加密失败"+e.getMessage());
				return null;
			}
			
	    
	    }
	    /**
		 * 加密方法
		 * 
		 * @param str
		 *            源数据的字符串
		 * @return
		 */
		/*public static String encryptMode(String str) {
			try {
				byte[] src = str.getBytes("utf-8");
				SecretKey deskey = new SecretKeySpec(
						build3DesKey(PASSWORD_CRYPT_KEY), Algorithm); // 生成密钥
				Cipher c1 = Cipher.getInstance(Algorithm); // 实例化负责加密/解密的Cipher工具类
				c1.init(Cipher.ENCRYPT_MODE, deskey); // 初始化为加密模式
				return bytesToHexString(c1.doFinal(src));
			} catch (java.security.NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (javax.crypto.NoSuchPaddingException e2) {
				e2.printStackTrace();
			} catch (java.lang.Exception e3) {
				e3.printStackTrace();
			}
			return null;
		}
*/
		/**
		 * 解密函数
		 * 
		 * @param str
		 *            密文的字符串
		 * @return
		 *//*
		public static String decryptMode(String str) {
			try {
				byte[] src = hexStringToBytes(str);
				SecretKey deskey = new SecretKeySpec(
						build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);
				Cipher c1 = Cipher.getInstance(Algorithm);
				c1.init(Cipher.DECRYPT_MODE, deskey); // 初始化为解密模式
				return new String(c1.doFinal(src), "utf-8");
			} catch (java.security.NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			} catch (javax.crypto.NoSuchPaddingException e2) {
				e2.printStackTrace();
			} catch (java.lang.Exception e3) {
				e3.printStackTrace();
			}
			return null;
		}*/

	    public static void main(String[] args) throws Exception {
			String msg = "深大";
			System.out.println("【加密前】：" + msg);
			// 加密
			String secretArr = DESStrategyUtil.encryptMode(msg,PASSWORD_CRYPT_KEY);

			System.out.println("【加密后】：" + secretArr);

			// 解密
			String myMsgArr1 = DESStrategyUtil.decryptMode(secretArr,PASSWORD_CRYPT_KEY);
			System.out.println("【解密后】：" + myMsgArr1);
		}
}
