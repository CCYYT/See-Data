package util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitUtil {
    private static  String host;
    private static int port;
    private static String virtualHost;
    private static String userName;
    private static String password;
    private static ConnectionFactory connectionFactory;

    static {
        host = "172.20.10.9";
        port = 5672;
        virtualHost = "netty_msg";
        userName = "netty";
        password = "Qq000000";
        connectionFactory = new ConnectionFactory(){{
            setHost(host);
            setPort(port);
            setVirtualHost(virtualHost);
            setUsername(userName);
            setPassword(password);
        }};
    }


    public static ConnectionFactory getConnectionFactory(){
        return connectionFactory;
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }
}
