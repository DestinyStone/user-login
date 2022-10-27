package com.demo.demo002.entity;

import com.demo.demo002.common.tools.UserUtil;

import java.util.UUID;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:54
 * @Description:
 */
public class User {
    private String id;

    private String userName;

    private String password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static User generateUser(String userName, String password) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUserName(userName);
        user.setPassword(UserUtil.getPasswordEncrypt(password));
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
