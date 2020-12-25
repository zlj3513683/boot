package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.DemoService;
import com.example.demo.apo.Cs;
import com.example.demo.bean.User;
import com.example.demo.exception.AppBizException;
import com.example.demo.request.DemoRequest;
import com.example.demo.request.FaceRequest;
import com.example.demo.request.ValidRequest;
import com.example.demo.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author 2019/12/30
 * @author zoulinjun
 */
@Slf4j
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Cs
    @RequestMapping(value = "/hello.htm", method = RequestMethod.POST)
    public String hello(@Valid @RequestBody  FaceRequest faceRequest,BindingResult results) {
        if (results.hasErrors()) {
            FieldError error = results.getFieldError();
            throw  new AppBizException("0001",error.getField()+error.getDefaultMessage());
        }
    log.info("是否乱码了");

//        User u = new User("");

        int k = 111;
        if(k > 22){
//            demoService.test();
//            throw  new AppBizException("9999","数据大于22");
        }
        return "Hello World";
    }

    @RequestMapping(value = "/cs.htm", method = RequestMethod.POST)
    public String cs(@Valid @RequestBody  User user,BindingResult results) {
        if (results.hasErrors()) {
            FieldError error = results.getFieldError();
            throw  new AppBizException("0001",error.getField()+error.getDefaultMessage());
        }
    log.info("是否乱码了");

//        User u = new User("");

        int k = 111;
        if(k > 22){
//            demoService.test();
//            throw  new AppBizException("9999","数据大于22");
        }
        return "Hello World";
    }


    @ResponseBody
    @RequestMapping(value = "/valid.htm", method = RequestMethod.POST)
    public Map<String, Object> valid(@Valid ValidRequest faceRequest, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        List<ObjectError> oes = errors.getAllErrors();
        for (ObjectError oe : oes) {
            String key = null;
            String msg = null;
            // 字段错误
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                key = fe.getField();// 获取错误验证字段名
            } else {
                // 非字段错误
                key = oe.getObjectName();// 获取验证对象名称
            }
            // 错误信息
            msg = oe.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }

    @ResponseBody
    @RequestMapping(value = "/c2b.htm", method = RequestMethod.POST)
    public String c2b(@RequestBody JSONObject jsonObject) {

        ValidatorUtils.validateEntity(jsonObject);

        User t  = (User)JSON.parseObject(jsonObject.toString(),User.class);
        ValidatorUtils.validateEntity(t);
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        JSONObject object = new JSONObject();
        object.put("msg","失败了吗");
        return object.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/csParm.htm", method = RequestMethod.GET)
    public String csParm(@RequestBody JSONObject jsonObject) {

        DemoRequest request = new DemoRequest(jsonObject);
        System.out.println(request);
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        JSONObject object = new JSONObject();
        object.put("msg","失败了吗");
        return object.toJSONString();
    }

    @RequestMapping(value = "/tag.htm", method = RequestMethod.GET)
    public String tag(HttpServletRequest request) {

        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        JSONObject object = new JSONObject();
        object.put("msg","失败了吗");
        return "index";
    }

    @RequestMapping(value = "/tag.htm",params = "method=edit", method = RequestMethod.GET)
    public String tag2(HttpServletRequest request) {

        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        JSONObject object = new JSONObject();
        object.put("msg","失败了吗");
        return "index1";
    }
    @RequestMapping(value = "/tag2.htm", params = "method=edit",method = RequestMethod.GET)
    public void tag1(HttpServletRequest request, HttpServletResponse response) throws  Exception{

        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        JSONObject object = new JSONObject();
        object.put("msg","失败了吗");
        response.sendRedirect("/tag.htm?method=edit");
    }


}
