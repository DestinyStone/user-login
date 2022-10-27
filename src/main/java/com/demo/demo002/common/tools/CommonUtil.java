package com.demo.demo002.common.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 21:01
 * @Description:
 */
@Component
public class CommonUtil {

    private static String address;
    private static String port;

    public static String getUrl(String suffix) {
        return "http://" + address + ":" + port + "/" + suffix;
    }

    @Value("${config.address}")
    public void setAddress(String address) {
        CommonUtil.address = address;
    }

    @Value("${server.port}")
    public void setPort(String port) {
        CommonUtil.port = port;
    }
}
