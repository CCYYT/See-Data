package com.example.core.queue;

import com.example.common.domain.device.queue.DeviceCustomData;
import com.example.common.domain.device.queue.DeviceData;
import com.example.common.util.JsonUtil;
import com.example.core.event.DeviceCustomDataEvent;
import com.example.core.event.DeviceDataEvent;
import com.example.core.service.DeviceDataService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DeviceDataConsumer {
    @Resource
    DeviceDataService deviceDataService;
    @Resource
    ApplicationContext applicationContext;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "device_data",durable = "true"),
            exchange = @Exchange(
                    name = "device",
                    type = ExchangeTypes.TOPIC
            ),
                key = "device.data"
            ))
    public void DeviceDataConsumer(byte[] bytes) throws Exception {
        DeviceData deviceData = (DeviceData) JsonUtil.bytesToObject(bytes,DeviceData.class);
        // 发布新增 DeviceData类型数据 事件
        applicationContext.publishEvent(new DeviceDataEvent(this,deviceData));
        // 将数据存入数据库
        deviceDataService.insert(new com.example.core.domain.DeviceData(){{
            setDeviceUuid(deviceData.getDeviceUuid());
            setDeviceData(deviceData.getData());
            setDataInfo(deviceData.getDataInfo());
            setDataType(deviceData.getDataType());
         }});
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "device_custom_data",durable = "true"),
            exchange = @Exchange(
                    name = "device",
                    type = ExchangeTypes.TOPIC
            ),
            key = "device.customData"
    ))
    public void DeviceCustomDataConsumer(byte[] bytes) throws Exception {
        DeviceCustomData deviceCustomData = (DeviceCustomData) JsonUtil.bytesToObject(bytes,DeviceCustomData.class);
        // 发布新增 DeviceCustomData类型数据 事件
        applicationContext.publishEvent(new DeviceCustomDataEvent(this,deviceCustomData));
        deviceDataService.insert(deviceCustomData);
    }

}
