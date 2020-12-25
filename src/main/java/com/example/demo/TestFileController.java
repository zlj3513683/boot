package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/12/18
 */
@Slf4j
@RestController
public class TestFileController {


    @RequestMapping(value = "/upload.htm",method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response,
                       @RequestParam("imgSource") List<MultipartFile> imgSource) {
//    public void upload(HttpServletRequest request, HttpServletResponse response) {

//        @RequestParam("imgSource") MultipartFile imgSource

        log.info("1111111111111111111111111 kaishi ");

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("imgSource");
        List<MultipartFile> files1 = ((MultipartHttpServletRequest) request).getFiles("imgSource1");

        log.info("sss:" + files.size());
        log.info("sss:" + files1.size());
    }


}
