package com.imooc.sm.global;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 核心控制器：
 * web容器接收请求交给-->核心控制器-->核心控制器从IOC容器中找到匹配的
 * Controller.方法并执行
 */
public class DispatcherServlet extends GenericServlet {
 private ApplicationContext applicationContext;

    public void init() throws ServletException {
        super.init();
        applicationContext=new ClassPathXmlApplicationContext("spring.xml");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        /**
         * 规定请求、Controller和方法命名都有一定规则
         * 员工               登陆
         * /staff/add.do           /login.do
         * staffController         selfController
         * add(request,response)   login(request,response)
         */
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
       //去除请求第一个字符'/'
        String path=request.getServletPath().substring(1);
        //获取请求的类名、方法名
        String className=null;
        String methodName=null;
        int index=path.indexOf('/');
        if(index !=-1){
            //员工
            className=path.substring(0,index)+"Controller";
            methodName=path.substring(index+1,path.indexOf(".do"));
        }else{
            //特殊
            className="selfController";
            methodName=path.substring(0,path.indexOf(".do"));
        }
        //反射
        Object object=applicationContext.getBean(className);
        try {
            Method method=object.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(object,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
