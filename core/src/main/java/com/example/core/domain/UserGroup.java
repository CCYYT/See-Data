package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 用户组信息(UserGroup)实体类
 *
 * @author makejava
 * @since 2023-06-10 14:01:32
 */

@ApiModel("用户组信息(UserGroup)实体类")
public class UserGroup implements Serializable {
    private static final long serialVersionUID = -94734423982410684L;
    @JsonIgnore
    @ApiModelProperty(value = "用户组id")
    private Integer id;
    @ApiModelProperty(value = "用户组的uuid")
    private String uuid;
    @NotBlank
    @ApiModelProperty(value = "用户组名")
    private String groupName;
    @ApiModelProperty(value = "用户组描述信息")
    private String info;
    @ApiModelProperty(value = "用户组创建时间(自动创建)")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

