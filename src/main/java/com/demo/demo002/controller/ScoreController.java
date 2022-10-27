package com.demo.demo002.controller;

import com.demo.demo002.common.tools.UserUtil;
import com.demo.demo002.common.tools.api.R;
import com.demo.demo002.common.tools.api.ServiceException;
import com.demo.demo002.entity.Score;
import com.demo.demo002.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 17:22
 * @Description:
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @GetMapping("/detail")
    public R<List<Score>> detail(@RequestParam("code") String code) {
        User decode = UserUtil.decode(code);
        if (decode == null) {
            throw new ServiceException("用户身份异常");
        }
        List<Score> result = Arrays.asList(
                new Score("语文", 80.0),
                new Score("数学", 70.0),
                new Score("英语", 60.0)
        );
        return R.data(result);
    }
}
