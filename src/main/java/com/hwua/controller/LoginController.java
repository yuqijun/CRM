package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.User;
import com.hwua.services.impl.UserServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/login")
    public String login(User user, HttpSession session) throws Exception {
        if(user == null){
            return "login";
        }
        //创建subject对象
        Subject subject = SecurityUtils.getSubject();
        //获取传进来的账号密码封装成token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        User user2 ;
        try{
            //使用subject对象将token交给 SecurityManager  进行验证
            subject.login(token);
            user2= (User)subject.getPrincipal();
            session.setAttribute("user",user);
        }catch(UnknownAccountException ua){
            //不存在账号
            return "login";
        }catch(IncorrectCredentialsException ic){
            //账号密码不正确
            return "login";
        }catch(LockedAccountException la){
            //账号被锁定
            return "login";
        }
        return "/pages/main";
    }
}
