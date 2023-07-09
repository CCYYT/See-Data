package com.example.core.queue;


import com.example.common.domain.device.queue.DeviceStatus;
import com.example.common.util.JsonUtil;
import com.example.core.domain.Device;
import com.example.core.event.DeviceDataEvent;
import com.example.core.service.DeviceService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class DeviceStatusConsumer {
    @Resource
    DeviceService deviceService;
    @Resource
    ApplicationContext applicationContext;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "device_status",durable = "true"),
            exchange = @Exchange(
                    name = "device",
                    type = ExchangeTypes.TOPIC
            ),
                key = "device.status"
            ))
    public void DeviceStatusConsumer(byte[] bytes) throws Exception {
        DeviceStatus deviceStatus = (DeviceStatus) JsonUtil.bytesToObject(bytes,DeviceStatus.class);
        /*
        * 发布 DeviceStatusUpdate 事件
        * */
        // 更新数据库中的设备状态
        deviceService.update(new Device(){{
            setUuid(deviceStatus.getDeviceUuid());
            setDeviceStatus(deviceStatus.getStatus());
        }});
    }


}
