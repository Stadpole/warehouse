package com.bupt.aop;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bupt.entity.LoginLog;
import com.bupt.service.LoginLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect // 将一个java类定义为切面类
@Order(-1)//如果有多个aop,这里可以定义优先级,越小级别越高
public class LogAop {
    @Autowired
    private LoginLogService loginLogService;

    LoginLog log=new LoginLog();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    /**
     * 定义一个切入点,注意这里只定义controller包
     */
    @Pointcut("execution(public * com.bupt.loginController..HomeController.*(..))")
    public void log() {

    }

    /**
     * 在切入点开始处切入内容
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        startTime.set(System.currentTimeMillis());
         if(log.getId()!=null){
             log.setId(null);
         }

        // 接收到请求，记录请求内容
        logger.info("开始记录日志--------------LogAop.doBefore()");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        log.setUrl(request.getRequestURL().toString());
        log.setMethod(request.getMethod());
        log.setIp(request.getRemoteAddr());

    }


    /**
     * 在切入点前后切入内容，并自己控制何时执行切入点自身的内容
     * 可以实现拦截器的功能
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint pjp){
        logger.info("LogAop.doAround()");
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            logger.info("exception: ", e);
            throw new RuntimeException("unknown error");
        }
    }

    /**
     * 用来处理当切入内容部分抛出异常之后的处理逻辑
     */
    @AfterThrowing("log()")
    public void doAfterThrowing(){
        logger.info("LogAop.doAfterThrowing()");
    }

    /**
     * 在切入点结尾处切入内容
     */
    @After("log()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("LogAop.doAfter()");
    }

    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     * @param joinPoint
     */
    @AfterReturning("log()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        logger.info("LogAop.doAfterReturning()");
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        HttpSession session=request.getSession();
        Object obj=session.getAttribute("userId");
        // 记录下请求内容
        log.setYhId(obj.toString());
        log.setTimeConsuming(System.currentTimeMillis() - startTime.get());
        loginLogService.save(log);
    }

}