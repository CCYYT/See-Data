package com.example.core.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/media")
@Component
public class MediaWebSocket {
    //用来存放每个客户端对应的Session对象。
    private static Map<String, Session> map = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        map.put(session.getId(), session);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        map.remove(session.getId());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
//        broadcast(message);
    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
    }

    /**
     * 视频推流
     * */
    public static void broadcast(byte[] bytes){
        for (Session session : map.values()) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
//            session.getAsyncRemote().sendText(Arrays.toString(bytes));
            if(session!=null){
                try {
                    session.getBasicRemote().sendText(Arrays.toString(bytes));
                } catch (IOException e) {
                    System.out.println("客户端已关闭连接一条消息未送达!");
//                    throw new RuntimeException(e);
                }
            }
        }
    }
}
