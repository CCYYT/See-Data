package com.example.common.domain.device.openFeign;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@ApiModel("设备信息校验(DeviceValidate)实体类")
public class DeviceValidate implements Serializable {
    private static final long serialVersionUID = 893567862185697825L;
    @NotBlank(message = "区域uuid不能为空")
    private String areaUuid;
    @NotBlank(message = "设备uuid不能为空")
    private String deviceUuid;

    public String getAreaUuid() {
        return areaUuid;
    }

    public void setAreaUuid(String areaUuid) {
        this.areaUuid = areaUuid;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }
}
