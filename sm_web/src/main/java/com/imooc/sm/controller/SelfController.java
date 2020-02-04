package com.imooc.sm.controller;

import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("selfController")
public class SelfController {
    @Autowired
    private SelfService selfService;

    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/sm/login.jsp");
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Staff staff = selfService.login(account, password);
        if (staff == null) {
            //跳登录页
            response.sendRedirect("/sm/toLogin.do");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("USER", staff);
            //跳登陆成功页
            response.sendRedirect("/sm/main.do");
        }
    }

    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("USER", null);
        response.sendRedirect("/sm/toLogin.do");
    }


}
