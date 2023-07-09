package clients;

import com.example.common.domain.device.openFeign.DeviceValidate;
import com.example.common.domain.device.openFeign.DevicesValidate;
import com.example.common.result.Result;
import feign.Headers;
import feign.RequestLine;

public interface DeviceClient {
    @Headers("Content-Type: application/json")
    @RequestLine("POST /device/validate")
    Result validate(DeviceValidate deviceValidate);

    @Headers("Content-Type: application/json")
    @RequestLine("POST /device/validates")
    Result validates(DevicesValidate devicesValidate);
}
