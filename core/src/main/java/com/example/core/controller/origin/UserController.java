//package com.example.mqtt_springBoot_demo.controller.origin;
//
//import com.example.mqtt_springBoot_demo.controller.Code;
//import com.example.mqtt_springBoot_demo.domain.Result;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @PostMapping("/voucher/verification")
//    public Result verificationVoucher(@RequestBody Map<String,Object> body){
//        int voucher = (int) body.get("voucher");
//        Result result;
//        if(voucher == 0x65733332){
//            result = new Result(Code.VERIFICATION_OK,
//                    new HashMap<String,String>(){{
//                                        put("PeriodOfValidity", String.valueOf(new Date(1717135500000L).getTime()));
//                                    }}
//            );
//        }else {
//            result = new Result(Code.VERIFICATION_ERR,null);
//        }
//        return result;
//    }
//}
