package com.example.core.Listener;

import com.example.common.domain.mail.SimpleMail;
import com.example.core.domain.WarningRules;
import com.example.core.event.WarningEvent;
import com.example.core.service.RemindService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 警报事件监听器
* */
@Component
public class WarningListener {
    @Resource
    RemindService remindService;
    @Resource
    RabbitTemplate rabbitTemplate;

    @Async
    @EventListener
    public void warn(WarningEvent event){
        WarningRules warningRules = event.getWarningRules();
        if((Integer.valueOf(1)).equals(warningRules.getReminderMethod())){
            sendMail(warningRules);
        }
    }

    /**
     * 发送邮箱
     * */
    public void sendMail(WarningRules warningRules){
        List<String> mailList = remindService.queryUserMailByWarningRulesId(warningRules.getId());
        for (String mail : mailList) {
            rabbitTemplate.convertAndSend("mail", "mail.simpleMail", SimpleMail.generateWarningMail(mail, warningRules.toMailString()));
        }
    }
}
