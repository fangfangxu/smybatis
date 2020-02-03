package com.imooc.sm.controller;

import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("staffController")
public class StaffController {
    @Autowired
    private StaffService staffService;
    /**
     * /staff/方法名.do
     */
    /**
     *  查询员工列表
     * /staff/list.do /staff_list.jsp
     */
    public void list(HttpServletRequest request, HttpServletResponse response){
        List<Staff> list= staffService.getList();
        request.setAttribute("LIST",list);
        try {
            request.getRequestDispatcher("../staff_list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  打开添加员工页面
     * /staff/toAdd.do /staff.jsp
     */
    public void toAdd(HttpServletRequest request, HttpServletResponse response){

        try {
            request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  新增员工
     * /staff/add.do /staff_list.jsp
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");
        String account=request.getParameter("account");
        Staff staff=new Staff();
        staff.setName(name);
        staff.setAccount(account);
        staffService.add(staff);
        response.sendRedirect("list.do");
    }

    /**
     *  打开编辑员工页面
     * /staff/toEdit.do /staff_edit.jsp
     */
    public void toEdit(HttpServletRequest request, HttpServletResponse response){
        Integer id=Integer.parseInt(request.getParameter("id"));
        Staff staff=staffService.selectById(id);
        request.setAttribute("STAFF",staff);
        try {
            request.getRequestDispatcher("../staff_edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  编辑员工
     * /staff/edit.do   /staff_list.jsp
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String account=request.getParameter("account");
        Staff staff=staffService.selectById(id);
        staff.setAccount(account);
        staff.setName(name);
        staff.setId(id);
        staffService.edit(staff);
        response.sendRedirect("list.do");
    }

    /**
     *  删除员工
     * /staff/remove.do /staff_list.jsp
     */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        staffService.delete(id);
        response.sendRedirect("list.do");
    }

}
