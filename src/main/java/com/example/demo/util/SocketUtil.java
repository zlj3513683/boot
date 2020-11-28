package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class SocketUtil {
    private static Logger logger = LoggerFactory.getLogger(SocketUtil.class);
    public static final String CHARSET = "GBK";		//报文编码格式
    /**
     * 发送socket请求
     * @param clientIp
     * @param clientPort
     * @param msg
     * @return
     */
    private static synchronized byte[] tcpPost(String clientIp,String clientPort,byte[] msg){
        byte[] rs = null;

        if(clientIp==null||"".equals(clientIp)||clientPort==null||"".equals(clientPort)){
            logger.error("socket ip或端口不存在...");
            return null;
        }

        int clientPortInt = Integer.parseInt(clientPort);

        logger.info("clientIp："+clientIp+" clientPort："+clientPort);

        Socket s = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            s = new Socket(clientIp, clientPortInt);
            s.setSendBufferSize(4096);
            s.setTcpNoDelay(true);
            s.setSoTimeout(60*1000);
            s.setKeepAlive(true);
            out = s.getOutputStream();
            in = s.getInputStream();

            //准备报文msg
            logger.info("准备发送报文："+ byte2HexStr(msg));

            out.write(msg);
            out.flush();

            byte[] rsByte = readStream(in);

            if(rsByte!=null){
                rs = rsByte;
            }


        } catch (Exception e) {
            logger.error("tcpPost发送请求异常："+e.getMessage());
        }finally{
            logger.info("tcpPost(rs)："+rs);
            try {
                if(out!=null){
                    out.close();
                    out = null;
                }
                if(in!=null){
                    in.close();
                    in = null;
                }
                if(s!=null){
                    s.close();
                    s = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return rs;

    }

    public static String byte2HexStr(byte[] b) {
        if(b == null)
            return "";
        String stmp = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            // sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * 读取输入流
     * @param in
     * @return
     */
    private static byte[] readStream(InputStream in){
        if(in==null){
            return null;
        }

        byte[] b = null;
        ByteArrayOutputStream outSteam = null;
        try {
            byte[] buffer = new byte[1024];
            outSteam = new ByteArrayOutputStream();

            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }

            b = outSteam.toByteArray();
        } catch (IOException e) {
            logger.error("读取流信息异常"+e);
            e.printStackTrace();
        } finally{
            try {
                if(outSteam!=null){
                    outSteam.close();
                    outSteam = null;
                }
                if(in!=null){
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }
    /**
     * int到byte[] 由高位到低位
     * @param i 需要转换为byte数组的整行值。
     * @return byte数组
     */
    public static byte[] int2Byte(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }



    public static void main(String[] args) throws Exception {
//        请求报文：
//        4字节 报文长度 不包含本字段 十进制 左补零
//        4字节 交易码   固定值：6681
//        15字节 商户号
//        8字节  终端号   （商户号终端号将用于查询用于加密密码密文的PINKEY）
//        32字节 密码被国际密钥加密的密文  左对齐 右补空格
//        32字节 源账号  左对齐 右补空格  现有场景下应该是一个时间字符串
//        32字节 目标账号  左对齐 右补空格  现有场景下应该是一个时间字符串 且应该等同于源账号
//        32字节 国密密钥被加密机LMK加密的密文
        byte[] len = SocketUtil.int2Byte(155);
        byte[] transType = SocketUtil.int2Byte(6681);
        byte[] merNo = "666290073720003".getBytes(CHARSET);
        byte[] terNo = "12345678".getBytes(CHARSET);
        byte[] des3Pin = "22222222222222222222222222222222".getBytes(CHARSET);
        byte[] srcAct = "                        20200318".getBytes(CHARSET);
        byte[] desAct = "                        20200318".getBytes(CHARSET);
        byte[] sm4Key = "5907DC3D837CDD4A512AF6AE341618C3".getBytes(CHARSET);

        byte[] msg =new byte[159];
        System.arraycopy(len,0,msg,0,len.length);
        System.arraycopy(transType,0,msg,4,transType.length);
        System.arraycopy(merNo,0,msg, 8,merNo.length);
        System.arraycopy(terNo,0,msg,23,terNo.length);
        System.arraycopy(des3Pin,0,msg,31,des3Pin.length);
        System.arraycopy(srcAct,0,msg,63,srcAct.length);
        System.arraycopy(desAct,0,msg,95,desAct.length);
        System.arraycopy(sm4Key,0,msg,127,sm4Key.length);

        byte[] data= SocketUtil.tcpPost("192.168.1.81","19010",msg);
        logger.info(new String(data));


    }






















}
