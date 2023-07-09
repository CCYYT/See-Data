package com.example.core.domain.ws;

public class ResponseEntry {
    private String deviceCardName;// 数据卡片名
    private Object content;// 查询结果

    public ResponseEntry(String deviceCardName, Object content) {
        this.deviceCardName = deviceCardName;
        this.content = content;
    }

    public String getDeviceCardName() {
        return deviceCardName;
    }

    public void setDeviceCardName(String deviceCardName) {
        this.deviceCardName = deviceCardName;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
