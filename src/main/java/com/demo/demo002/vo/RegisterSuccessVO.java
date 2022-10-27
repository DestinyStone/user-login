package com.demo.demo002.vo;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 17:09
 * @Description:
 */
public class RegisterSuccessVO {

    private String userName;
    private String encrypt;
    private String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
