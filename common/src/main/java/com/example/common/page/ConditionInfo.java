package com.example.common.page;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel("字段匹配规则")
public class ConditionInfo {
    @NotNull(message = "条件为空")
    @Size(min=1,message = "条件为空")
    private List<@NotBlank(message = "条件值为空") String> value;
    @NotNull(message = "模式为空")
    @Pattern(regexp = "(in|equal|like|range)",message = "模式错误 模式范围(in|equal|like|range)")
    private String mode;

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
