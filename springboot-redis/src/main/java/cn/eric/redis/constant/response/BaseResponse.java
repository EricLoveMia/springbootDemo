package cn.eric.redis.constant.response;

import cn.eric.redis.constant.enums.StatusCode;


/**
 * @author eric
 * @date 2021/8/3
 */
public class BaseResponse<T> {
    /**
     * 状态码
     **/
    private Integer code;
    /**
     * 描述信息
     **/
    private String msg;
    /**
     * 响应数据-采用泛型表示可以接受通用的数据类型
     **/
    private T data;

    /**
     * 重载的构造方法一
     **/
    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 重载的构造方法二
     **/
    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    /**
     * 重载的构造方法三
     **/
    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
