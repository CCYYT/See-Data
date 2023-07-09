package com.example.common.domain.device.queue;

import java.io.Serializable;
import java.util.Date;
/**
 * 设备上传时的json 实体类
* */
public class DeviceData implements Serializable {
    private static final long serialVersionUID = 875480469535697825L;
    private String deviceUuid;
    private String data;
    private String dataType;
    private String dataInfo;
    private Date createTime;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataInfo() {
        return dataInfo;
    }

    public void setDataInfo(String dataInfo) {
        this.dataInfo = dataInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
