//package com.example.core.config;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.support.converter.MessageConversionException;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//
//@Configuration
//public class RabbitConfig {
//    @Resource
//    private ConnectionFactory connectionFactory;
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory customRabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new CustomMessageConverter()); // 设置自定义的消息转换器
//        return factory;
//    }
//
//    public class CustomMessageConverter implements MessageConverter {
//        private ObjectMapper objectMapper = new ObjectMapper();
//
//        @Override
//        public Object fromMessage(Message message) throws MessageConversionException {
//            try {
//                // 将消息的字节数据转换为自定义的 Java 对象
//                byte[] body = message.getBody();
//                return objectMapper.readValue(body, DeviceData.class);
//            } catch (IOException e) {
//                throw new MessageConversionException("Failed to convert message", e);
//            }
//        }
//    }
//
//}
