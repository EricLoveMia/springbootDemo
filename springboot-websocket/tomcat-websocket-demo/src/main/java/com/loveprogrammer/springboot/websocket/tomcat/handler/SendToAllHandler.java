package com.loveprogrammer.springboot.websocket.tomcat.handler;

import com.loveprogrammer.springboot.websocket.tomcat.message.SendResponse;
import com.loveprogrammer.springboot.websocket.tomcat.message.SendToAllRequest;
import com.loveprogrammer.springboot.websocket.tomcat.message.SendToUserRequest;
import com.loveprogrammer.springboot.websocket.tomcat.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {

    @Override
    public void execute(Session session, SendToAllRequest message) {
        // 这里，假装直接成功
        SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session, SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }

}
