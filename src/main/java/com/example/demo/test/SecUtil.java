package com.example.demo.test; /**
 * 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 </p>
 * <p>Company: Lotery</p>
 * @author max
 * @version V1.0 
 * @date 2011-8-29
 */


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Title: </p>
 * <p>Description: 加密的工具类</p>
 * <p>Copyright: Copyright (c) 2011 版权</p>
 * <p>Company: </p>
 * @author max
 * @version V1.0 
 * @date 2011-8-29上午11:07:41
 */
public class SecUtil {
	
	
	/**
	 * 
	 * @author: kevin
	 * @Title getEncrypt
	 * @Time: 2011-6-10下午02:26:59
	 * @Description: SHA256位加密
	 * @return: String 
	 * @throws: 
	 * @param strSrc
	 * @return
	 */
    public static String getSHA256Encrypt(String strSrc) {
        MessageDigest md = null;
        String strDes = null;

        try { 
        	byte[] bt = strSrc.getBytes("UTF-8");
            md = MessageDigest.getInstance("SHA-256");
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        }  catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);	        	
            return null;
        }catch (Exception e) {
        	System.err.println(e);        	
		}
        return strDes;
    }
    /**
     * 
     * @author: kevin
     * @Title bytes2Hex
     * @Time: 2011-6-10下午02:27:13
     * @Description: 
     * @return: String 
     * @throws: 
     * @param bts
     * @return
     */
    public static String bytes2Hex(byte[]bts) {
//        String des = "";
        StringBuffer des = new StringBuffer("");
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
//                des += "0";
                des.append("0");
            }
//            des += tmp;
             des.append(tmp);
        }
        return des.toString();
    }	
	

}
