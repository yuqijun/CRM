package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.services.impl.Role_PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
@Controller
public class Role_PermissionController {
    @Autowired
    private Role_PermissionService role_permissionService;
    @GetMapping("/roleAddPermission")
    @SystemControllerLog(description = "roleAddPermission")
    @ResponseBody
    public Map<String,Object> roleAddPermission(String roleid, String [] permissionid)throws  Exception{
        System.out.println("roleid"+roleid+"------"+"permissionid"+permissionid);
        Map<String,Object> map = new HashMap<>();
        Integer result = role_permissionService.addRole_Permission(roleid,permissionid);
        if(result!=null&&result!=0){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        return map;
    }
}
