package com.example.mail.queue;

import com.example.common.domain.mail.SimpleMail;
import com.example.mail.service.MailService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MailConsumer {

    @Resource
    MailService mailService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "mail_simpleMail"),
            exchange = @Exchange(
                    name = "mail",
                    type = ExchangeTypes.TOPIC
//                    durable = "false"
//                    ignoreDeclarationExceptions = "true"
            ),
            key = "mail.simpleMail"
            ))
    public void simpleMailConsumer(SimpleMail simpleMail){
        mailService.sendSimpleMail(simpleMail);
    }
}
