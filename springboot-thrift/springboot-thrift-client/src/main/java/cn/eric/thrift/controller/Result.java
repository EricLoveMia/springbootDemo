package cn.eric.thrift.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YCKJ2725
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * 通用成功编码
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 通用错误编码
     */
    public static final int CODE_ERROR = 1;
    /**
     * 登陆错误  用户名密码错误
     */
    public static final int CODE_LOGIN_ERROR = 201;
    /**
     * 登陆超时
     */
    public static final int CODE_LOGIN_TIMEOUT = 202;
    /**
     * 没有登陆
     */
    public static final int CODE_LOGIN_NOSESSION = 200;

    public Result() {
        put("code", CODE_SUCCESS);
        put("msg", "操作成功");
    }

    public static Result error() {
        return error(CODE_ERROR, "操作失败");
    }

    public static Result error(String msg) {
        return error(CODE_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(String msg) {
        Result r = new Result();
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(Object o) {
        Result r = new Result();
        r.put("code", CODE_SUCCESS);
        r.put("data", o);
        return r;
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
