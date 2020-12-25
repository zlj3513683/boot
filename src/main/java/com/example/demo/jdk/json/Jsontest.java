package com.example.demo.jdk.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.constraints.pl.NIP;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/8
 */
public class Jsontest {

    public static void main(String[] args) {
        JsonObject jsonObject2 = new JsonObject();
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.addProperty("url","111");
        jsonObject1.addProperty("name","1223");
        jsonObject2.addProperty("aaa","abcd");
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("title",jsonObject1);
        jsonObject.add("body",jsonObject2);
        System.out.println(jsonObject.toString());

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("name","susan");
//        System.out.println(jsonObject3.put("imgUrl","ergpizo"));
        jsonObject3.put("imagUrl",jsonObject3.put("imgUrl","ergpizo"));
        jsonObject3.put("aaa",null);
        System.out.println(jsonObject3.toJSONString());

        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("def");

        Map map = new HashMap();

        System.out.println("ceshi hashma");

        Object obj;
        for (int i = 0; i < 11; i++) {
            String key = "str" ;
            if( (obj= map.put((key),"ergouzi")) != null){
                System.out.println(key + "---------" + obj);
            }
        }
        JSONArray array = new JSONArray();
        JSONObject objec1 = new JSONObject();
        JSONObject objec2 = new JSONObject();
        objec1.put("picExtName","1.zip");
        objec1.put("picContent","fkasfjkafjkasjfas");
        objec2.put("picExtName","2.zip");
        objec2.put("picContent","aasfasfasfasfafa");

        JSONArray array2 = new JSONArray();
        JSONObject objec3 = new JSONObject();
        JSONObject objec4 = new JSONObject();
        objec3.put("picExtName","3.zip");
        objec3.put("picContent","fkasfjkafjkasjfas");
        objec4.put("picExtName","4.zip");
        objec4.put("picContent","aasfasfasfasfafa");
        JSONObject object = new JSONObject();
        array.add(objec1);
        array.add(objec2);
        array2.add(objec3);
        array2.add(objec4);
        object.put("file1",array);
        object.put("file2",array2);
        System.out.println(object);

        JSONObject body = new JSONObject();
        body.put("param1","1111");
        body.put("fileData",object);
        System.out.println(body);

        JSONObject req = new JSONObject();
        req.put("head","");
        req.put("signature","");
        req.put("body",body);
        System.out.println(req
        .toJSONString());

    }


}
