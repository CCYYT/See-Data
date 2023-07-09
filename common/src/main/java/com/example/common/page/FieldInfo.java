package com.example.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@ApiModel("分页查询字段信息类")
public class FieldInfo {
    @Valid
    @ApiModelProperty("条件信息 (字段名,匹配规则)")
    private ConditionInfo condition;

    @Pattern(regexp = "(desc|asc)",message = "规则错误 规则范围(desc|asc)")
    @ApiModelProperty("排序信息")
    private String sort;

    public ConditionInfo getCondition() {
        return condition;
    }

    public void setCondition(ConditionInfo condition) {
        this.condition = condition;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
