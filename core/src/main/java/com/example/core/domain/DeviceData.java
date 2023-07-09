package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 设备数据信息(DeviceData)实体类
 *
 * @author makejava
 * @since 2023-06-16 18:20:55
 */

@ApiModel("设备数据信息(DeviceData)实体类")
public class DeviceData implements Serializable {
    private static final long serialVersionUID = 291419811541330208L;
    @ApiModelProperty(value = "设备数据id")
    private Integer id;
    @JsonIgnore
    @ApiModelProperty(value = "设备id")
    private Integer deviceId;
    @NotBlank(message = "设备uuid不能为空")
    @ApiModelProperty(value = "设备uuid")
    private String deviceUuid;
    @ApiModelProperty(value = "设备数据")
    private String deviceData;
    @ApiModelProperty(value = "数据类型Id")
    private Integer dataTypeId;
    @ApiModelProperty(value = "数据类型")
    private String dataType;
    @ApiModelProperty(value = "数据单位")
    private String dataUnit;
    @ApiModelProperty(value = "数据单位符号")
    private String dataUnitSymbol;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @ApiModelProperty(value = "数据产生的时间")
    private Date createTime;
    @ApiModelProperty(value = "设备数据描述信息")
    private String dataInfo;
    @ApiModelProperty(value = "设备数据类型描述信息")
    private String dataTypeInfo;

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

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }

    public String getDataUnitSymbol() {
        return dataUnitSymbol;
    }

    public void setDataUnitSymbol(String dataUnitSymbol) {
        this.dataUnitSymbol = dataUnitSymbol;
    }

    public String getDataTypeInfo() {
        return dataTypeInfo;
    }

    public void setDataTypeInfo(String dataTypeInfo) {
        this.dataTypeInfo = dataTypeInfo;
    }

    public Integer getDataTypeId() {
        return dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

