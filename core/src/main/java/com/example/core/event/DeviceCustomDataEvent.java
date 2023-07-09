package com.example.core.event;

import com.example.common.domain.device.queue.DeviceCustomData;
import org.springframework.context.ApplicationEvent;

/**
* 新增设备数据事件
* */
public class DeviceCustomDataEvent extends ApplicationEvent {
    private DeviceCustomData deviceCustomData;//设备数据
    public DeviceCustomDataEvent(Object source, DeviceCustomData deviceCustomData) {
        super(source);
        this.deviceCustomData = deviceCustomData;
    }

    public DeviceCustomData getDeviceCustomData() {
        return deviceCustomData;
    }
}
