package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.User;
import com.hwua.services.impl.UserServices;
import com.hwua.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserServices userServices;
    @PostMapping("/registerUser")
    @ResponseBody
    @SystemControllerLog(description = "registerUser")
    public  Map<String, Object>  registerUser(String userName,String email,String password,String phoneNum,Integer status) throws Exception {
        Map<String,Object>  map = new HashMap<>();
        Integer row = 0;
        User user = new User(null,email,userName,password,phoneNum,status,null);
        user.setPassword(String.valueOf(Md5Util.md5hash(userName,password)));
        row = userServices.registerUser(user);
        if(row==null ||row==0){
            map.put("info","failed");
        }else{
            map.put("info","success");
        }
        System.out.println(map.toString());
        return map;
    }
}
