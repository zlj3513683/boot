package com.example.demo.jiguang;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.bing.Jdpush.Jdpush;

import java.io.IOException;
/**
 * 功能：
 *
 * @author zoulinjun
 * @date 2020/5/22
 */
public class JiGuangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String appKey ="";
    private static final String masterSecret = "";
    private static final String message = "辛勤的老师给你布置了一点作业，请查看";
    public JiGuangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jdpush.testSendPush(appKey,masterSecret,message);
        System.out.println("sucess");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}