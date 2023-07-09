package com.example.core.websocket;

import com.example.common.result.Code;
import com.example.core.domain.chat.Msg;
import com.example.core.domain.chat.SocketMsg;
import com.example.core.domain.chat.SystemMsg;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/websocket/{nickname}")
@Component
public class MyWebSocket {
    //用来存放每个客户端对应的Session对象。
    private static Map<String,Session> map = new ConcurrentHashMap<>();
    //用来解析json
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        String nickname = session.getRequestParameterMap().get("nickname").get(0);
//        String nickname = session.getPathParameters().get("nickname");
        map.put(nickname, session);
        SystemMsg result = new SystemMsg(){{
            setCode(Code.MESSAGE_USER_UPDATA);
            setIsSystem(true);
            setMsg(nickname+"上线了,当前在线人数为："+map.size());
            setUsers(map.keySet());
        }};
        broadcast(result);

    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        String nickname = session.getRequestParameterMap().get("nickname").get(0);
        map.remove(nickname);
        SystemMsg result = new SystemMsg(){{
            setCode(Code.MESSAGE_USER_UPDATA);
            setIsSystem(true);
            setMsg(nickname+"下线了,当前在线人数为："+map.size());
            setUsers(map.keySet());
        }};
        broadcast(result);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {

        //从客户端传过来的数据是json数据，所以这里使用jackson进行转换为SocketMsg对象，
        // 然后通过socketMsg的type进行判断是单聊还是群聊，进行相应的处理:

        String nickname = session.getRequestParameterMap().get("nickname").get(0);
        try {
             Set<Session> sessions = session.getOpenSessions();  //获取所有连接的session对象
            //将客户端发来的json字符串装换为SocketMsg对象
            SocketMsg socketMsg = objectMapper.readValue(message, SocketMsg.class);
            socketMsg.setFromUser(nickname);//发送者.
            socketMsg.setCode(Code.MESSAGE_OK);
            if(socketMsg.getType() == 1){
                //单聊.需要找到发送者和接受者.
                Session fromSession = map.get(nickname);
                Session toSession = map.get(socketMsg.getToUser());
                socketMsg.setIsSystem(false);
                //发送给接受者.
                if(toSession != null){
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(objectMapper.writeValueAsString(socketMsg));
                    //发送者与接收者不是同一个
                    if(fromSession != toSession){
                        toSession.getAsyncRemote().sendText(objectMapper.writeValueAsString(socketMsg));
                    }
                }else{
                    SystemMsg systemMsg = new SystemMsg(){{
                        setCode(Code.MESSAGE_USER_UNKNOW);
                        setIsSystem(true);
                        setMsg("系统消息：对方不在线或者您输入的用户名不对");
                    }};
                    //发送给发送者.
                    fromSession.getAsyncRemote().sendText(objectMapper.writeValueAsString(systemMsg));
                }
            }else{
                //群发消息
                broadcast(socketMsg);
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        SystemMsg systemMsg = new SystemMsg(){{
            setMsg("发生错误！！");
            setIsSystem(true);
            setCode(Code.MESSAGE_ERR);
        }};
        try {
            session.getAsyncRemote().sendText(objectMapper.writeValueAsString(systemMsg));
            System.out.println("发生错误");
            error.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 群发自定义消息
     * */
    public static void broadcast(Msg result){
        try {
            // 将Map对象转换为Json字符串
            String JsonMsg = objectMapper.writeValueAsString(result);
            for (Session session : map.values()) {
                //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
                session.getAsyncRemote().sendText(JsonMsg);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
