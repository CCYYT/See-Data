package com.example.common.domain.device.queue;

import java.io.Serializable;

/**
 * 发送给设备命令的实体类
 * */
public class DeviceCommand implements Serializable {
    private static final long serialVersionUID = 895830827515697825L;
    // 设备UUID
    private String deviceUuid;
    //命令
    private String command;
    //命令编解码格式 0:无 1:JSON
    private Integer commandCodec;

    public DeviceCommand() {}
    public DeviceCommand(String deviceUuid, String command, Integer commandCodec) {
        this.deviceUuid = deviceUuid;
        this.command = command;
        this.commandCodec = commandCodec;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getCommandCodec() {
        return commandCodec;
    }

    public void setCommandCodec(Integer commandCodec) {
        this.commandCodec = commandCodec;
    }
}
