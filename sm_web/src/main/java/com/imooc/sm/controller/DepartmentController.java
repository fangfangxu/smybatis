package com.imooc.sm.controller;

import com.imooc.sm.entity.Department;
import com.imooc.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller("departmentController")
public class DepartmentController {
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    /**
     *  查询部门列表
     * /department/list.do /department_list.jsp
     */
    public void list(HttpServletRequest request, HttpServletResponse response){
       List<Department> list= departmentService.getList();
        request.setAttribute("LIST",list);
        try {
            request.getRequestDispatcher("/department_list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  打开添加部门页面
     * /department/toAdd.do /department_add.jsp
     */
    public void toAdd(HttpServletRequest request, HttpServletResponse response){

        try {
            request.getRequestDispatcher("/department_add.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  新增部门
     * /department/add.do /department_list.jsp
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String name=request.getParameter("name");
       String address=request.getParameter("address");
       Department department=new Department();
       department.setName(name);
       department.setAddress(address);
       departmentService.add(department);
       response.sendRedirect("/sm/department/list.do");
    }

    /**
     *  打开编辑部门页面
     * /department/toEdit.do /department_edit.jsp
     */
    public void toEdit(HttpServletRequest request, HttpServletResponse response){
        Integer id=Integer.parseInt(request.getParameter("id"));
        Department department=departmentService.selectById(id);
        request.setAttribute("DEPARTMENT",department);
        try {
            request.getRequestDispatcher("/department_edit.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  编辑部门
     * /department/edit.do   /department_list.jsp
     */
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        Department department=new Department();
        department.setId(id);
        department.setName(name);
        department.setAddress(address);
        departmentService.edit(department);
        response.sendRedirect("/sm/department/list.do");
    }

    /**
     *  删除部门
     * /department/remove.do /department_list.jsp
     */
    public void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        departmentService.delete(id);
        response.sendRedirect("/sm/department/list.do");
    }
}
