package com.example.core.event;

import com.example.core.domain.Warning;
import com.example.core.domain.WarningRules;
import org.apache.catalina.core.StandardContext;
import org.springframework.context.ApplicationEvent;

/**
* 警报事件
* */
public class WarningEvent extends ApplicationEvent {
    private Warning warning;//警报内容
    private WarningRules warningRules;//触发的规则

    public WarningEvent(Object source, Warning warning, WarningRules warningRules) {
        super(source);
        this.warning = warning;
        this.warningRules = warningRules;
    }

    public Warning getWarning() {
        return warning;
    }

    public void setWarning(Warning warning) {
        this.warning = warning;
    }

    public WarningRules getWarningRules() {
        return warningRules;
    }

    public void setWarningRules(WarningRules warningRules) {
        this.warningRules = warningRules;
    }
}
