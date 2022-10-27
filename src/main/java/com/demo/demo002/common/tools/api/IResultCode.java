package com.demo.demo002.common.tools.api;

import java.io.Serializable;

/**
 * @Author: zhouxiaofeng
 * @Date: 2021/12/15 15:33
 * @Description:
 */
public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}
