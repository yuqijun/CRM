package com.hwua.annotation;

import com.hwua.pojo.Log;
import com.hwua.pojo.User;
import com.hwua.services.impl.LogServices;
import com.hwua.util.CurrenDate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
    @Autowired
    private LogServices logServices;
    //    @Pointcut("@annotation(com.hwua.annotation.SystemServiceLog)")
//    public void serviceAspect(){
//    }

    //Controller层切点
    @Pointcut("@annotation(com.hwua.annotation.SystemControllerLog)")
    public void controllerAspect(){
    }


//    public static String getServiceMethodDescription(JoinPoint joinPoint)throws Exception {
//        String targetName = joinPoint.getTarget().getClass().getName();
//        String methodName = joinPoint.getSignature().getName();
//        Object[] arguments = joinPoint.getArgs();
//        Class targetClass = Class.forName(targetName);
//        Method[] methods = targetClass.getMethods();
//        String description = "";
//        for (Method method : methods) {
//            if (method.getName().equals(methodName)) {
//                Class[] clazzs = method.getParameterTypes();
//                if (clazzs.length == arguments.length) {
//                    description = method.getAnnotation(SystemServiceLog.class).description();
//                    break;
//                }
//            }
//        }
//        return description;
//    }



    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SystemControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


    @Around(value = "controllerAspect()")
    public Object  doAroundAdvice( ProceedingJoinPoint  joinPoint) {
        Object result = null;
        Date startTime = new Date();
        Log log = new Log();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String ip =  request.getRemoteAddr();
        String url = request.getServletPath();
        try {
            Date endTime = new Date();
            Object [] args =     joinPoint.getArgs();
            result = joinPoint.proceed(args);
            log.setVisitTime(CurrenDate.getDate());
            log.setUserName(user.getUsername());
            log.setIp(ip);
            log.setUrl(url);
            log.setExecutionTime(String.valueOf(endTime.getTime()-startTime.getTime()));
            log.setMethod( getControllerMethodDescription(joinPoint));
            logServices.saveLog(log);
    } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  result;
    }
}
