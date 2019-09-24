package cn.eric.springbootdemo.config;

import cn.eric.springbootdemo.domain.Payload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSONObject;
import org.thymeleaf.util.DateUtils;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author Eric
 * @version 1.0
 * @ClassName: WebSocketServer
 * @Description: TODO
 * @company lsj
 * @date 2019/9/12 16:55
 **/
/**
 * ServerEndpoint 用户定义客户端连接的地址, 可以在路径上指定路径参数
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {

    /** 在线连接数量 */
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /** 当前连接的用户id */
    private String userId;


    /**
     * 连接成功建立时调用该方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        sessionMap.put(userId, session);
        this.userId = userId;
        int currentOnlineCount = onlineCount.incrementAndGet();
        log.info("{} 连接创建成功，当前用户id为{}, 当前在线人数{}", now() , userId, currentOnlineCount);

        JSONObject succesJson = new JSONObject();
        succesJson.put("from", "服务器");
        succesJson.put("to", userId);
        succesJson.put("content", "WebSocket服务器连接成功！");

        sendMessage(session, succesJson.toJSONString());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        sendMessage(session, message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        log.error(throwable.toString());
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.remove(this.userId);
        int currentOnlineCount = onlineCount.decrementAndGet();

        log.info("用户:{} 退出连接，当前连接数为：{}", this.userId, currentOnlineCount);
    }


    /**
     * 向指定会话发送消息
     * @param session 当前会话session
     * @param message 消息内容
     */
    public void sendMessage(Session session, String message) {
        Payload payload = JSONObject.parseObject(message, Payload.class);
        String toUserId = payload.getTo();
        Session targetSession = sessionMap.get(toUserId);
        if (targetSession == null) {
            log.info("目标用户{} 已退出连接", toUserId);
            return;
        }

        try {
            targetSession.getBasicRemote().sendText(now() + " " + message);
        } catch (IOException e) {
            log.error("发送消息失败, 失败原因为：{}", e);
        }
    }

    /**
     * 向指定用户发送消息
     * @param userId 用户id
     * @param message 消息
     */
    public void sendMessage(String userId, String message) {
        Session session = sessionMap.get(userId);
        if (session != null) {
            JSONObject succesJson = new JSONObject();
            succesJson.put("from", "服务器");
            succesJson.put("to", userId);
            succesJson.put("content", message);

            this.sendMessage(session, succesJson.toJSONString());
        } else {
            log.info("用户{} 已退出连接，无法发送消息", userId);
        }
    }

    private String now() {
        return DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
    }
}

