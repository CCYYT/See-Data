package com.example.common.domain.device.openFeign;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel("设备信息校验(DeviceValidate)实体类")
public class DevicesValidate implements Serializable {
    private static final long serialVersionUID = 893567862185697825L;
    @NotBlank(message = "区域uuid不能为空")
    private String areaUuid;
    @Size(min = 1,max = 5,message = "设备列表范围(1-5)")
    @NotNull(message = "设备列表不能为空")
    private List<String> devicesUuid;

    public String getAreaUuid() {
        return areaUuid;
    }

    public void setAreaUuid(String areaUuid) {
        this.areaUuid = areaUuid;
    }

    public List<String> getDevicesUuid() {
        return devicesUuid;
    }

    public void setDevicesUuid(List<String> devicesUuid) {
        this.devicesUuid = devicesUuid;
    }
}
