package com.hwua.realm;


import com.hwua.pojo.Role;
import com.hwua.pojo.User;
import com.hwua.services.impl.UserServices;
import com.hwua.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserServices userServices;
    /**
     * 对当前登录用具进行授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        //创建SimpleAuthorizationInfo 对象 给对象中设置相关的角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //给当前登陆的用户赋予指定的角色
//        authorizationInfo.addRole("admin");
        List<Role> roles = null;
        try {
            roles = userServices.queryUserById(user.getId()).getRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(Role permission : roles){
            authorizationInfo.addRole(permission.getRoleName());
        }
        System.out.println("当前用户具有的角色"+authorizationInfo.getRoles());
        return authorizationInfo;
    }

    /**
     * 对当前登录用具进行身份验证
     * 此方法什么时候调用，调用调用subject对象调用login方法的时候，底层调用的是securityManager对象的login方法，此login方法最终调用realm方法中的doGetAuthenticationInfo 方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = null;
        String username = (String)token.getPrincipal();
        try {
            //从数据库中找到的用户信息
             user = userServices.queryUserByName(username);
//            user.setPassword(String.valueOf(Md5Util.md5hash(user.getUsername(),user.getPassword())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(user == null){
            //如果数据库中不存在该用户则抛出异常
            throw new UnknownAccountException();
        }
        //得到salt  salt要不一样
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo authenticationInfo =  new SimpleAuthenticationInfo(user,user.getPassword(),salt,super.getName());
        return authenticationInfo;   //把此对象返回给shiro，shiro会拿这个对象取和你subject传过来的token进行密码比对
    }
}
