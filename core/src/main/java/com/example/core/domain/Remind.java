package com.example.core.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 提示接收中间表(Remind)实体类
 *
 * @author makejava
 * @since 2023-07-02 15:07:17
 */

@ApiModel("提示接收中间表(Remind)实体类")
public class Remind implements Serializable {
    private static final long serialVersionUID = -26344700078600390L;
    @ApiModelProperty(value = "提示接收中间表 id")
    private Integer id;
    @ApiModelProperty(value = "预警规则id")
    private Integer warningRulesId;
    @JsonIgnore
    @ApiModelProperty(value = "接收提示的用户id")
    private Integer userId;
    @ApiModelProperty(value = "接收提示的用户Uuid")
    private String userUuid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarningRulesId() {
        return warningRulesId;
    }

    public void setWarningRulesId(Integer warningRulesId) {
        this.warningRulesId = warningRulesId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}

