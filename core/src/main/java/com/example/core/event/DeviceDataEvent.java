package com.example.core.event;

import com.example.common.domain.device.queue.DeviceData;
import org.springframework.context.ApplicationEvent;

/**
* 新增设备数据事件
* */
public class DeviceDataEvent extends ApplicationEvent {
    private DeviceData deviceData;//设备数据
    public DeviceDataEvent(Object source, DeviceData deviceData) {
        super(source);
        this.deviceData = deviceData;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }
}
