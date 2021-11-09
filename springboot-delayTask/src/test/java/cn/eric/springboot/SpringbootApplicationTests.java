package cn.eric.springboot;

import cn.eric.springboot.bean.SpringSimpleMultiBean;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {SpringbootApplication.class})
@RunWith(SpringRunner.class)
public class SpringbootApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Before
    public void before() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    public void test2() throws Exception {

        SpringSimpleMultiBean bean = applicationContext.getBean("springMultiBean", SpringSimpleMultiBean.class);
        bean.say();

//		 SpringOtherBean springOtherBean = applicationContext.getBean("springOtherBean",SpringOtherBean.class);
//		 springOtherBean.say();
    }
}
