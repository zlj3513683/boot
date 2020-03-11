package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/3/4
 */
@Controller
@Slf4j
public class PageController {
    @RequestMapping("/index.htm")
    public String index(HttpServletRequest request){
        Map<String,Integer> maps = new HashMap<String, Integer>();
        maps.put("a",53);
        maps.put("b",23);
        maps.put("c",231);
        maps.put("d",244);
        maps.put("e",1231);
        maps.put("f",1231);
        request.setAttribute("map",maps);
        List<Map> bbb = new ArrayList<>();
        Map<String,String > b1 = new HashMap<String, String>();
        b1.put("method","a~c");
        Map<String,String > b2 = new HashMap<String, String>();
        b2.put("method","d~f~e");
        bbb.add(b1);
        bbb.add(b2);
        request.setAttribute("bbb",bbb);
        log.info("进入index");
        return "index";
    }

}
