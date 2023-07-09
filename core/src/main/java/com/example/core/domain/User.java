package com.example.core.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用户信息(User)实体类
 *
 * @author makejava
 * @since 2023-06-11 18:37:59
 */

@ApiModel("用户信息(User)实体类")
public class User implements Serializable {
    private static final long serialVersionUID = -66827485014312998L;
    @JsonIgnore
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户的uuid")
    private String uuid;
    @NotBlank
    @ApiModelProperty(value = "用户名")
    private String userName;
    @NotBlank
    @ApiModelProperty(value = "用户密码")
    private String passwd;

    @Email(message = "邮箱地址错误")
    @ApiModelProperty(value = "用户邮箱")
    private String mail;
    @Range(min = 0, max = 2,message = "用户类型错误 范围(0-2)")
    @ApiModelProperty(value = "用户类型(0:普通用户,1:区域管理员,2:用户组管理员)")
    private Integer type=0;
    @Range(min = 0, max = 1,message = "用户状态错误 范围(0-1)")
    @ApiModelProperty(value = "用户状态(0:启用,1:停用)")
    private Integer status=0;
    @NotBlank(message = "用户组uuid不能为空")
    @ApiModelProperty(value = "用户组的uuid")
    private String groupUuid;
    @JsonIgnore
    @ApiModelProperty(value = "用户组id")
    private Integer groupId;
    @ApiModelProperty(value = "用户描述信息")
    private String userInfo;
    @ApiModelProperty(value = "用户创建时间(自动创建)")
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public void setGroupUuid(String groupUuid) {
        this.groupUuid = groupUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

