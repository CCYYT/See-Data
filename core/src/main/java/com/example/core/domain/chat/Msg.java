package com.example.core.domain.chat;

public class Msg {
    private int code; //状态码
    private boolean isSystem; //是否是系统消息
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public boolean getIsSystem() {
        return isSystem;
    }
    public void setIsSystem(boolean system) {
        isSystem = system;
    }
}
