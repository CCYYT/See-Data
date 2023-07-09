package Manage;

import clients.UserClient;
import com.example.common.result.Code;
import com.example.common.result.Result;
import util.FeignUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* 凭证信息管理
* */
public class CredentialManage{
    // 缓存凭证信息
    private static Map<Integer,Long> credentialPool = new ConcurrentHashMap<>();

//    static {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    long l = System.currentTimeMillis(); //获取当前时间戳
//                    for (Integer integer : credentialPool.keySet()) {
//                        if(credentialPool.get(integer) != 0L && l > credentialPool.get(integer)) {
//                            credentialPool.put(integer, 0L);
//                            System.out.println(integer+" 过期了");
//                        }
//                    }
//                }
//            }
//        }).start();
//    }

    // 验证凭证
    public static Boolean voucherVerification(int voucher){
        long l = System.currentTimeMillis(); //获取当前时间戳
        if (credentialPool.containsKey(voucher)) {
//            if(credentialPool.get(voucher)!=0L) return true;
            if(l < credentialPool.get(voucher)) return true;
        }else {
            getVoucherInfo(voucher);
            return voucherVerification(voucher);
        }
        return false;
    }
    // 获取凭证信息
    private static void getVoucherInfo(int voucher){
        Result result = ((UserClient) FeignUtil.getClient(UserClient.class)).voucherVerification(new HashMap<String, Object>(){{put("voucher",voucher);}});
        if(Code.VERIFICATION_ERR.equals(result.getCode())){
            credentialPool.put(voucher,0L);
        }
        if (Code.VERIFICATION_OK.equals(result.getCode())) {
            credentialPool.put(voucher, Long.valueOf(((Map<String, String>) result.getData()).get("PeriodOfValidity")));
        }
    }
}
