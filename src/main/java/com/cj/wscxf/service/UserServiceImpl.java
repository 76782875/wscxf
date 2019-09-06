package com.cj.wscxf.service;

import com.cj.wscxf.model.User;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(serviceName="userService",//对外发布的服务名
        targetNamespace="http://service.wscxf.cj.com",//指定你想要的名称空间，通常使用包名反转
        endpointInterface="com.cj.wscxf.service.UserService")  // 接口地址
@Component
public class UserServiceImpl implements UserService{

    private Map<String, User> userMap = new HashMap<String, User>();

    public UserServiceImpl() {
        System.out.println("向实体类插入数据");
        User user = new User();
        user.setId(111);
        user.setUserName("test1");

        userMap.put(user.getId()+"", user);

        user = new User();
        user.setId(112);
        user.setUserName("test2");
        userMap.put(user.getId()+"", user);

        user = new User();
        user.setId(113);
        user.setUserName("test3");
        userMap.put(user.getId()+"", user);
    }

    @Override
    public String getUserName(String userId) {
        return "userId为：" +userMap.get( userId).getUserName();
    }

    @Override
    public User getUser(String userId) {
        System.out.println("userMap是:"+userMap);
        return userMap.get(userId);
    }

}