package com.hwua.configuration;

import com.hwua.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public Realm userRealm (CredentialsMatcher credentialsMatcher) throws Exception{
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);   //给realm设置  密码加密器
        return  userRealm;
    }

    @Bean
    public SessionsSecurityManager securityManager(Realm userRealm) throws Exception{
        SessionsSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 配置一个ShiroFilterFactory工厂,拦截url请求的
     * @return
     * @throws Exception
     */
@Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SessionsSecurityManager securityManager) throws Exception{
        //创建shiro过滤链对象
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        //当认证失败，跳转到login.jsp页面
        filterFactoryBean.setLoginUrl("/login.jsp");
        filterFactoryBean.setUnauthorizedUrl("/unauthorized.jsp");
        //过滤链需要过滤的网页url集合
        Map<String,String> shiroFilterChainMap = new LinkedHashMap();
        //此过滤器代表认证过滤器，也就是说，此url必须登录后才能访问
        shiroFilterChainMap.put("/xxxxxx/**","authc");
        //此过滤器代表不用认证，直接可以访问
        shiroFilterChainMap.put("/xxxx/xxxxx","anon");
        //此路径的话直接进行登出,回到LoginUrl 指定的页面
        shiroFilterChainMap.put("/xxxxx/xxxxxxx","logout");
        shiroFilterChainMap.put("/permissionList/**","roles[superAdmin]");
        // 注册拦截器
        filterFactoryBean.setFilterChainDefinitionMap(shiroFilterChainMap);
    return filterFactoryBean;
    }


    //配置一个CredentialsMatcher类型的加密对象
    //当调用自定义 realm 验证方法的时候会把表单传过来的密码交给我们的 加密匹配器对象进行加密，加密好以后在和数据库中的密码进行比较
    @Bean
    public CredentialsMatcher credentialsMatcher()  throws  Exception{
        HashedCredentialsMatcher hashedCredentialsMatcher =   new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);//选择md5 加密方式
        hashedCredentialsMatcher.setHashIterations(1024);   //设置加密的次数
        return hashedCredentialsMatcher;
    }
}
