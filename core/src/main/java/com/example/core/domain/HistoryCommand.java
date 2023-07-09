package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 历史命令信息(HistoryCommand)实体类
 *
 * @author makejava
 * @since 2023-07-03 16:59:27
 */

@ApiModel("历史命令信息(HistoryCommand)实体类")
public class HistoryCommand implements Serializable {
    private static final long serialVersionUID = 891048776812216093L;
    @ApiModelProperty(value = "历史指令id")
    private Integer id;
    @NotBlank(message = "发送命令内容不能为空")
    @ApiModelProperty(value = "发送的命令")
    private String command;
    @JsonIgnore
    @ApiModelProperty(value = "发送给目标设备的id")
    private Integer deviceId;
    @NotBlank(message = "目标设备的uuid不能为空")
    @ApiModelProperty(value = "发送给目标设备的uuid")
    private String deviceUuid;
    @JsonIgnore
    @ApiModelProperty(value = "发送命令的用户id")
    private Integer userId;
    @ApiModelProperty(value = "发送命令的用户uuid")
    private String userUuid;
    @ApiModelProperty(value = "命令编解码格式(0:无,1:JSON) 默认为1")
    private Integer commandCodec=1;
    @ApiModelProperty(value = "命令描述")
    private String info;
    @ApiModelProperty(value = "发送的时间")
    private Date sendTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Integer getCommandCodec() {
        return commandCodec;
    }

    public void setCommandCodec(Integer commandCodec) {
        this.commandCodec = commandCodec;
    }
}

