package com.demo.demo002.common.handler;

import com.demo.demo002.common.tools.api.R;
import com.demo.demo002.common.tools.api.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 17:39
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public R handlerServiceException(ServiceException e) {
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e) {
        return R.fail("未知错误");
    }
}
