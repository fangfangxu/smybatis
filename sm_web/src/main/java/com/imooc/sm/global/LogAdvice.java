package com.imooc.sm.global;

import com.imooc.sm.entity.Log;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 1、业务日志：非登陆处理日志、非to开头方法的日志
 * 2、系统日志：业务出现异常记录系统日志
 * 3、登陆日志：login、logout
 */
@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService logService;


    /**
     * 记录操作日志
     */
    @AfterReturning("execution(* com.imooc.sm.controller.*.*(..)) " +
            "&& !execution(* com.imooc.sm.controller.SelfController.*(..))" +
            "&& !execution(* com.imooc.sm.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint) {
        Log log = new Log();
        //类名
        String moudle = joinPoint.getTarget().getClass().getSimpleName();
        //方法名
        String operation = joinPoint.getSignature().getName();
        //操作人
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        String operator = ((Staff) session.getAttribute("USER")).getAccount();
        log.setMoudle(moudle);
        log.setOperation(operation);
        log.setOperator(operator);
        log.setResult("成功");
        logService.addOperateLog(log);
    }

    /**
     * 记录系统异常日志
     *
     * @param joinPoint
     */
    @AfterThrowing(throwing = "e", pointcut = "execution(* com.imooc.sm.controller.*.*(..)) && !execution(* com.imooc.sm.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint,Throwable e) {
        Log log=new Log();
        String moudle=joinPoint.getTarget().getClass().getSimpleName();
        String operation=joinPoint.getSignature().getName();
        HttpServletRequest request=(HttpServletRequest)joinPoint.getArgs()[0];
        HttpSession session=request.getSession();
        String operator=session.getAttribute("USER")==null?null:((Staff)session.getAttribute("USER")).getAccount();
        log.setResult(e.getClass().getSimpleName());
        log.setMoudle(moudle);
        log.setOperation(operation);
        log.setOperator(operator);
        logService.addSystemLog(log);
    }

    /**
     * 记录登陆日志
     */
    @After("execution(* com.imooc.sm.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    /**
     * 记录登出日志
     */
    @Before("execution(* com.imooc.sm.controller.SelfController.logout(..))")
    public void loginout(JoinPoint joinPoint) {
        log(joinPoint);
    }

    private void log(JoinPoint joinPoint){
        Log log = new Log();
        //类名
        String moudle = joinPoint.getTarget().getClass().getSimpleName();
        //方法名
        String operation = joinPoint.getSignature().getName();
        log.setMoudle(moudle);
        log.setOperation(operation);
        //操作人
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        String operator =session.getAttribute("USER")==null?null: ((Staff) session.getAttribute("USER")).getAccount();
        if("".equals(operator)){
            operator=request.getParameter("account");
            log.setResult("失败");
        }else {
            log.setResult("成功");
        }
        log.setOperator(operator);
        logService.addLoginLog(log);
    }


}
