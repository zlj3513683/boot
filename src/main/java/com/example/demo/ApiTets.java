package com.example.demo;

import com.example.demo.util.FileUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.junit.Test;

import java.io.*;

/**
 *在定义一个图片的json：保存多个图片base64 {pic1:"sdasdafa",pic2:"aaaaaa",pictype:下级传输类型（流/base64/path）}  --- base64 liu
 * 遍历出所有图片并保存  然后传数据流或者路径到相应的接口去
 *
 * @author sunlinan
 * @Description:
 * @date 2019-04-13 20:50
 */
public class ApiTets {
//    private String url="http://localhost:8000/pospUtils/UnaccAction.asp?method=dealWith";
    private String url="http://localhost:9090/upload.htm";
    private String filePath="E:\\zlj\\qb\\doc\\111.zip";

    public static void main(String[] args) {
        new ApiTets().inputStreamUpload();
    }

//    @Test
    public void inputStreamUpload() {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求
        //1)
        HttpPost post = new HttpPost(url);
        InputStream inputStream = null;
        try {
            //文件路径请换成自己的
            //2)
            inputStream = new FileInputStream(filePath);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            //第一个参数为 相当于 Form表单提交的file框的name值 第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            //3)
            builder.setContentType(ContentType.create("multipart/form-data", "UTF-8"));
            String picStr = FileUtil.file2Base64(new File("E:\\zlj\\qb\\doc\\222.txt"));
            File file = FileUtil.base64ToFile(picStr);
            builder.addBinaryBody("imgSource",file,ContentType.create("multipart/form-data"),"222.txt");
            builder.addBinaryBody("imgSource",new File(filePath));
            builder.addBinaryBody("imgSource1",file,ContentType.create("multipart/form-data"),"444.txt");
            builder.addBinaryBody("imgSource1",new File(filePath));
//            builder.addBinaryBody("imgSource", inputStream, ContentType.create("multipart/form-data"), "test1.PNG");
//            builder.addBinaryBody("imgSource", new FileInputStream(filePath), ContentType.create("multipart/form-data"), "test2.PNG");
//            builder.addBinaryBody("imgSource1", new FileInputStream(filePath), ContentType.create("multipart/form-data"), "test3.PNG");
//            builder.addBinaryBody("imgSource1", new FileInputStream(filePath), ContentType.create("multipart/form-data"), "test4.PNG");
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody("{\"accessKeyId\": \"12\",\"assumeRole\": false,\"cloudType\": \"AWS\",\"secretKey\": \"12\"}", ContentType.APPLICATION_JSON);
//            StringBody stringBody = new StringBody("11");
            builder.addPart("uuId",stringBody);
            builder.addPart("conSitua",stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            //获取请求响应码
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                entity = response.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    //转换为字节输入流
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                    String body = null;
                    while ((body = br.readLine()) != null) {
                        System.out.println(body);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


//    @Test
    public static void fileUpload() {
        //构建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //构建POST请求
        HttpPost httpPost = new HttpPost("http://10.10.109.90:8172/asr/speech2Text");
        InputStream inputStream = null;
        try {
            //要上传的文件
            File file = null;
            file = new File("C:\\uploadFile\\eb4c03ed9caa4cdd840d99f4ab96ad3d\\voice\\1111.mp3");
            //构建文件体
            FileBody fileBody = new FileBody(file, ContentType.MULTIPART_FORM_DATA, "1111.mp3");
            StringBody stringBody = new StringBody("12", ContentType.MULTIPART_FORM_DATA);
            HttpEntity httpEntity = MultipartEntityBuilder
                    .create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .addPart("file", fileBody)
                    .addTextBody("appId","sobot")
                    .build();
            httpPost.setEntity(httpEntity);
            //发送请求
            HttpResponse response = client.execute(httpPost);
            httpEntity = response.getEntity();
            if (httpEntity != null) {
                inputStream = httpEntity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}