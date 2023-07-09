package com.example.common.domain.device.queue;

/**
 * 更新设备数据状态 实体类
* */
public class DeviceStatus {
    // 设备uuid
    private String deviceUuid;
    // 设备状态
    private Integer status;

    public DeviceStatus(String deviceUuid, Integer status) {
        this.deviceUuid = deviceUuid;
        this.status = status;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
