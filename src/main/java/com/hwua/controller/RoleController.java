package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.pojo.Role;
import com.hwua.services.impl.RoleServices;

import com.hwua.services.impl.User_RoleServices;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    private User_RoleServices user_roleServices;
    @Autowired
    private RoleServices roleServices;
    @ResponseBody
    @GetMapping("/showAllRole")
    @SystemControllerLog(description = "showAllRole")
    public List<Role> showAllRole() throws Exception {
        return roleServices.queryAllRole();
    }

    @GetMapping("/showAllRole2")
    //从添加角色的页面进来
    @SystemControllerLog(description = "showAllRole2")
    public ModelAndView showAllRole2(String id,String [] roleids) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("roles",roleServices.queryAllRole());
        mv.addObject("userID",id);
        //跳转到角色添加页面
        mv.setViewName("/pages/user-role-add");
        return mv;
    }
    @ResponseBody
    @GetMapping("/add_role")
    @SystemControllerLog(description = "add_role")
    public  Map<String, Object> add_role(HttpServletRequest request, Integer [] roleids, String userid) throws Exception {
        Integer resualt = user_roleServices.insertUser_RoleByUserId(userid,roleids);
        Map<String ,Object> map = new HashMap<>();
        if(resualt!=null&&resualt!=0){
            map.put("info","success");
            map.put("resualt",resualt);
        }else{
            map.put("info","fail");
        }
        return map;
    }
    @ResponseBody
    @GetMapping("/deleteRoleById")
    @SystemControllerLog(description = "deleteRoleById")
    public  Map<String,Object> deleteRoleById(String id)throws Exception{
        Map<String,Object> map = new HashMap<>();
        Integer result = roleServices.deleteRoleById(id);
        System.out.println("删除"+result+"个用户");
        if(result!=null&&result!=0){
            map.put("info","success");
        }else{
            map.put("info","fail");
        }
        System.out.println("返回的map11321"+map);
        return map;
    }
    @ResponseBody
    @PostMapping("/addrole")
    @SystemControllerLog(description = "addrole")
    public Map<String,Object>addrole(String roleName,String roleDesc)throws  Exception{
        Role role=new Role();
        role.setRoleName(roleName);
        role.setRoleDesc(roleDesc);
        Map<String,Object> map = new HashMap<>();
        Integer resualt = roleServices.addRole(role);
        if(resualt!=null&&resualt!=0){
            map.put("info","success");
        }else{
            map.put("info","fali");
        }

        return map;
    }

}

