package com.example.demo.util;

import org.bouncycastle.util.encoders.Base64;

import java.io.*;

/**
 * 功能：
 *
 * @author 2020/1/14
 * @author zoulinjun
 */
public class FileUtil {
    public static String file2Base64(File file) {
        if(file==null) {
            return null;
        }
        String base64 = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            byte[] buff = new byte[fin.available()];
            fin.read(buff);
            base64 = Base64.toBase64String(buff);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return base64;
    }

    public static File base64ToFile(String base64) {
        if(base64==null||"".equals(base64)) {
            return null;
        }
        byte[] buff=Base64.decode(base64);
        File file=null;
        FileOutputStream fout=null;
        try {
            file = File.createTempFile("tmp", null);
//            file = new File("E:\\zlj\\qb\\pic\\1.jpg");
            fout=new FileOutputStream(file);
            fout.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fout!=null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static void main(String[] args) throws IOException {
        String picStr = FileUtil.file2Base64(new File("E:\\zlj\\qb\\doc\\222.txt"));
        System.out.println(picStr);
        File file = FileUtil.base64ToFile(picStr);

        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(file);
            showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            FileOutputStream fout=new FileOutputStream(new File("E:\\zlj\\qb\\doc\\333.txt"));
            while ((byteread = in.read(tempbytes)) != -1) {
                fout.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }

//        FileOutputStream fout=new FileOutputStream(new File("E:\\zlj\\qb\\doc\\333.txt"));
//        //创建字节流缓冲区，加快写出速度
//        BufferedOutputStream bout=new BufferedOutputStream(fout);
//
//        //创建字符输出流对象
//        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(bout, "utf-8"));
//
//        bw.write(bw);
//
//        bw.flush();
//        bw.close();

    }

    private static void showAvailableBytes(InputStream in) {
        try {
            System.out.println("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
