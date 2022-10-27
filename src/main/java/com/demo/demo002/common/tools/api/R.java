package com.demo.demo002.common.tools.api;

import java.io.Serializable;

/**
 * @Author: zhouxiaofeng
 * @Date: 2021/12/15 15:32
 * @Description:
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
        private boolean success;
    private T data;
    private String msg;

    private R(IResultCode resultCode) {
        this(resultCode, (T)null, resultCode.getMessage());
    }

    private R(IResultCode resultCode, String msg) {
        this(resultCode, (T)null, msg);
    }

    private R(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMessage());
    }

    private R(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private R(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = ResultCode.SUCCESS.code == code;
    }


    public static <T> R<T> data(T data) {
        return data(data, "操作成功");
    }

    public static <T> R<T> data(T data, String msg) {
        return data(200, data, msg);
    }

    public static <T> R<T> data(int code, T data, String msg) {
        return new R(code, data, data == null ? "暂无承载数据" : msg);
    }

    public static <T> R<T> success(String msg) {
        return params(ResultCode.SUCCESS, msg);
    }

    public static <T> R<T> success(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> fail(String msg) {
        return params(ResultCode.FAILURE, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R(code, (Object)null, msg);
    }

    public static <T> R<T> fail(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> fail(IResultCode resultCode, String msg) {
        return new R(resultCode, msg);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }

    /**
     * 动态参数
     * @return
     */
    public static <T> R<T> params(IResultCode code, String msg, String...val) {
        if (val.length == 0) {
            return new R<>(code, msg);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            builder.append(msg.replace(":" + (i + 1), val[i]));
        }
        return new R<>(code, builder.toString());
    }

    /**
     * 动态参数
     * @return
     */
    public static <T> R<T> successParam(IResultCode code, String msg, String...val) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            builder.append(msg.replace(":" + (i + 1), val[i]));
        }
        return new R<>(code, builder.toString());
    }


    public int getCode() {
        return this.code;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "R(code=" + this.getCode() + ", success=" + this.isSuccess() + ", data=" + this.getData() + ", msg=" + this.getMsg() + ")";
    }

    public R() {
    }
}
