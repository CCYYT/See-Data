package com.example.core.event;

import com.example.core.domain.HistoryCommand;
import org.springframework.context.ApplicationEvent;

/**
* 新增设备数据事件
* */
public class CommandEvent extends ApplicationEvent {
    private HistoryCommand command;
    public CommandEvent(Object source, HistoryCommand command) {
        super(source);
        this.command = command;
    }

    public HistoryCommand getCommand() {
        return command;
    }
}
