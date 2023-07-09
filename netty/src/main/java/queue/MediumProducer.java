package queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MediumProducer {
    private static String EXCHANGE_NAME = "medium_exchange";
    private static String routingKey = "medium.video.original";
    private static Connection connection;
    private static Channel channel;

    static {
        try {
            connection = RabbitUtil.getConnection();
            channel = connection.createChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public static void publishMsg(String msg) throws IOException {
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());
    }
    public static void publishMsgByBytes(byte[] bytes) throws IOException {
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,bytes);
    }

    public static void closeChannelAndConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
