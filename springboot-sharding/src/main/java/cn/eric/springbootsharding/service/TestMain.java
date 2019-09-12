package cn.eric.springbootsharding.service;

import cn.eric.springbootsharding.config.ListSesrviceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: TestMain
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:09
 **/
public class TestMain {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ListSesrviceConfig.class);
        Map<String, ListService> beansOfType = context.getBeansOfType(ListService.class);
        System.out.println(beansOfType.size());
        Iterator<Map.Entry<String, ListService>> iterator = beansOfType.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, ListService> next = iterator.next();
            System.out.println("key:" + next.getKey());
            System.out.println("value:" + next.getValue());
        }

        ListService bean = context.getBean(ListService.class);
        // Mac OS X系统下的列表命令为:ls
        System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为:" + bean.showListCmd());

    }
}
