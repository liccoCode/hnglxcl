package controllers;

import models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 用户登陆限制
 * User: wyattpan
 * Date: 1/13/12
 * Time: 12:42 AM
 */
public class Login extends Secure.Security {

    /**
     * 登陆的用户 Cache
     */
    private static final Map<String, User> USER_CACHE = new ConcurrentHashMap<>();

    /**
     * 登陆
     */
    static boolean authenticate(String username, String password) {
        //全部转为小写
        username = username.toLowerCase();
        /*
         * 1. 判断是否拥有此用户; 使用公司邮箱 @easyacceu.com
         * 2. 判断用户登陆是否正常
         */
        User user = User.findByUserName(username);
        if(user == null) return false;
        boolean iscorrect = user.authenticate(password);
        return iscorrect;
    }


}
