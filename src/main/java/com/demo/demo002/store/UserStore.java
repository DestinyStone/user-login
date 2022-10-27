package com.demo.demo002.store;

import cn.hutool.core.util.RandomUtil;
import com.demo.demo002.common.tools.UserUtil;
import com.demo.demo002.entity.User;

import java.util.Vector;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:55
 * @Description:
 */
public class UserStore {
    private static final Vector<User> userList = new Vector();

    public static void addUser(User user) {
        userList.add(user);
    }

    public static User addRandomUser() {
        String userName = "";
        while (true) {
            userName = RandomUtil.randomString(10);
            User user = getUserByUserName(userName);
            if (user != null) {
                continue;
            }

            User insertUser = UserUtil.generateGuest(userName);
            addUser(insertUser);
            return insertUser;
        }

    }

    public static User getUserByUserName(String userName) {
        for (User user : userList) {
            if (userName.equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

    public static User getUser(String userName, String password) {
        for (User user : userList) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
