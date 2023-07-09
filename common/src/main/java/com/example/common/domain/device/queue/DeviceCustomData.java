package com.example.common.domain.device.queue;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class DeviceCustomData implements Serializable{
    private static final long serialVersionUID = 875480469535697825L;
    private String deviceUuid;
    private Map<String,String> dataMap;
    private String dataInfo;
    private Date createTime;


    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
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
