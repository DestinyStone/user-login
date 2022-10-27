package com.demo.demo002.controller;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.codec.Base64;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.demo.demo002.common.tools.CommonUtil;
import com.demo.demo002.common.tools.UserUtil;
import com.demo.demo002.common.tools.api.R;
import com.demo.demo002.common.tools.api.ServiceException;
import com.demo.demo002.entity.User;
import com.demo.demo002.store.UserStore;
import com.demo.demo002.vo.QrCodeStatusVO;
import com.demo.demo002.vo.QrCodeVO;
import com.demo.demo002.vo.RegisterSuccessVO;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 15:54
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final TimedCache<String, String> timedCache = CacheUtil.newTimedCache(1000);

    @RequestMapping("/exist")
    public R exist(@RequestParam("userName") String userName, @RequestParam(value = "password", required = false) String password) {
        User user = StringUtils.isEmpty(password) ? UserStore.getUserByUserName(userName) : UserStore.getUser(userName, password);
        if (user == null) {
            return R.data("0");
        }
        return R.data("1");
    }

    @RequestMapping("/register")
    public R registerUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        if (UserStore.getUserByUserName(userName) != null) {
            throw new ServiceException("用户名已存在");
        }

        User user = UserUtil.generateUser(userName, password);
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

    @GetMapping("/detail")
    public R<User> detail(@RequestParam("token") String token) {
        User user = UserUtil.decode(token);
        if (user == null) {
            throw new ServiceException("用户身份异常");
        }
        user.setPassword(null);
        return R.data(user);
    }

    @GetMapping("/get/qr/code")
    public R getQrCode() throws IOException {
        String uuid = UUID.randomUUID().toString();
        BufferedImage generate = QrCodeUtil.generate(CommonUtil.getUrl("/qrCodeSubmit?uuid=" + uuid), 300, 300);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(generate, "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        stream.flush();
        stream.close();
        QrCodeVO qrCodeVO = new QrCodeVO();
        qrCodeVO.setUuid(uuid);
        qrCodeVO.setValue(base64);
        return R.data(qrCodeVO);
    }

    /**
     * 查询二维码的状态
     * @param uuid
     * @return 0 未被使用 1被使用着
     */
    @GetMapping("/get/qr/code/status")
    public R getQrCodeStatus(@RequestParam("uuid") String uuid) {
        String value = timedCache.get(uuid, false);
        QrCodeStatusVO vo = new QrCodeStatusVO();

        if (StringUtils.isEmpty(value)) {
            vo.setStatus("0");
            return R.data(vo);
        }

        vo.setStatus(value);

        if ("2".equals(value)) {
            User user = UserStore.addRandomUser();
            String encrypt = UserUtil.encrypt(user);
            vo.setToken(encrypt);
        }

        return R.data(vo);
    }

    @GetMapping("/trigger/qr/code")
    public R triggerQrCodeStatus(@RequestParam("uuid") String uuid, @RequestParam("status") String status) {
        timedCache.put(uuid, status, "2".equals(status) ? 60 * 1000 : 1000);
        return R.status(true);
    }

}
