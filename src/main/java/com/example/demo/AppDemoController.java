package com.example.demo;

import com.example.demo.config.ApplicationConfig;
import com.example.demo.jiguang.Jdpush;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/7/7
 */
@Slf4j
@RestController
@RequestMapping(value = "/app")
public class AppDemoController {

    @Autowired
    private ApplicationConfig applicationConfig;

    /**
     * 获取用户标识
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,  value = "/getUserId")
    @ResponseBody
    public JsonObject message(HttpServletRequest request) {
        Map<String, String[]> pa =  request.getParameterMap();
        for (String key:
        pa.keySet()) {
            String val = pa.get(key)[0];
            log.info("key:" + key + ",value:" + val);
        }
        JsonObject resultModel = new JsonObject();

        resultModel.addProperty("version","1.0.0");
        resultModel.addProperty("bussId","01");
        resultModel.addProperty("fcCode","UPAY10001");
        resultModel.addProperty("issAddnData","cssss");
        resultModel.addProperty("userId","abcd342ds");
        resultModel.addProperty("respCode","00");
        resultModel.addProperty("respMsg","成功");
        resultModel.addProperty("signature","1B2E7004645C7F0D31009810B545A97B");

        return resultModel;
    }

    @RequestMapping(method = RequestMethod.POST,  value = "/push")
    @ResponseBody
    public JsonObject push(HttpServletRequest request) {
        Map<String, String[]> pa =  request.getParameterMap();
        for (String key:
        pa.keySet()) {
            String val = pa.get(key)[0];
            log.info("key:" + key + ",value:" + val);
        }
        JsonObject resultModel = new JsonObject();


        resultModel.addProperty("version","1.0.0");
        resultModel.addProperty("bussId","01");
        resultModel.addProperty("fcCode","UPAY10001");
        resultModel.addProperty("reqType","0740000903");
        resultModel.addProperty("issCode","assaaa");
        resultModel.addProperty("respCode","00");
        resultModel.addProperty("respMsg","成功");
        resultModel.addProperty("signature","assa");

        return resultModel;
    }


}
