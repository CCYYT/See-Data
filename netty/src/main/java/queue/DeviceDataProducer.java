package queue;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.common.util.JsonUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DeviceDataProducer {
    private static String EXCHANGE_NAME = "device";
//    private static String routingKey = "device.data";
    private static Connection connection;
    private static Channel channel;

    static {
        try {
            connection = RabbitUtil.getConnection();
            channel = connection.createChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    // 发送DeviceData类型的数据
    public static void publishMsgByDeviceData(DeviceData deviceData) throws Exception {
        publishMsgByObject("device.data",deviceData);
    }

    // 发送DeviceCustomData类型的数据
    public static void publishMsgByDeviceCustomData(DeviceCustomData deviceCustomData) throws Exception {
        publishMsgByObject("device.customData",deviceCustomData);
    }

    /**
     * 发送对象消息
    * */
    private static void publishMsgByObject(String routingKey,Object o) throws Exception {
        //使用jackson序列化对象
        channel.basicPublish(EXCHANGE_NAME,routingKey,null, JsonUtil.convertToBytes(o));
    }

    public static void closeChannelAndConnection() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }
}
