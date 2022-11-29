package com.loveprogrammer.springboot.websocket.tomcat.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loveprogrammer.springboot.websocket.tomcat.message.AuthRequest;
import com.loveprogrammer.springboot.websocket.tomcat.message.Message;
import com.loveprogrammer.springboot.websocket.tomcat.util.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.loveprogrammer.springboot.websocket.tomcat.handler.MessageHandler;
import org.springframework.util.CollectionUtils;

/**
 * @version 1.0.0
 * @description: 定义 Websocket 服务的端点（EndPoint）
 * @author: eric
 * @date: 2022-11-29 10:19
 **/
@Controller
@ServerEndpoint("/")
public class WebsocketServerEndpoint implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 消息类型与 MessageHandler 的映射
     *
     * 注意，这里设置成静态变量。虽然说 WebsocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebsocketServerEndpoint Bean 。
     */
    private static final Map<String, MessageHandler> HANDLERS = new HashMap<>();

    @OnOpen
    public void OnOpen(Session session, EndpointConfig config){
        logger.info("[onOpen][session({}) 接入]", session);
        List<String> accessList = session.getRequestParameterMap().get("accessToken");
        String accessToken = !CollectionUtils.isEmpty(accessList) ? accessList.get(0) : null;
        AuthRequest authRequest = new AuthRequest().setAccessToken(accessToken);

        MessageHandler<AuthRequest> messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if (messageHandler == null) {
            logger.error("[onOpen][认证消息类型，不存在消息处理器]");
            return;
        }
        messageHandler.execute(session,authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        logger.info("[onOpen][session({}) 接收到一条消息({})]", session, message);

        JSONObject jsonObject = JSON.parseObject(message);
        String type = jsonObject.getString("type");

        MessageHandler messageHandler = HANDLERS.get(type);
        if (messageHandler == null) {
            logger.error("[onMessage][消息类型({}) 不存在消息处理器]", type);
            return;
        }

        // 解析消息
        Class<? extends Message> messageClass = this.getMessageClass(messageHandler);

        // 处理消息
        Message messageObj = JSON.parseObject(jsonObject.getString("body"), messageClass);
        messageHandler.execute(session, messageObj);
    }

    private Class<? extends Message> getMessageClass(MessageHandler messageHandler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过
        Class<?> aClass = AopProxyUtils.ultimateTargetClass(messageHandler);
        // 获得接口的 Type 数组
        Type[] genericInterfaces = aClass.getGenericInterfaces();
        Class<?> superclass = aClass.getSuperclass();
        // 此处，是以父类的接口为准
        while (0 == genericInterfaces.length && Objects.nonNull(superclass)) {
            genericInterfaces = superclass.getGenericInterfaces();
            superclass = aClass.getSuperclass();
        }
        // 遍历 interfaces 数组
        for (Type type : genericInterfaces) {
            // 要求 type 是泛型参数
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                // 要求是 MessageHandler 接口
                if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                    // 取首个元素
                    if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                        return (Class<Message>) actualTypeArguments[0];
                    } else {
                        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", messageHandler));
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", messageHandler));
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, closeReason);
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> HANDLERS.put(messageHandler.getType(),messageHandler));
        logger.info("[afterPropertiesSet][消息处理器数量：{}]", HANDLERS.size());
    }
}
