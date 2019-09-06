package cn.eric.springbootdemo.controller;

import cn.eric.springbootdemo.controller.dto.PageInfo;
import cn.eric.springbootdemo.domain.Order;
import cn.eric.springbootdemo.domain.User;
import cn.eric.springbootdemo.service.CommonMqService;
import cn.eric.springbootdemo.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author Eric
 * @version 1.0
 * @ClassName: UserController
 * @Description: TODO
 * @company lsj
 * @date 2019/4/30 16:41
 **/
@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User API接口", tags = "user", description = "User API接口")
public class UserController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private CommonMqService commonMqService;

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "用户登录", notes = "用户登录接口")
    @ApiResponses({
            @ApiResponse(code = 0, message = "success"),
            @ApiResponse(code = 10001, message = "用户名错误", response = IllegalArgumentException.class),
            @ApiResponse(code = 10002, message = "密码错误")
    })
    @GetMapping(value = "/login")
    public String login(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                        @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password) throws JsonProcessingException {

        User userLog=new User();
        userLog.setName(username);
        userLog.setPassword(password);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(env.getProperty("log.user.exchange.name"));
        rabbitTemplate.setRoutingKey(env.getProperty("log.user.routing.key.name"));

        Message message= MessageBuilder.withBody(objectMapper.writeValueAsBytes(userLog)).setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.convertAndSend(message);

        return "{'username':'" + username + "', 'password':'" + password + "'}";
    }


    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "header", name = "phone", required = true, value = "手机号"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "query", name = "nickname", required = true, value = "nickname", defaultValue = "双击666"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "path", name = "platform", required = true, value = "平台", defaultValue = "PC"),
            @ApiImplicitParam(dataTypeClass = String.class, paramType = "body", name = "password", required = true, value = "密码")
    })
    @PutMapping(value = "/{platform}/regist")
    public String regist(@RequestHeader String phone, @RequestParam String nickname, @PathVariable String platform, @RequestBody String password){
        return "{'username':'" + phone + "', 'nickname':'" + nickname + "', 'platform': '" + platform + "', 'password':'"+password+"'}";
    }


    @ApiOperation(value = "用户列表", notes = "查询用户列表")
    @GetMapping(value = "/list")
    public String getUserList(PageInfo page){
        return "[{'id': "+page.getPage()+", 'username': 'zhangsan"+page.getSize()+"'}]";
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable Long id){
        return "success";
    }

    @ApiIgnore
    @RequestMapping("/ignoreApi")
    public String ignoreApi(){
        return "docs";
    }

    @ApiOperation(value = "测试抢单", notes = "测试抢单")
    @GetMapping("/orderMulitiThread")
    public String orderMulitiThread(Integer num){

        CountDownLatch countDownLatch = new CountDownLatch(1);
        for(int i=0;i<num;i++){
            new Thread(new RunThread(countDownLatch)).start();
        }
        countDownLatch.countDown();
        return "success";
    }

    @PostMapping("/createOrder")
    public String createOrderk(@RequestBody Order order){
        order.setStatus(1);
        orderService.createOrder(order);
        return "success";
    }

    private class RunThread implements Runnable{

        private final CountDownLatch latch;

        public RunThread(CountDownLatch countDownLatch) {
            this.latch = countDownLatch;
        }

        @Override
        public void run() {
            String mobile = "1" + new Random().nextInt() + new Random().nextInt();
            try {
                System.out.println("准备抢单手机号" + mobile);
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            commonMqService.sendRobbingMsgV2(mobile);
        }
    }
}


