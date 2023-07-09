package com.example.core.domain.chat;

import java.util.Set;
/**
 * 系统消息的JavaBean
 */

public class SystemMsg extends Msg {
    private Set<String> users;
    private String msg;

    public Set<String> getUsers() {
        return users;
    }
    public void setUsers(Set<String> users) {
        this.users = users;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
