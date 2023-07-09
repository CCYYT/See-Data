package com.example.core.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 预警规则(WarningRules)实体类
 *
 * @author makejava
 * @since 2023-06-17 12:05:48
 */

@ApiModel("预警规则(WarningRules)实体类")
public class WarningRules implements Serializable {
    private static final long serialVersionUID = -65434867313283378L;
    @ApiModelProperty(value = "预警规则id")
    private Integer id;
    @JsonIgnore
    @ApiModelProperty(value = "设备id")
    private Integer deviceId;
    @NotBlank(message = "设备uuid不能为空")
    @ApiModelProperty(value = "设备uuid")
    private String deviceUuid;
    @NotNull(message = "数据类型id不能为空")
    @ApiModelProperty(value = "数据类型id")
    private Integer dataTypeId;
    @NotNull(message = "预警规则不能为空")
    @Range(min = 0,max = 2,message = "预警规则值范围错误 值范围{0(小于) | 1(等于) | 2(大于)}")
    @ApiModelProperty(value = "预警规则(0:小于,1:等于,2:大于)")
    private Integer warningRules;
    @NotBlank(message = "预警值不能为空")
    @ApiModelProperty(value = "预警值")
    private String warningValue;
    @NotNull(message = "警报等级不能为空")
    @Range(min = 0,max = 3,message = "警报等级范围错误 值范围(0:普通,1:警告,2:严重,3:危险)")
    @ApiModelProperty(value = "警报等级(0:普通,1:警告,2:严重,3:危险)")
    private Integer warningLevel;
    @NotBlank(message = "警告内容不能为空")
    @ApiModelProperty(value = "警告内容")
    private String warningInfo;
    @NotNull(message = "提示方式值不能为空")
    @Range(min = 0,max = 1,message = "提示方式范围错误 值范围(0:无,1:邮件)")
    @ApiModelProperty(value = "提示方式(0:无,1:邮件)")
    private Integer reminderMethod;
    @ApiModelProperty(value = "规则创建时间")
    private Date createTime;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String toMailString() {
        return "警报信息: " + warningInfo + "\n"+
                ", 设备UUID： " + deviceUuid + "\n" +
                ", 警报等级： " + warningLevel + "\n" +
                ", 数据类型id： " + dataTypeId + "\n" +
                ", 触发时间：" + formatter.format(createTime);
    }

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

    public Integer getWarningRules() {
        return warningRules;
    }

    public void setWarningRules(Integer warningRules) {
        this.warningRules = warningRules;
    }

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
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

    public Integer getReminderMethod() {
        return reminderMethod;
    }

    public void setReminderMethod(Integer reminderMethod) {
        this.reminderMethod = reminderMethod;
    }
}

