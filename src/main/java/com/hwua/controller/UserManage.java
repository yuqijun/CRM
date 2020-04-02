package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.User;
import com.hwua.services.impl.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserManage {
    @Autowired
    private UserServices userServices;
    @ResponseBody
    @GetMapping("/userlist" )
    @SystemControllerLog(description = "queryAllUser")
    public List<User>  queryAllUser( HttpServletRequest request) throws Exception {
//        List<User> userlist = userServices.queryAllUser();
        return  userServices.queryAllUser();
    }
}
