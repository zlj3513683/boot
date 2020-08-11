package com.example.demo;

import com.example.demo.config.ApplicationConfig;
import com.example.demo.entity.BussBillInfo;
import com.example.demo.jiguang.Jdpush;
import com.example.demo.service.IBussBillInfoService;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/8/10
 */
@Slf4j
@RestController
@RequestMapping(value = "/mysql")
public class MysqlController {
    @Autowired
    private ApplicationConfig applicationConfig;
    @Autowired
    private IBussBillInfoService bussBillInfoService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8", value = "/hello")
    public @ResponseBody
    JsonObject message(String message) {
        JsonObject resultModel = new JsonObject();
        BussBillInfo bussBillInfo = bussBillInfoService.selectById("0000000001160101000001");

        resultModel.addProperty("bill",bussBillInfo.getBillName());

        return resultModel;
    }

}
