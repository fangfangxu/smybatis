# smybatis
项目练习：人员管理系统

全路径：http://localhost:8080/sm/staff/list.do

     request.getContextPath():获取项目的根路径:http://localhost:8080/sm
     request.getServletPath(): /staff/list.do
     
     加'/'访问方式说明：
     （1）重定向（从web服务器根路径开始，自己加web相应根路径sm）：http://localhost:8080
     response.sendRedirect("/sm/toLogin.do"); 
     （2）转发（从web应用根路径开始）：http://localhost:8080/sm
     request.getRequestDispatcher("/index.jsp").forward(request, response); 