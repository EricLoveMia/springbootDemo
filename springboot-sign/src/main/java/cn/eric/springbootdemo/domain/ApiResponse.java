package cn.eric.springbootdemo.domain;

import cn.eric.springbootdemo.util.ApiUtil;
import cn.eric.springbootdemo.util.MD5Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ApiResponse
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 11:12
 **/
@Data
@Slf4j
public class ApiResponse<T> {

    private ApiResult result;

    private T data;

    private String sign;

    public static <T> ApiResponse success(T data) {
        return response(ApiCodeEnum.SUCCESS.getCode(), ApiCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> ApiResponse response(String code, String msg, T data) {
        ApiResult result = new ApiResult(code, msg);
        ApiResponse response = new ApiResponse();
        response.setResult(result);
        response.setData(data);
        String sign = signData(data);
        response.setSign(sign);
        return response;
    }

    private static <T> String signData(T data) {
        // TODO 查询key
        String key = "1234";
        Map<String, String> responseMap = null;

        try {
            responseMap = getFields(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String urlComponet = ApiUtil.concatSignString(responseMap);
        String signature = urlComponet + "key" + key;
        String sign = MD5Util.encode(signature);
        return sign;
    }

    /**
     * @return java.util.Map<java.lang.String   ,   java.lang.String>
     * @throws
     * @author Eric
     * @date 11:45 2019/5/22
     * @params data 反射的对象 获取对象的字段名和值
     **/
    public static Map<String, String> getFields(Object data) throws IllegalAccessException {
        if (data == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        Field[] declaredFields = data.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);

            String name = declaredField.getName();
            Object value = declaredField.get(data);
            if (value != null) {
                map.put(name, value.toString());
            }
        }
        return map;
    }
}
