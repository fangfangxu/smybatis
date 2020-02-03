package com.imooc.sm.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller("testController")
public class TestController {
    /**
     * 访问使用一定规则：/test/show.do
     * @param request
     * @param response
     */
    public void show(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("name","张三");
        try {
            request.getRequestDispatcher("../show.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
