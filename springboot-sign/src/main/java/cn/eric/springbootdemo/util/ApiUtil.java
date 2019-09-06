package cn.eric.springbootdemo.util;

import cn.eric.springbootdemo.domain.NotRepeatSubmit;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ApiUtil
 * @Description: TODO
 * @company lsj
 * @date 2019/5/17 11:24
 **/
public class ApiUtil {


    public static String concatSignString(HttpServletRequest request){
        Map<String,String> paramterMap = new HashMap<>();

        request.getParameterMap().forEach((key,value) -> paramterMap.put(key,value[0]));

        Set<String> keySet = paramterMap.keySet();

        String[] keyArray = keySet.toArray(new String[keySet.size()]);

        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k: keyArray) {
            if(k.equals("sign")){
                continue;
            }
            if(paramterMap.get(k).trim().length() > 0){
                sb.append(k).append("=").append(paramterMap.get(k).trim()).append("&");
            }
        }
        return  sb.toString();
    }

    public static String concatSignString(Map<String,String> map){
        Map<String,String> paramterMap = new HashMap<>();
        map.forEach((key,value) -> paramterMap.put(key,value));
        // 按照key 升序排序
        Set<String> keySet = map.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k: keyArray) {
            if(k.equals("sign")){
                continue;
            }
            if(paramterMap.get(k).trim().length() > 0){
                sb.append(k).append("=").append(paramterMap.get(k).trim()).append("&");
            }
        }
        return  sb.toString();
    }

    /**
     * 获取方法上的@NotRepeatSubmit 注解
     * @author Eric
     * @date 11:47 2019/5/17
     * @params handler
     * @throws
     * @return cn.eric.springbootdemo.domain.NotRepeatSubmit
     **/
    public static NotRepeatSubmit getNotRepeatSubmit(Object handler) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            NotRepeatSubmit annotation = method.getAnnotation(NotRepeatSubmit.class);
        }

        return null;
    }
}
