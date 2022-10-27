package com.demo.demo002.common.tools;

import com.alibaba.fastjson.JSON;
import com.demo.demo002.common.tools.api.ServiceException;
import com.demo.demo002.entity.User;
import io.jsonwebtoken.Claims;

import java.util.HashMap;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:59
 * @Description:
 */
public class UserUtil {

    public static final String ENCRYPT_KEY = "user";

    public static User decode(String encrypt) {
        User user;

        try {
            if (JwtUtil.isExpired(encrypt)) {
                throw new ServiceException("签名已过期");
            }

            if (!JwtUtil.verify(encrypt)) {
                throw new ServiceException("签名异常");
            }

            Claims claim = JwtUtil.getClaim(encrypt);
            Object o = claim.get(ENCRYPT_KEY);
            user = JSON.parseObject(o.toString(), User.class);
        }catch (ServiceException e) {
            throw e;
        }catch (Exception e) {
            throw new ServiceException("签名异常");
        }


        return user;
    }

    public static String encrypt(User user) {
        HashMap<String, Object> map = new HashMap<>();
        map.put(ENCRYPT_KEY, JSON.toJSONString(user));
        return JwtUtil.generate(map);
    }

    public static String getPasswordEncrypt(String password) {
        return password;
    }
}
