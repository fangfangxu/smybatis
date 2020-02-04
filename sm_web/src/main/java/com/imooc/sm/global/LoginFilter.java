package com.imooc.sm.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getServletPath();
        if (path.toLowerCase().indexOf("login") != -1) {
            //放行
            filterChain.doFilter(request, response);
        } else {
            //验证是否登陆
            HttpSession session = request.getSession();
            Object o = session.getAttribute("USER");
            if (o != null) {
                //放行
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/sm/toLogin.do");
            }


        }
    }
}
