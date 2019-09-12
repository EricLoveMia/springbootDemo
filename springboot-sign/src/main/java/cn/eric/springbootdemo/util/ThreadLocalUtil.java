package cn.eric.springbootdemo.util;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: ThreadLocalUtil
 * @Description: TODO
 * @company lsj
 * @date 2019/5/22 13:30
 **/
public class ThreadLocalUtil<T> {

    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new HashMap<>(4);
        }
    };

    public static Map<String, Object> getThreadLocal() {
        return threadLocal.get();
    }

    public static <T> T get(String key) {
        Map map = threadLocal.get();
        return (T) map.get(key);
    }

    public static <T> T get(String key, T defaultValue) {
        Map map = threadLocal.get();
        return (T) map.get(key) == null ? defaultValue : (T) map.get(key);
    }

    public static void set(String key, Object value) {
        Map map = threadLocal.get();
        map.put(key, value);
    }

    public static void set(Map<String, Object> keyValueMap) {
        Map map = threadLocal.get();
        map.putAll(keyValueMap);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static <T> Map<String, T> fetckVarsPrifix(String prefix) {
        Map<String, T> vars = new HashMap<>();
        if (prefix == null) {
            return vars;
        }
        Map map = threadLocal.get();
        Set<Map.Entry> set = map.entrySet();

        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    vars.put((String) key, (T) entry.getValue());
                }
            }
        }
        return vars;
    }

    public static <T> T remove(String key) {
        Map map = threadLocal.get();
        return (T) map.remove(key);
    }

    public static void clear(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return;
        }
        List<String> removeKeys = new ArrayList<>();
        Map map = threadLocal.get();
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            if (key instanceof String) {
                if (((String) key).startsWith(prefix)) {
                    removeKeys.add((String) key);
                }
            }
        }
        for (String key : removeKeys) {
            map.remove(key);
        }
    }

}



























