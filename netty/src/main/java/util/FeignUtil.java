package util;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
* Feign(远程调用)工具类
* */
public class FeignUtil {

    private static String serverHost;
    private static Map<Class,Object> map = new ConcurrentHashMap<>();


    static {
        serverHost = "http://localhost";// 远程调用服务器地址
    }

    public static Object getClient(Class userClientClass){
        if (!map.containsKey(userClientClass)) {
            loadClient(userClientClass);
        }
        return map.get(userClientClass);
    }

    public static void loadClient(Class userClientClass){
        if(map.containsKey(userClientClass)) return;
        map.put(userClientClass, Feign.builder()
//                                    .decoder(new GsonDecoder()) // 返回内容为json格式，所以需要用到json解码器  Gson不太行
//                                    .encoder(new GsonEncoder()) // 使用json编码器编码  Gson不太行
                                    .decoder(new JacksonDecoder())// 返回内容为json格式，所以需要用到json解码器 Jackson 行
                                    .encoder(new JacksonEncoder())// 使用json编码器编码 Jackson 行
                                    // .options(new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true)) // 配置超时参数等
                                    .target(userClientClass, serverHost));
    }
}
