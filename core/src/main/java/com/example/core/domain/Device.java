package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 设备信息(Device)实体类
 *
 * @author makejava
 * @since 2023-06-16 17:17:26
 */

@ApiModel("设备信息(Device)实体类")
public class Device implements Serializable {
    private static final long serialVersionUID = 767626813061520922L;
    @JsonIgnore
    @ApiModelProperty(value = "设备id")
    private Integer id;
    @ApiModelProperty(value = "设备的uuid")
    private String uuid;
    @NotBlank(message = "设备名不能为空")
    @ApiModelProperty(value = "设备名")
    private String deviceName;
    @ApiModelProperty(value = "设备状态(0:离线,1:在线,2:未启用)")
    private Integer deviceStatus=0;
    @ApiModelProperty(value = "设备经度")
    private Double deviceLongitude;
    @ApiModelProperty(value = "设备纬度")
    private Double deviceLatitude;
//    @NotNull(message = "设备类型不能为空")
    @ApiModelProperty(value = "数据类型id")
    private Integer dataTypeId;
    @ApiModelProperty(value = "数据类型")
    private String dataType;
//    @NotBlank(msg)
    @ApiModelProperty(value = "数据最大值")
    private String dataMax;
    @ApiModelProperty(value = "数据最小值")
    private String dataMin;
    @ApiModelProperty(value = "数据图表类型")
    private String chartSort;
    @ApiModelProperty(value = "数据类型描述")
    private String dataTypeInfo;
    @JsonIgnore
    @ApiModelProperty(value = "设备所属区域id")
    private Integer areaId;
    @NotBlank(message = "设备所属区域不能为空")
    @ApiModelProperty(value = "设备所属区域uuid")
    private String areaUuid;
    @ApiModelProperty(value = "设备所属区域名")
    private String areaName;
    @ApiModelProperty(value = "设备的创建时间")
    private Date createTime;
    @ApiModelProperty(value = "设备描述信息")
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Integer deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Double getDeviceLongitude() {
        return deviceLongitude;
    }

    public void setDeviceLongitude(Double deviceLongitude) {
        this.deviceLongitude = deviceLongitude;
    }

    public Double getDeviceLatitude() {
        return deviceLatitude;
    }

    public void setDeviceLatitude(Double deviceLatitude) {
        this.deviceLatitude = deviceLatitude;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaUuid() {
        return areaUuid;
    }

    public void setAreaUuid(String areaUuid) {
        this.areaUuid = areaUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataTypeInfo() {
        return dataTypeInfo;
    }

    public void setDataTypeInfo(String dataTypeInfo) {
        this.dataTypeInfo = dataTypeInfo;
    }

    public String getDataMax() {
        return dataMax;
    }

    public void setDataMax(String dataMax) {
        this.dataMax = dataMax;
    }

    public String getDataMin() {
        return dataMin;
    }

    public void setDataMin(String dataMin) {
        this.dataMin = dataMin;
    }

    public String getChartSort() {
        return chartSort;
    }

    public void setChartSort(String chartSort) {
        this.chartSort = chartSort;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

