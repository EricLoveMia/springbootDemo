package cn.eric.springbootsharding.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: HelloControllerTests
 * @Description: TODO
 * @company lsj
 * @date 2019/4/19 18:18
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloControllerTests {

    private MockMvc mvc;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setUp() throws Exception {
        //mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        mvc = MockMvcBuilders.standaloneSetup(new OrderController()).build();
    }

    @Test
    public void getHello() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/hello/world").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }

    @Test
    public void getShardingInsert() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/api/order/insert").accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }
}
