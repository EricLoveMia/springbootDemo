package cn.eric.springbootdemo.controller.dto;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * 通用成功编码
     */
    public static final int CODE_SUCCESS = 200;
    /**
     * 通用错误编码
     */
    public static final int CODE_ERROR = 500;
    /**
     * 登陆错误  用户名密码错误
     */
    public static final int CODE_LOGIN_ERROR = 501;
    /**
     * 登陆超时
     */
    public static final int CODE_LOGIN_TIMEOUT = 502;
    /**
     * 没有登陆
     */
    public static final int CODE_LOGIN_NOSESSION = 503;

    public R() {
        put("code", CODE_SUCCESS);
        put("msg", "操作成功");
    }

    public static R error() {
        return error(CODE_ERROR, "操作失败");
    }

    public static R error(String msg) {
        return error(CODE_ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object object) {
        R r = new R();
        r.put("data", object);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
