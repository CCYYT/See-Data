package com.example.common.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@ApiModel("字段排序信息类")
public class SortInfo {
    @ApiModelProperty("排序字段(必填字段) 格式:下滑线命名 例:user_name")
    @NotBlank(message = "排序字段不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "sortField包含特殊字符")
    private String sortField;
    @ApiModelProperty("字符是否降序(默认升序)")
    private boolean desc;

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField){
        this.sortField = sortField;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }
}
