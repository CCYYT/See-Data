package com.example.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2023-06-03 16:52:22
 */
@ApiModel("(Admin)实体类")
public class Admin implements Serializable {
    private static final long serialVersionUID = 438695755599319725L;
    /**
    * 超级管理员id
    */
    @JsonIgnore
    @ApiModelProperty(value = "超级管理员id")
    private Integer id;
    @ApiModelProperty(value = "超级管理员uuid")
    private String uuid;
    /**
     * 超级管理员名
     */
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "超级管理员名")
    private String name;
    /**
     * 超级管理员密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "超级管理员密码")
    private String passwd;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}

