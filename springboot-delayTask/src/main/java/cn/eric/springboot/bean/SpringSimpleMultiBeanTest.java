package cn.eric.springboot.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName SpringSimpleMultiBeanTest
 * @Description: TODO
 * @Author YCKJ2725
 * @Date 2021/11/9
 * @Version V1.0
 **/
public class SpringSimpleMultiBeanTest {

    @Test
    public void test2() throws Exception {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-init.xml");
        SpringSimpleMultiBean bean = applicationContext.getBean("springMultiBean", SpringSimpleMultiBean.class);
        bean.say();

//		 SpringOtherBean springOtherBean = applicationContext.getBean("springOtherBean",SpringOtherBean.class);
//		 springOtherBean.say();
    }

}
