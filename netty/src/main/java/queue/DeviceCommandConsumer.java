package queue;

import com.example.common.domain.device.queue.DeviceCommand;
import com.example.common.util.JsonUtil;
import com.rabbitmq.client.*;
import config.NettyConfig;
import domain.DataCodecCode;
import domain.DataTypeCode;
import io.netty.channel.ChannelHandlerContext;
import message.Message;
import util.RabbitUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DeviceCommandConsumer {

    static {
        try{
            // 获取连接
            Connection connection = RabbitUtil.getConnection();
            // 创建通道
            Channel channel = connection.createChannel();
            // 创建队列Queue
            /*
            queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            参数：
                1. queue：队列名称
                2. durable:是否持久化，当mq重启之后，还在
                3. exclusive：
                    * 是否独占。只能有一个消费者监听这队列
                    * 当Connection关闭时，是否删除队列
                4. autoDelete:是否自动删除。当没有Consumer时，自动删除掉
                5. arguments：参数。
             */
            //如果没有一个名字叫hello_world的队列，则会创建该队列，如果有则不会创建
            channel.queueDeclare("device_command",true,false,false,null);

            // 接收消息
            Consumer consumer = new DefaultConsumer(channel) {
                /*
                               回调方法，当收到消息后，会自动执行该方法
                               1. consumerTag：标识
                               2. envelope：获取一些信息，交换机，路由key...
                               3. properties:配置信息
                               4. body：数据
                            */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        //将数据转换为DeviceCommand对象
                        DeviceCommand deviceCommand = (DeviceCommand) JsonUtil.bytesToObject(body, DeviceCommand.class);
                        //获取deviceUuid对应的通道
                        ChannelHandlerContext ctx = NettyConfig.channelMap.get(deviceCommand.getDeviceUuid());
                        if(ctx != null){
                            //创建Message对象 写入通道
                            if((Integer.valueOf(0)).equals(deviceCommand.getCommandCodec())){
                                ctx.writeAndFlush(new Message(DataTypeCode.DEVICE_COMMAND_MSG, DataCodecCode.STRING_UTF8_CODEC,deviceCommand.getCommand()));
                            }else if((Integer.valueOf(1)).equals(deviceCommand.getCommandCodec())){
                                ctx.writeAndFlush(new Message(DataTypeCode.DEVICE_COMMAND_MSG, DataCodecCode.JSON_JACKSON_CODEC,deviceCommand.getCommand()));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }

            };

        /*
        basicConsume(String queue, boolean autoAck, Consumer callback)
        参数：
            1. queue：队列名称
            2. autoAck：是否自动确认
            3. callback：回调对象
         */

            channel.basicConsume("device_command",true,consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
