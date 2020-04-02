package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.Permission;
import com.hwua.services.impl.PermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PermissionController {
    //显示所有权限资源
    @Autowired
    private PermissionServices permissionServices;
    @GetMapping("/permissionList")
    @SystemControllerLog(description = "showAllPermission")
    public ModelAndView showAllPermission() throws Exception {
        ModelAndView md = new ModelAndView();
        List<Permission> permissions = permissionServices.queryAllPermission();
        md.addObject("permissionList",permissions);
        md.setViewName("/pages/permission-list");
        return md;
    }
    //根据id删除权限资源
    @ResponseBody
    @DeleteMapping("/deletePermission/{id}")
    @SystemControllerLog(description = "deletePermission")
    public Map<String, Object> deletePermission(@PathVariable(name = "id")String id) throws Exception {
        Integer row = permissionServices.deletePermissionById(id);
        Map<String,Object> map =  new HashMap<>();
        if(row!=0){
            map.put("info","success");
        }else{
            map.put("info","failed");
        }
        return map;
    }
    @GetMapping("/showAllPermission1")
    @SystemControllerLog(description = "showAllPermission1")
    public ModelAndView showAllPermission1(String id) throws Exception {
        ModelAndView md = new ModelAndView();
        List<Permission> permissions = permissionServices.queryAllPermission();
        md.addObject("roleid",id);
        md.addObject("permissionList",permissions);
        md.setViewName("/pages/role-permission-add");
        return md;
    }
    @ResponseBody
    @PostMapping("/addPermission")
    @SystemControllerLog(description = "addPermission")
    public Map<String,Object> addPermission(String permissionName ,String url)throws  Exception{
        Integer resualt = null;
        Map<String,Object> map =new HashMap<>();
        Permission permission = new Permission();
        permission.setPermissionName(permissionName);
        permission.setUrl(url);
        resualt = permissionServices.addPermission(permission);
        System.out.println("添加"+resualt+"条权限-----");
        if(resualt!=null&&resualt!=0){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        System.out.println("返回的map-----"+map);
        return map;
    }

}
