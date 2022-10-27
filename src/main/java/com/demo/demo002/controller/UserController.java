package com.demo.demo002.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.demo.demo002.common.tools.CommonUtil;
import com.demo.demo002.common.tools.JwtUtil;
import com.demo.demo002.common.tools.UserUtil;
import com.demo.demo002.common.tools.api.R;
import com.demo.demo002.common.tools.api.ServiceException;
import com.demo.demo002.entity.User;
import com.demo.demo002.store.UserStore;
import com.demo.demo002.vo.RegisterSuccessVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:54
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/register")
    public R registerUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (UserStore.getUserByUserName(userName) != null) {
            throw new ServiceException("用户名已存在");
        }

        User user = User.generateUser(userName, password);
        UserStore.addUser(user);
        String encrypt = UserUtil.encrypt(user);

        RegisterSuccessVO vo = new RegisterSuccessVO();
        vo.setUserName(userName);
        vo.setEncrypt(encrypt);
        vo.setRemark("注册成功");
        return R.data(encrypt);
    }

    @GetMapping("/login")
    public R login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        User user = UserStore.getUser(userName, password);
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }
        String encrypt = UserUtil.encrypt(user);
        RegisterSuccessVO vo = new RegisterSuccessVO();
        vo.setUserName(userName);
        vo.setEncrypt(encrypt);
        vo.setRemark("登录成功");
        return R.data(encrypt);
    }

    @GetMapping("/get/qr/code")
    public R getQrCode() throws IOException {
        BufferedImage generate = QrCodeUtil.generate(CommonUtil.getUrl("/qrCodeSubmit"), 300, 300);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(generate, "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        stream.flush();
        stream.close();
        return R.data(base64);
    }

    @GetMapping("/get/qr/code/status")
    public R getQrCodeStatus() {
        return null;
    }

    @GetMapping("/trigger/qr/code")
    public R triggerQrCodeStatus() {
        return null;
    }

}
