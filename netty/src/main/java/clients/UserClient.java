package clients;



import com.example.common.result.Result;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface UserClient {
    @Headers("Content-Type: application/json")
    @RequestLine("POST /user/voucher/verification")
    Result voucherVerification(Map<String,Object> body);
}
