package com.example.demo;

import com.example.demo.config.ApplicationConfig;
import com.example.demo.jiguang.Jdpush;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/5/22
 */
@Slf4j
@RestController
@RequestMapping(value = "/Jdpush")
public class JiGuangController {


    @Autowired
    private ApplicationConfig applicationConfig;

    /**
     * 向所有人推送消息
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/message")
    public @ResponseBody
    JsonObject message(String message) {
        JsonObject resultModel = new JsonObject();
        try{
            Jdpush.testSendPush(applicationConfig.getAppkey(),applicationConfig.getMasterSecret(),message);
            resultModel.addProperty("status",200);
            resultModel.addProperty("code",0000);
            resultModel.addProperty("msg","推送成功");
        }catch (Exception e){
            log.error("推送失败",e);
            resultModel.addProperty("status",500);
            resultModel.addProperty("code",1000);
            resultModel.addProperty("msg","推送失败");
        }

        return resultModel;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/message2")
    public String messag2e(String message) {

        return "index";
    }

    @GetMapping("/index2")
    public String index(){
        return "index"; //当浏览器输入/index时，会返回 /templates/home.html页面
    }
}
