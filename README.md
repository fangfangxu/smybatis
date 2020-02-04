# smybatis
项目练习：人员管理系统
一、
全路径：http://localhost:8080/sm/staff/list.do

     request.getContextPath():获取项目的根路径:http://localhost:8080/sm
     request.getServletPath(): /staff/list.do
     
     加'/'访问方式说明：
     （1）重定向（从web服务器根路径开始，自己加web相应根路径sm）：http://localhost:8080
     response.sendRedirect("/sm/toLogin.do"); 
     （2）转发（从web应用根路径开始）：http://localhost:8080/sm
     request.getRequestDispatcher("/index.jsp").forward(request, response); 
     
二、日志切面

     /**
      * 1、业务操作日志
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
    @After("execution(* com.imooc.sm.controller.*.*(..)) " +
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

     
     