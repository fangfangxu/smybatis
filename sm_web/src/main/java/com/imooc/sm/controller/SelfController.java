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

    /**
     * 个人中心
     *
     * @param request
     * @param response
     */
    public void info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }


    /**
     * 打开修改密码页面
     *
     * @param request
     * @param response
     */
    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/change_password.jsp").forward(request, response);
    }


    /**
     * 修改密码页面
     *
     * @param request
     * @param response
     */
    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        HttpSession session = request.getSession();

        Staff staff = (Staff) session.getAttribute("USER");
        if (!staff.getPassword().equals(password)) {
            request.getRequestDispatcher("/change_password.jsp").forward(request, response);
        } else {
            selfService.changePassword(staff.getId(), password1);
            response.sendRedirect("/sm/logout.do");
//          response.getWriter().print(" <script type=\"text/javascript\">parent.location.href=\"/sm/logout.do\"></script>");
        }


    }


}
