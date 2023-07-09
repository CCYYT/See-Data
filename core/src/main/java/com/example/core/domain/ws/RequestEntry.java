package com.example.core.domain.ws;


import com.example.common.page.PageInfo;
import com.example.common.page.PageInfoEnhance;

public class RequestEntry {
    private String requestDataType;//请求的数据类型
    private PageInfoEnhance param;//请求参数
    private String deviceCardName; //数据卡片名

    public String getRequestDataType() {
        return requestDataType;
    }

    public void setRequestDataType(String requestDataType) {
        this.requestDataType = requestDataType;
    }

    public PageInfoEnhance getParam() {
        return param;
    }

    public void setParam(PageInfoEnhance param) {
        this.param = param;
    }

    public String getDeviceCardName() {
        return deviceCardName;
    }

    public void setDeviceCardName(String deviceCardName) {
        this.deviceCardName = deviceCardName;
    }
}
