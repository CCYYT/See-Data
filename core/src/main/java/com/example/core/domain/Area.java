package com.example.core.domain;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 区域信息(Area)实体类
 *
 * @author makejava
 * @since 2023-06-13 21:20:44
 */

@ApiModel("区域信息(Area)实体类")
public class Area implements Serializable {
    private static final long serialVersionUID = 875480465882250145L;
    @JsonIgnore
    @ApiModelProperty(value = "区域id")
    private Integer id;
    @ApiModelProperty(value = "区域的uuid")
    private String uuid;
    @NotBlank(message = "区域名不能为空")
    @ApiModelProperty(value = "区域名")
    private String areaName;
    @JsonIgnore
    @ApiModelProperty(value = "所属组id")
    private Integer groupId;
    @NotBlank(message = "所属用户组的uuid不能为空")
    @ApiModelProperty(value = "用户组的uuid")
    private String groupUuid;
    @ApiModelProperty(value = "区域描述信息")
    private String areaInfo;
    @ApiModelProperty(value = "区域创建时间(自动创建)")
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public void setGroupUuid(String groupUuid) {
        this.groupUuid = groupUuid;
    }
}

