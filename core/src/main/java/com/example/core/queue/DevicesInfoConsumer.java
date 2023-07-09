//package com.example.mqtt_springBoot_demo.queue;
//
//import org.springframework.amqp.core.ExchangeTypes;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DevicesInfoConsumer {
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "my_topic_queue_1",durable = "false"),
//            exchange = @Exchange(
//                    name = "device_info_exchange",
//                    type = ExchangeTypes.TOPIC,
//                    durable = "false"
////                    ignoreDeclarationExceptions = "true"
//            ),
//                key = "device.info.#"
//            ))
//    public void deviceInfo(DeviceMsg deviceMsg){
////        MediaWebSocket.broadcast(bytes);
////        System.out.println(new String(bytes));
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "my_topic_queue_1",durable = "false"),
//            exchange = @Exchange(
//                    name = "device_info_exchange",
//                    type = ExchangeTypes.TOPIC,
//                    durable = "false"
////                    ignoreDeclarationExceptions = "true"
//            ),
//            key = "devices.info.#"
//    ))
//    public void devicesInfo(DevicesMsg devicesMsg){
//
////        MediaWebSocket.broadcast(bytes);
////        System.out.println(new String(bytes));
//    }
//
//}
