package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 警报信息(Warning)实体类
 *
 * @author makejava
 * @since 2023-07-06 23:05:53
 */

@ApiModel("警报信息(Warning)实体类")
public class Warning implements Serializable {
    private static final long serialVersionUID = 879266033761950945L;
    @ApiModelProperty(value = "预警信息id")
    private Integer id;
    @JsonIgnore
    @ApiModelProperty(value = "设备id")
    private Integer deviceId;
    @ApiModelProperty(value = "设备uuid")
    private String deviceUuid;
    @ApiModelProperty(value = "设备名")
    private String deviceName;
    @JsonIgnore
    @ApiModelProperty(value = "区域id")
    private Integer areaId;
    @ApiModelProperty(value = "区域uuid")
    private String areaUuid;
    @ApiModelProperty(value = "区域名")
    private String areaName;
    @ApiModelProperty(value = "设备数据类型id")
    private Integer dataTypeId;
    @ApiModelProperty(value = "设备数据类型")
    private String dataType;
    @ApiModelProperty(value = "预警信息")
    private String warningInfo;
    @ApiModelProperty(value = "警告等级(0:普通,1:警告,2:严重,3:危险)")
    private Integer warningLevel;
    @ApiModelProperty(value = "警报处理状态(0:待处理,1:已处理)")
    private Integer status;
    @ApiModelProperty(value = "预警发生时间")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public Integer getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(Integer warningLevel) {
        this.warningLevel = warningLevel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAreaUuid() {
        return areaUuid;
    }

    public void setAreaUuid(String areaUuid) {
        this.areaUuid = areaUuid;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}

