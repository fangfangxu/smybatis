package com.imooc.sm.controller;

import com.imooc.sm.entity.Department;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.DepartmentService;
import com.imooc.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @Autowired
    private DepartmentService departmentService;


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
        //返回所有的部门信息
        List<Department> departmentList= departmentService.getList();
        request.setAttribute("DLIST",departmentList);
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
        String sex=request.getParameter("sex");
        String idNumber=request.getParameter("idNumber");
        String info=request.getParameter("info");
        Integer did=Integer.valueOf(request.getParameter("did"));
        Date bornDate=null;
        try {
            bornDate=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Staff staff=new Staff();
        staff.setName(name);
        staff.setAccount(account);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setDid(did);
        staffService.add(staff);
        response.sendRedirect("list.do");
    }

    /**
     *  打开编辑员工页面
     * /staff/toEdit.do /staff_edit.jsp
     */
    public void toEdit(HttpServletRequest request, HttpServletResponse response){
        //返回所有的部门信息
        List<Department> departmentList= departmentService.getList();
        request.setAttribute("DLIST",departmentList);
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
        String sex=request.getParameter("sex");
        String idNumber=request.getParameter("idNumber");
        String info=request.getParameter("info");
        Integer did=Integer.valueOf(request.getParameter("did"));
        Date bornDate=null;
        try {
            bornDate=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Staff staff=staffService.selectById(id);
        staff.setName(name);
        staff.setAccount(account);
        staff.setSex(sex);
        staff.setIdNumber(idNumber);
        staff.setInfo(info);
        staff.setBornDate(bornDate);
        staff.setDid(did);
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

    /**
     * 详情
     */
    public void detail(HttpServletRequest request, HttpServletResponse response){
        Integer id=Integer.parseInt(request.getParameter("id"));
        Staff staff=staffService.selectById(id);
        request.setAttribute("STAFF",staff);
        try {
            request.getRequestDispatcher("../staff_detail.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
