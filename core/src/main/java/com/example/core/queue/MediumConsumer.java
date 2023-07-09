package com.example.core.queue;

import com.example.core.websocket.MediaWebSocket;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class MediumConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "my_topic_queue_2",durable = "false"),
            exchange = @Exchange(
                    name = "medium_exchange",
                    type = ExchangeTypes.TOPIC,
                    durable = "false"
//                    ignoreDeclarationExceptions = "true"
            ),
                key = "medium.video.handle"
            ))
    public void consumer(byte[] bytes){
        MediaWebSocket.broadcast(bytes);
//        System.out.println(new String(bytes));
    }
}
