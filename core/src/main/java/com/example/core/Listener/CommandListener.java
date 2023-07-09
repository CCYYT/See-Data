package com.example.core.Listener;

import com.example.common.domain.device.queue.DeviceCommand;
import com.example.common.util.JsonUtil;
import com.example.core.domain.HistoryCommand;
import com.example.core.event.CommandEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 命令事件监听器
* */
@Component
public class CommandListener {
    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 将设备命令 异步发送到rabbitMQ
    * */
    @Async
    @EventListener
    public void sendCommand(CommandEvent event) throws Exception {
        HistoryCommand command = event.getCommand();
        DeviceCommand deviceCommand = new DeviceCommand(command.getDeviceUuid(), command.getCommand(), command.getCommandCodec());
        rabbitTemplate.convertAndSend(
                "device",
                "device.command",
                JsonUtil.convertToBytes(deviceCommand)
        );
    }

}
