package com.example.core.websocket;

import com.example.common.page.PageInfo;
import com.example.common.util.JsonUtil;
import com.example.core.domain.ws.RequestEntry;
import com.example.core.domain.ws.ResponseEntry;
import com.example.core.service.DeviceDataService;
import com.example.core.service.UserService;
import com.example.core.service.WarningRulesService;
import com.example.core.service.WarningService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/user/{userUuid}")
public class UserWebSocket {
    //用来存放每个客户端对应的Session对象。
    private static Map<String, Session> map = new ConcurrentHashMap<>();
    private UserService userService = (UserService) ServiceBeanUtil.getServiceBean("userService");
    private DeviceDataService deviceDataService = (DeviceDataService) ServiceBeanUtil.getServiceBean("deviceDataService");
    private WarningService warningService = (WarningService) ServiceBeanUtil.getServiceBean("warningService");
    private WarningRulesService warningRulesService = (WarningRulesService) ServiceBeanUtil.getServiceBean("warningRulesService");

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("userUuid") String userUuid) throws IOException {
        if(("").equals(userUuid) || userService.queryByUuid(userUuid) == null) {
            session.getBasicRemote().sendText("错误的用户");
            session.close();
            return;
        }
        map.put(userUuid, session);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session,@PathParam("userUuid") String userUuid) {
        map.remove(userUuid);
        System.out.println(map.size());
    }

    /**
     * 收到客户端消息后调用的方法
     * 获取请求信息 查询结果后 发送给前端
     *
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        RequestEntry requestEntry = (RequestEntry) JsonUtil.JsonToObject(message, RequestEntry.class);
        switch (requestEntry.getRequestDataType()){
            case "deviceData":
                session.getBasicRemote().sendText(
                        JsonUtil.ObjectToString(
                                new ResponseEntry(
                                        requestEntry.getDeviceCardName(),
                                        deviceDataService.queryByPage(requestEntry.getParam())
                                )
                        ));
                break;
            case "warning":
                session.getBasicRemote().sendText(
                        JsonUtil.ObjectToString(
                                new ResponseEntry(
                                        requestEntry.getDeviceCardName(),
                                        warningService.queryByPage(requestEntry.getParam())
                                )
                        ));
                break;
            case "warningRules":
                session.getBasicRemote().sendText(
                        JsonUtil.ObjectToString(
                                new ResponseEntry(
                                        requestEntry.getDeviceCardName(),
                                        warningRulesService.queryByPage(requestEntry.getParam())
                                )
                        ));
                break;
        }
    }

    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 通过userUuid发消息
     * */
    public static void sendMsg(String userUuid,String msg) throws IOException {
        Session session = map.get(userUuid);
        if(session == null) return;
        session.getBasicRemote().sendText(msg);
    }

//    /**
//     * 视频推流
//     * */
//    public static void broadcast(byte[] bytes){
//        for (Session session : map.values()) {
//            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
////            session.getAsyncRemote().sendText(Arrays.toString(bytes));
//            if(session!=null){
//                try {
//                    session.getBasicRemote().sendText(Arrays.toString(bytes));
//                } catch (IOException e) {
//                    System.out.println("客户端已关闭连接一条消息未送达!");
////                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
}
