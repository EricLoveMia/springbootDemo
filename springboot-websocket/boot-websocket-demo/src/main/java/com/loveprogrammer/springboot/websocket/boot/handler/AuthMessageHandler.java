package com.loveprogrammer.springboot.websocket.boot.handler;

import com.loveprogrammer.springboot.websocket.boot.message.AuthRequest;
import com.loveprogrammer.springboot.websocket.boot.message.AuthResponse;
import com.loveprogrammer.springboot.websocket.boot.message.UserJoinNoticeRequest;
import com.loveprogrammer.springboot.websocket.boot.util.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

/**
 * @version 1.0.0
 * @description: 认证处理类
 * @author: eric
 * @date: 2022-11-29 14:15
 **/
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest>{

    @Override
    public void execute(WebSocketSession session, AuthRequest message) {

        if(StringUtils.isEmpty(message.getAccessToken())){
            WebSocketUtil.send(session, AuthResponse.TYPE,new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入"));
            return;
        }
        // 添加到 WebSocketUtil 中
        WebSocketUtil.addSession(session, message.getAccessToken()); // 考虑到代码简化，我们先直接使用 accessToken 作为 User

        // 判断是否认证成功。这里，假装直接成功
        WebSocketUtil.send(session, AuthResponse.TYPE, new AuthResponse().setCode(0));

        // 通知所有人，某个人加入了。这个是可选逻辑，仅仅是为了演示
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE,
                new UserJoinNoticeRequest().setNickname(message.getAccessToken())); // 考虑到代码简化，我们先直接使用 accessToken 作为 User

    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
