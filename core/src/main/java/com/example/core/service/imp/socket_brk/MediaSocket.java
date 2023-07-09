package com.example.core.service.imp.socket_brk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class MediaSocket {
    @Value("${socket.port}")
    private int port; // 端口号

    @PostConstruct //@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次。
    public void socketStart(){
        //直接另起一个线程挂起Socket服务
        new Thread(this::socketServer).start();
    }

    private void socketServer() {// 启动服务器端的方法
        try {
            ServerSocket serverSocket = new ServerSocket(port);// 根据端口创建服务器
            HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool(3,1000);

            // 客户端可能有很多个
            while(true){
                Socket socket = serverSocket.accept() ; // 阻塞式的！
                System.out.println("有人上线了！！");
                // 每次收到一个客户端的socket请求，都需要为这个客户端分配一个
                // 独立的线程 专门负责对这个客户端的通信！！
                handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
            }
        } catch (IOException e) {
            System.out.println("启动服务器端出现错误："+e.getMessage());
        }
    }
}
