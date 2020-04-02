package com.hwua.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex){
        System.out.println("全局异常处理方法.....");
        SysException se=null;
        if(ex instanceof SysException){
            se=(SysException)ex;
        }else{//假如收到的异常不是我们自定义的异常，我们把错误信息直接放到我们自定义的异常中即可
            String exMessage = ex.getMessage();
            se = new SysException(exMessage);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("error",se.getMessage());
        mv.setViewName("/pages/error");
        return mv;
    }
}
