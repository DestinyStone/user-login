package com.demo.demo002.entity;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:54
 * @Description:
 */
public class User {
    private String id;

    private String userName;

    private String password;

    /**
     * 0 正常密码登录 1 无需密码 游客身份
     */
    private Integer mode;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.mode = 0;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
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
